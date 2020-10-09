package com.eric.aop.annotation;

import com.eric.aop.collector.LogCollector;
import com.eric.aop.collector.NothingCollector;
import org.springframework.http.HttpHeaders;

import java.lang.annotation.*;

/**
 * @author shiyu.du
 * @describe 收集日志注解
 * @date 2020/9/21
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiLog {

    /**
     * 仅当发生异常时记录收集
     * @return
     */
    boolean logOnError() default false;

    /**
     * 操作类型
     * @return
     */
    String type() default "undefined";

    /**
     * 记录的headers ,默认记录 content-type user-agent
     */
    String[] headers() default {HttpHeaders.USER_AGENT, HttpHeaders.CONTENT_TYPE};

    /**
     * 是否记录请求参数
     * @return
     */
    boolean args() default true;
    /**
     * 是否记录响应参数
     * @return
     */
    boolean responseBody() default true;
    /**
     *当目标发生异常时，是否追加异常堆栈信息到logData的content中
     * @return
     */
    boolean stackTraceOnError() default false;
    /**
     * 异步方式收集
     * @return
     */
    boolean asyncMode() default true;

    /**
     * 收集器
     */
    Class<? extends LogCollector> collector() default NothingCollector.class;

}
