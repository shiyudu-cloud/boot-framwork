package com.eric.aop.collector;

import com.eric.aop.model.AopLogData;

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
