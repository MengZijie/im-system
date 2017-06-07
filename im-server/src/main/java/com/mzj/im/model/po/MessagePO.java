package com.mzj.im.model.po;

import com.mzj.im.util.ToJSON;

import java.sql.Timestamp;

/**
 * Created by OB on 2017/2/9.
 */
public class MessagePO {
    private long id;
    private long originUserId;
    private long targetUserId;
    private int messageType;
    private String text;
    private Timestamp sendTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(long originUserId) {
        this.originUserId = originUserId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }
}