package com.mzj.im.dao.redis;


import com.mzj.im.model.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by ob on 17-3-17.
 */
@Component
public class RedisUserDAO extends RedisBaseDAO<Long, UserVO> {

    private static final String PREFIX = "user_map_key_";

    private static String getUserKey(long userId) {
        return new StringBuffer(PREFIX).append(String.valueOf(userId)).toString();
    }

    public UserVO get(final long userId) {
        return super.get(getUserKey(userId));
    }

    public void put(final UserVO user) {
        super.set(getUserKey(user.getId()), user);
    }

//    public void putAll(final Map<String, UserVO> map) {
//        super.set(map);
//    }

    public void delete(final long userId) {
        super.delete(getUserKey(userId));
    }

}