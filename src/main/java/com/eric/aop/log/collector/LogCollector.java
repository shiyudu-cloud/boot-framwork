package com.eric.aop.log.collector;

import com.eric.aop.log.model.AopLogData;

/**
 * @author shiyu.du
 * @describe 日志收集器
 * @date 2020/9/21
 **/
@FunctionalInterface
public interface LogCollector {


    /**
     * 日志收集
     * @param logData
     */
    void collect(AopLogData logData);
}
