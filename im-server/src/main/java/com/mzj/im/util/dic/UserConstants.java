package com.mzj.im.util.dic;

/**
 * Created by OB on 2017/2/5.
 */
public enum UserConstants {

    CURRENT_USER("user");

    private String value;


    UserConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
