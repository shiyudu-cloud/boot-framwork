package com.eric.aop.log.collector;

import com.eric.aop.log.model.AopLogData;

/**
 * @author shiyu.du
 * @describe
 * @date 2020/9/21
 **/
public class NothingCollector implements LogCollector{


    @Override
    public void collect(AopLogData logData) {
        //this is a empty collector will do nothing
    }
}
