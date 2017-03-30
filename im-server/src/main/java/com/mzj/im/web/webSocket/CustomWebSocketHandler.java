package com.mzj.im.web.webSocket;

import com.mzj.im.dao.redis.WebSocketDAO;
import com.mzj.im.model.vo.MessageVO;
import com.mzj.im.model.vo.UserVO;
import com.mzj.im.service.MessageService;
import com.mzj.im.util.ObjectUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by OB on 2017/2/9.
 */
@Component
public class CustomWebSocketHandler implements WebSocketHandler {

    @Autowired
    private MessageService messageService;

    @Autowired
    private WebSocketDAO webSocketDAO;

    private static final Map<Long, WebSocketSession> userWebSocketSessionMap;

    static {
        userWebSocketSessionMap = new HashMap<>();
    }

    /**
     * 连接建立后
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        long id =(Long) session.getAttributes().get("userId");
        if (ObjectUtil.isNull(userWebSocketSessionMap.get(id))) {
            userWebSocketSessionMap.put(id, session);
        }
    }

    /**
     * 发送消息前预处理
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message.getPayloadLength() != 0) {

//            MessageVO msg = JSONObject.toJavaObject(JSONObject.parseObject(message.getPayload().toString()), MessageVO.class);
            MessageVO msg = (MessageVO) JSONObject.toBean(JSONObject.fromObject(message.getPayload().toString()));
            Timestamp time = new Timestamp(System.currentTimeMillis());
            msg.setSendTime(time);
            // TODO: 2017/2/9 优化：message先缓存到redis中，然后持久化 
            messageService.addOneMessage(msg);
            sendMessageToUser(msg.getOriginUserId(), new TextMessage(msg.toJSON().toString()));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        UserVO user = (UserVO) session.getAttributes().get("user");
        Iterator<Map.Entry<Long, WebSocketSession>> it = userWebSocketSessionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userWebSocketSessionMap.remove(entry.getKey());
                break;
            }
        }
        if (session.isOpen()) {
            session.close();
        }
    }

    /**
     * 在此刷新页面就相当于断开WebSocket连接,
     * 原本在静态变量userWebSocketSessionMap中的WebSocketSession会变成关闭状态(close)，
     * 但是刷新后的第二次连接服务器创建的新WebSocketSession(open状态)
     * 又不会加入到userWebSocketSessionMap中,所以这样就无法发送消息
     * 因此应当在关闭连接这个切面增加去除userWebSocketSessionMap中当前处于close状态的WebSocketSession，
     * 让新创建的WebSocketSession(open状态)可以加入到userWebSocketSessionMap中
     *
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        UserVO user = (UserVO) session.getAttributes().get("user");
        System.out.println("WebSocket: 用户 " + user.getUsername() + "断开连接");
        Iterator<Map.Entry<Long, WebSocketSession>> it = userWebSocketSessionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userWebSocketSessionMap.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 发送消息
     *
     * @param userId
     * @param message
     * @throws IOException
     */
    public void sendMessageToUser(long userId, TextMessage message) throws IOException {
        WebSocketSession session = userWebSocketSessionMap.get(userId);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void broadcast(final TextMessage message) throws IOException {
        Iterator<Map.Entry<Long, WebSocketSession>> it = userWebSocketSessionMap.entrySet().iterator();
        // 多线程群发
        while (it.hasNext()) {
            final Map.Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }

        }
    }
}
