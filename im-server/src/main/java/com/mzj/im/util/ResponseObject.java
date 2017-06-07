package com.mzj.im.util;

import com.mzj.im.util.dic.OperateResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Collection;

/**
 * Created by OB on 2017/2/7.
 */
public class ResponseObject<T> {
    private enum Status {

        SUCCESS("success"),
        FAILD("faild");

        private String status;

        public String getStatus() {
            return status;
        }

        private Status(String status) {
            this.status = status;
        }
    }

    private Status status;
    private T data;

    public String toJSONString() {
        JSONObject result = new JSONObject();
        result.put("status", status.getStatus());
        if(status == Status.FAILD) {
            result.put("data", null);
            return result.toString();
        }
        if (data instanceof Collection) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.addAll((Collection) data);
            result.put("data", jsonArray);
        }
        else if (data instanceof ToJSON) {
            result.put("data", ((ToJSON) data).toJSON());
        }
        else if (data instanceof String) {
            result.put("data", data);
        }
        return result.toString();
    }

    public ResponseObject<T> setSuccess() {
        this.status = Status.SUCCESS;
        return this;
    }

    public ResponseObject<T> setFaild() {
        this.status = Status.FAILD;
        return this;
    }

    public ResponseObject<T> setData(T data) {
        this.data = data;
        return this;
    }
}
