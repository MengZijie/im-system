package com.mzj.im.model.po;

import java.sql.Timestamp;

/**
 * Created by ob on 17-3-23.
 */
public class MsgPO implements Comparable {
    private long id;                    //id
    private long postUserId;            //post_user_id
    private long getUserId;             //getUserId
    private int msgMode;                //msg_mode
    private int msgType;                //msg_type
    private String msgDesc;             //msg_desc
    private char isOnline;              //is_online
    private char hasRead;               //has_read
    private Timestamp createTime;       //create_time
    private char isDelete;              //is_delete


    @Override
    public int compareTo(Object o) {
        return ((MsgPO) o).createTime.getTime() > this.createTime.getTime() ? -1 : 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(long postUserId) {
        this.postUserId = postUserId;
    }

    public long getGetUserId() {
        return getUserId;
    }

    public void setGetUserId(long getUserId) {
        this.getUserId = getUserId;
    }

    public int getMsgMode() {
        return msgMode;
    }

    public void setMsgMode(int msgMode) {
        this.msgMode = msgMode;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }

    public char getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(char isOnline) {
        this.isOnline = isOnline;
    }

    public char getHasRead() {
        return hasRead;
    }

    public void setHasRead(char hasRead) {
        this.hasRead = hasRead;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public char getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(char isDelete) {
        this.isDelete = isDelete;
    }
}
