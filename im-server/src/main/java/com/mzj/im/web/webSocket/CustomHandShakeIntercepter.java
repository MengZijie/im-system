package com.mzj.im.web.webSocket;

import com.mzj.im.model.vo.UserVO;
import com.mzj.im.util.ConnectionUtil;
import com.mzj.im.util.ObjectUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by OB on 2017/2/9.
 */
public class CustomHandShakeIntercepter implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpSession session = ((ServletServerHttpRequest) request).getServletRequest().getSession(false);
            UserVO user = (UserVO) session.getAttribute("user");
            if (ObjectUtil.isNotNull(user)) {
                ConnectionUtil.addUser(user);
                System.out.println("WebSocket [用户: " + user.getUsername() + "已连接");
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }

}
