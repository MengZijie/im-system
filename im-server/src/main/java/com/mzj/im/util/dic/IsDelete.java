package com.mzj.im.util.dic;

/**
 * Created by OB on 2017/2/7.
 */
public enum IsDelete {

    YES('Y'), NO('N');

    private char value;

    public char getValue() {
        return value;
    }

    IsDelete(char value) {
        this.value = value;
    }
}
