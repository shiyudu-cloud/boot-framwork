package com.eric.aop.log.ascept;

import com.eric.aop.log.annotation.ApiLog;
import com.eric.aop.log.collector.LogCollectorExecutor;
import com.eric.aop.log.model.AopLogData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author shiyu.du
 * @describe
 * @date 2020/9/22
 **/
@Slf4j
@Component
public class AopLogProcessor {

    private final LogCollectorExecutor logCollectorExecutor;


    public AopLogProcessor(LogCollectorExecutor logCollectorExecutor) {
        this.logCollectorExecutor=logCollectorExecutor;
    }
    /**
     * 处理 日志数据切面
     *
     * @param logData  日志数据
     * @param proceedingJoinPoint 切入point对象
     * @return 返回执行结果
     * @throws Throwable Exceptions in AOP should be thrown out and left to the specific business to handle
     */
    public Object proceed(AopLogData logData, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature= (MethodSignature)proceedingJoinPoint.getSignature();
        ApiLog apiLog=signature.getMethod().getAnnotation(ApiLog.class);
        if (ObjectUtils.isEmpty(apiLog)){
            apiLog =  proceedingJoinPoint.getTarget().getClass().getAnnotation(ApiLog.class);
        }
        if (!ObjectUtils.isEmpty(apiLog)){
            if (!apiLog.logOnError()){
                log.info("Log before recording method execution .... ");
                logProcessBefore(logData,apiLog,proceedingJoinPoint);
            }
            return this.proceed(logData,apiLog,proceedingJoinPoint);
        }
        return proceedingJoinPoint.proceed();

    }

    /**
     * 执行前记录 app应用信息 http等信息
     * @param logData 注解对象
     * @param apiLog   日志数据
     * @param point  切入point对象
     */
    public void logProcessBefore(AopLogData logData,ApiLog apiLog,ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature)point.getSignature();
        logData.setType(apiLog.type());
        logData.setMethod(signature.getDeclaringTypeName()+"#" + signature.getName()+"()");
        LogDataExtractor.logHttpRequest(logData,apiLog.headers());
        if (apiLog.args()){
            logData.setArgs(LogDataExtractor.getArgs(signature.getParameterNames(),point.getArgs()));
        }
    }

    /**
     * 方法执行处理记录
     *
     * @param apiLog 注解对象
     * @param logData   日志数据
     * @param point  切入point对象
     * @return 返回执行结果
     * @throws Throwable Exceptions in AOP should be thrown out and left to the specific business to handle
     */
    public Object proceed(AopLogData logData,ApiLog apiLog,ProceedingJoinPoint point) throws Throwable {
        try {
            Object result=point.proceed();
            if (apiLog.responseBody()){
                logData.setRespBody(LogDataExtractor.getResult(result));
            }
            logData.setSuccess(true);
            return result;
        }catch (Throwable throwable){
            if (apiLog.logOnError()){
                this.logProcessBefore(logData,apiLog,point);
            }
            logData.setSuccess(true);
            if (apiLog.stackTraceOnError()){
                try (StringWriter sw = new StringWriter(); PrintWriter writer = new PrintWriter(sw, true)) {
                    throwable.printStackTrace(writer);
                    logData.step("Fail : \n" + sw.toString());
                }
            }
            throw throwable;
        }
        finally {
            logData.toCostTime();
            AopLogData.setCurrent(logData);
            if (!apiLog.logOnError() || (apiLog.logOnError() && !logData.getSuccess())){
                if(apiLog.asyncMode()){
                    logCollectorExecutor.asyncExecute(apiLog.collector(),logData);
                } else {
                    logCollectorExecutor.execute(apiLog.collector(),logData);
                }
            }
        }

    }


}
