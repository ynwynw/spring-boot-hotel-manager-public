package com.example.hotel.core.common.page;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-12 13:23
 * @Description: 统一的json返回格式
 */
@Data
public class JsonData {

    private boolean ret; // 返回结果

    private String msg; // 异常信息

    private Object data; // 正常返回数据

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    public static JsonData success() {
        return new JsonData(true);

    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
