package com.mzj.im.model.vo;

import com.mzj.im.model.po.UserPO;
import com.mzj.im.util.ObjectUtil;
import com.mzj.im.util.ToJSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * Created by OB on 2017/2/3.
 */
public class UserVO extends UserPO implements Serializable, ToJSON {

    private boolean empty;

    private boolean online;

    private boolean lock;

    private InetSocketAddress address;

    public UserVO() {
        super();
        empty = false;
        online = false;
    }

    public UserVO(UserPO userPO) {
        if (ObjectUtil.isNotNull(userPO)) {
            setId(userPO.getId());
            setUsername(userPO.getUsername());
            setNickname(userPO.getNickname());
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

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        return jsonObject;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "empty=" + empty +
                ", online=" + online +
                ", lock=" + lock +
                ", address=" + address +
                "} " + super.toString();
    }
}
