package com.mzj.im.util;

import com.mzj.im.model.po.UserPO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by OB on 2017/2/4.
 */
public final class PasswordUtil {

    public static String encodePassword(byte[] password, byte[] salt) {
        String rePassword = new Md5Hash(password, salt, 10).toBase64();
        return rePassword;
    }

    public static void setPassword(UserPO user) {
        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setSalt(new Date().toString() + user.getUsername());
        }
        user.setPassword(encodePassword(user.getPassword().getBytes(), user.getSalt().getBytes()));
    }
}
