package com.mzj.im.web.filter.shiro;

import com.mzj.im.util.ObjectUtil;
import com.mzj.im.util.PasswordUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by OB on 2017/2/4.
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

//    private Integer maxRetryNum;
//
//    public void setMaxRetryNum(Integer maxRetryNum) {
//        this.maxRetryNum = maxRetryNum;
//    }

    private Cache<String, AtomicInteger> passwordRetryCache;

    public CustomCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    /**
     * @param token 请求的验证信息
     * @param info  数据库中取出的验证信息
     * @return 是否验证通过
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        // 从cache里面查询已经登陆的次数，超过五次则报异常
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);

        if (ObjectUtil.isNull(retryCount)) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }

        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }

        Object tokenCredentials = getCredentials(token);
        Object accountCredentials = getCredentials(info);
        byte[] salt = ((SimpleAuthenticationInfo) info).getCredentialsSalt().getBytes();
        byte[] encodedPassword = PasswordUtil.encodePassword(tokenCredentials.toString().getBytes(), salt).getBytes();
        boolean result = equals(encodedPassword, accountCredentials);
        if (result) {
            passwordRetryCache.remove(username);
        }
        return result;
    }
}
