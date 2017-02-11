package com.mzj.im.util.dic;

/**
 * Created by OB on 2017/2/7.
 */
public enum OperateResult {
    SUCCESS                 ("000000", "成功"),
    NOT_ALL_SUCCESS         ("100000", "没有全部成功"),
    FAILD                   ("200000", "失败"),
    INPUT_ILLIGAL_ERROR     ("310000", "输入错误");

    private String code;

    private String info;

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    OperateResult(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
