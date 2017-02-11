package com.mzj.im.util;

import com.alibaba.fastjson.JSONObject;
import com.mzj.im.util.dic.OperateResult;

/**
 * Created by OB on 2017/2/7.
 */
public class ResponseObject<T> {

    private OperateResult result;
    private T data;

    public ResponseObject() {
        result = null;
        data = null;
    }

    public ResponseObject(OperateResult result, T data) {
        this.result = result;
        this.data = data;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("resultCode", result.getCode());
        json.put("resultInfo", result.getInfo());
        json.put("data", data);
        return json;
    }

    public OperateResult getResult() {
        return result;
    }

    public void setResult(OperateResult result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
