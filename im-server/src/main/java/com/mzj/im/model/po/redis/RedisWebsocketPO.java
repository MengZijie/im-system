package com.mzj.im.model.po.redis;

import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;

/**
 * Created by ob on 17-3-24.
 */
public class RedisWebsocketPO implements Serializable {
    private WebSocketSession session;

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
