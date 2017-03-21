package com.mzj.im.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Created by ob on 17-3-17.
 */
public class RedisBaseDAO <K, V> {

    @Autowired(required = true)
    protected RedisTemplate<K, V> redisTemplate;

    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    protected RedisSerializer<K> getKeySerializer(){
        return (RedisSerializer<K>) redisTemplate.getKeySerializer();
    }

    protected RedisSerializer<V> getValueSerializer(){
        return (RedisSerializer<V>) redisTemplate.getValueSerializer();
    }
}
