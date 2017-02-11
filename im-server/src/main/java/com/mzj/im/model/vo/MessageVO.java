package com.mzj.im.model.vo;

import com.alibaba.fastjson.JSONObject;
import com.mzj.im.model.po.MessagePO;

/**
 * Created by OB on 2017/2/9.
 */
public class MessageVO extends MessagePO {
    public MessageVO() {
        super();
    }

    public MessageVO(MessagePO messagePO) {
        setId(messagePO.getId());
        setOriginUserId(messagePO.getOriginUserId());
        setTargetUserId(messagePO.getTargetUserId());
        setMessageType(messagePO.getMessageType());
        setText(messagePO.getText());
        setSendTime(messagePO.getSendTime());
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", getId());
        json.put("originUserId", getOriginUserId());
        json.put("targetUserId", getTargetUserId());
        json.put("messageType", getMessageType());
        json.put("text", getText());
        json.put("sendTime", getSendTime());
        return json;
    }
}
