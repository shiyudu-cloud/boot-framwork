package com.eric.json.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eric.json.JsonService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author shiyu.du
 * @date 2020/10/13
 * @describe fastJson 工具类
 */
@Service
public class FastJsonServiceImpl implements JsonService {


    /**
     * json 字符串转换成 jsonObject
     * @param json
     * @return
     */
    @Override
    public Object string2JsonObj(String json) {
        Assert.notNull(json,"json not null");
        return JSON.parseObject(json);
    }
    /**
     *  Object 转换成 json字符串
     * @param json
     * @return
     */
    @Override
    public String jsonObj2String(Object json) {
        Assert.notNull(json,"object not null");
        return JSON.toJSONString(json);
    }


    /**
     * Json 转换成json array
     * @param json
     * @return JSONArray
     */
    @Override
    public Object string2JsonArr(String json) {
        Assert.notNull(json,"json not null");
        return JSON.parseArray(json);
    }

    @Override
    public String jsonArr2String(Object jsonArr) {
        Assert.notNull(jsonArr,"object not null");
        return JSON.toJSONString(jsonArr);
    }

    @Override
    public Object json2Bean(String json, Class<?> clazz) {
        Assert.notNull(json,"json not null");
        return JSONObject.parseObject(json,clazz);
    }

    @Override
    public String bean2Json(Object o) {
        return JSON.toJSONString(o);
    }
}
