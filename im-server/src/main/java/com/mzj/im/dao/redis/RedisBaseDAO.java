package com.mzj.im.dao.redis;

import com.mzj.im.util.ClassUtil;
import com.mzj.im.util.ObjectUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by ob on 17-3-17.
 */
public class RedisBaseDAO<K, V> implements InitializingBean {

    private Class<K> key;
    private Class<V> value;

    private Class<K> getKey() {
        if (ObjectUtil.isNull(key)) {
            key = (Class<K>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return key;
    }

    private Class<V> getValue() {
        if (ObjectUtil.isNull(value)) {
            value = (Class<V>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return value;
    }

    @Autowired
    private RedisTemplate<String, V> redisTemplate;

    public ValueOperations<String, V> valueOperations;

    public HashOperations<String, K, V> hashOperations;

    public ListOperations<String, V> listOperations;

    public SetOperations<String, V> setOperations;

    /**
     * get value
     *
     * @param key
     * @return
     */
    public V get(String key) {
        return valueOperations.get(key);
    }

    /**
     * set key & value
     *
     * @param key
     * @param value
     */
    public void set(String key, V value) {
        valueOperations.set(key, value);
    }

    public void set(Map<String, V> map) {
        valueOperations.multiSet(map);
    }

    /**
     * key delete
     *
     * @param key
     */
    public void delete(String key) {
        getRedisTemplate().delete(key);
    }

    /**
     * key exist
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return getRedisTemplate().hasKey(key);
    }

    /**
     * key expire
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return getRedisTemplate().expire(key, timeout, unit);
    }

    /**
     * redistemplate是全局唯一的，子类不要出现对redistemplate的成员变量的设置(比如keyserializer,)
     *
     * @return
     */
    RedisTemplate<String, V> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 当需要更改serializer,可以直接通过connection.set等方法实现
     *
     * @param callback
     * @return
     */
    public <T> T execute(RedisCallback<T> callback) {
        return redisTemplate.execute(callback);
    }

    /**
     * 获取stringserializer
     */
    public RedisSerializer<String> getStringSerializer() {
        return redisTemplate.getStringSerializer();
    }

    /**
     * 获取JdkSerializationRedisSerializer
     */
    @SuppressWarnings("unchecked")
    public <T> RedisSerializer<T> getDefaultSerializer() {
        return (RedisSerializer<T>) redisTemplate.getDefaultSerializer();
    }

    /**
     * 获取stringserializer
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public RedisSerializer<String> getKeySerializer() {
        return (RedisSerializer<String>) redisTemplate.getKeySerializer();
    }

    /**
     * 获取jackson2jsonredisserializer
     *
     * @return
     */
    public RedisSerializer<V> getValueSerializer() {
        return (RedisSerializer<V>) redisTemplate.getValueSerializer();
    }

    /**
     * 获取jackson2jsonredisserializer
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public RedisSerializer<K> getHashKeySerializer() {
        return (RedisSerializer<K>) redisTemplate.getHashKeySerializer();
    }

    /**
     * 获取jackson2jsonredisserializer
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public RedisSerializer<V> getHashValueSerializer() {
        return (RedisSerializer<V>) redisTemplate.getHashValueSerializer();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (getKey() == null || getValue() == null) {
            throw new IllegalArgumentException("获取泛型class失败");
        }
        //
        valueOperations = redisTemplate.opsForValue();
        hashOperations = redisTemplate.opsForHash();
        listOperations = redisTemplate.opsForList();
        setOperations = redisTemplate.opsForSet();
        //
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<V>(getValue()));
        redisTemplate.setHashKeySerializer(new Jackson2JsonRedisSerializer<K>(getKey()));
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<V>(getValue()));
    }

}
