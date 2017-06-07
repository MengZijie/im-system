package com.mzj.im.web.webSocket;

import com.mzj.im.dao.redis.RedisUserDAO;
import com.mzj.im.model.vo.UserVO;
import com.mzj.im.util.ConnectionUtil;
import com.mzj.im.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by OB on 2017/2/9.
 */
public class CustomHandShakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private RedisUserDAO redisUserDAO;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpSession session =  ((ServletServerHttpRequest) request).getServletRequest().getSession(false);
            UserVO user = (UserVO) session.getAttribute("user");
            if (ObjectUtil.isNotNull(user)) {
                ConnectionUtil.addUser(user);
                attributes.put("userId", user.getId());
                System.out.println("WebSocket [用户: " + user.getUsername() +"]" + "正在连接");
                return true;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        if (request instanceof ServletServerHttpRequest) {
            HttpSession session = ((ServletServerHttpRequest) request).getServletRequest().getSession(false);
            UserVO user = (UserVO) session.getAttribute("user");
            if (ObjectUtil.isNotNull(user)) {
                user.setAddress(request.getLocalAddress());
                redisUserDAO.put(user);
            }
            redisUserDAO.put(user);
        }
    }

}
