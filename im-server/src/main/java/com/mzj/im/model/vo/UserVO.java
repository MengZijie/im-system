package com.mzj.im.model.vo;

import com.mzj.im.model.po.UserPO;
import com.mzj.im.util.ObjectUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by OB on 2017/2/3.
 */
public class UserVO extends UserPO {

    private boolean isEmpty;

    public UserVO() {
        super();
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
            isEmpty = true;
        }
    }

    public boolean isLock() {
        return "Y".equals(StringUtils.upperCase(String.valueOf(getIsDelete())));
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setNotEmpty() {
        isEmpty = false;
    }

    public UserVO publishUserVO() {
        if (StringUtils.isEmpty(getUsername())) {
            return null;
        }
        setPassword(null);
        setSalt(null);
        return this;
    }
}
