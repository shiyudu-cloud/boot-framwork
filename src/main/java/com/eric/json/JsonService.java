package com.eric.json;


/**
 * @author shiyu.du
 * @date 2020/10/13
 * @describe
 */
public interface JsonService {


    Object string2JsonObj(String json);


    String jsonObj2String(Object json);

    Object string2JsonArr(String json);

    String jsonArr2String(Object jsonArr);

    Object json2Bean(String json,Class<?> clazz);

    String bean2Json(Object o);

}
