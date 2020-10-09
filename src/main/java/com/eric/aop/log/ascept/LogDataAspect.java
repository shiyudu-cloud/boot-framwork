package com.eric.aop.log.ascept;

import com.eric.aop.log.model.AopLogData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author shiyu.du
 * @describe 日志切面
 * EnableAspectJAutoProxy(exposeProxy=true) 标注true使用Cglib
 * @date 2020/9/22
 **/
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy=true)
public class LogDataAspect {

    @Resource
    private AopLogProcessor aopLogProcessor;

    @Pointcut("@annotation(com.eric.aop.log.annotation.ApiLog) || @within(com.eric.aop.log.annotation.ApiLog)")
    public void aopLogPointCut(){}

    @Around("aopLogPointCut()")
    public Object note(ProceedingJoinPoint point) throws Throwable {
        return this.aopLog(point);
    }

    public Object aopLog(ProceedingJoinPoint point) throws Throwable {
        try {
            AopLogData.removeCurrent();
            AopLogData aopLogData=AopLogData.getCurrent();
            return aopLogProcessor.proceed(aopLogData,point);
        }finally {
            AopLogData.removeCurrent();
        }

    }
}
