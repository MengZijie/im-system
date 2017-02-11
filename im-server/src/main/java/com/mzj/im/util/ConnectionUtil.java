package com.mzj.im.util;

import com.mzj.im.model.vo.UserVO;

import java.util.HashMap;

/**
 * Created by OB on 2017/2/9.
 */
public final class ConnectionUtil {

    private static HashMap<Long, UserVO> connectUserMap;

    public static HashMap<Long, UserVO> getConnectUserMap() {
        if (ObjectUtil.isNull(connectUserMap)) {
            connectUserMap = new HashMap<>();
        }
        return connectUserMap;
    }

    public static void addUser(UserVO user) {
        getConnectUserMap().put(user.getId(), user);
    }

    public static void removeUser(UserVO user) {
        getConnectUserMap().remove(user.getId());
    }

    public static boolean containsUser(UserVO user) {
        return getConnectUserMap().containsKey(user.getId());
    }
}
