package com.mzj.im.model.vo;

import com.mzj.im.model.po.UserPO;
import com.mzj.im.util.ObjectUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created by OB on 2017/2/3.
 */
public class UserVO extends UserPO implements Serializable {

    private boolean empty;

    public boolean online;

    public boolean lock;

    public UserVO() {
        super();
        empty = false;
        online = false;
    }

    public UserVO(UserPO userPO) {
        if (ObjectUtil.isNotNull(userPO)) {
            setId(userPO.getId());
            setUsername(userPO.getUsername());
            setNickName(userPO.getNickName());
            setPassword(userPO.getPassword());
            setSalt(userPO.getSalt());
            setPhoto(userPO.getPhoto());
            setSign(userPO.getSign());
            setEmail(userPO.getEmail());
            setCreateTime(userPO.getCreateTime());
            setUpdateTime(userPO.getUpdateTime());
            setIsDelete(userPO.getIsDelete());
        } else {
            empty = true;
        }
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        online = online;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
        setIsDelete('Y');
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setNotEmpty() {
        empty = false;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public UserVO publishUserVO() {
        if (StringUtils.isEmpty(getUsername())) {
            return null;
        }
        setPassword(null);
        setSalt(null);
        return this;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getId());
        jsonObject.put("username", getUsername());
        return jsonObject;
    }
}
