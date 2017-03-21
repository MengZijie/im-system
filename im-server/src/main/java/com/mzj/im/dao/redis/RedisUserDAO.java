package com.mzj.im.dao.redis;


import com.mzj.im.model.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by ob on 17-3-17.
 */
@Component
public class RedisUserDAO extends RedisBaseDAO<Long, UserVO> {

    public UserVO get(final Long userId) {
        UserVO result = redisTemplate.execute(new RedisCallback<UserVO>() {
            @Override
            public UserVO doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = getKeySerializer().serialize(userId);
                byte[] value = connection.get(key);
                return value == null ? null : getValueSerializer().deserialize(value);
            }
        });
        return result;
    }

    public boolean put(final UserVO user) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = getKeySerializer().serialize(user.getId());
                byte[] value = getValueSerializer().serialize(user);
                return connection.setNX(key, value);
            }
        });
        return result;
    }

    public boolean putAll(final Collection<UserVO> userCollection) {
        if (CollectionUtils.isEmpty(userCollection))
            return true;
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                for (UserVO user : userCollection) {
                    byte[] key = getKeySerializer().serialize(user.getId());
                    byte[] value = getValueSerializer().serialize(user);
                    return connection.setNX(key, value);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    public void delete(final Long key) {
        redisTemplate.delete(key);
    }
}