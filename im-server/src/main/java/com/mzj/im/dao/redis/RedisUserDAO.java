package com.mzj.im.dao.redis;


import com.mzj.im.model.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by ob on 17-3-17.
 */
@Component
public class RedisUserDAO extends RedisBaseDAO<Long, UserVO> {

    public UserVO get(final long userId) {
        return super.get(String.valueOf(userId));
    }

    public void put(final UserVO user) {
        super.set(String.valueOf(user.getId()), user);
    }

    public void putAll(final Map<String, UserVO> map) {
        super.set(map);
    }

    public void delete(final Long key) {
        super.delete(String.valueOf(key));
    }
}