package com.mzj.im.dao.redis;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketSessionDecorator;

/**
 * Created by ob on 17-3-24.
 */
@Component
public class WebSocketDAO extends RedisBaseDAO <String, WebSocketSession> {
}
