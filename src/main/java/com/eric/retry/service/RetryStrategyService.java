package com.eric.retry.service;

import com.eric.retry.prop.RetryPropertiesConfig;
import com.github.rholder.retry.*;
import com.google.common.base.Predicates;

import java.util.concurrent.TimeUnit;

/**
 * @author shiyu.du
 * @date 2020/10/13
 * @describe
 */
public class RetryStrategyService {

    private RetryPropertiesConfig retryPropertiesConfig;

    public RetryStrategyService(RetryPropertiesConfig retryPropertiesConfig){
        this.retryPropertiesConfig = retryPropertiesConfig;
    }

    public Retryer<Boolean> retry(){
        Retryer<Boolean> retry = RetryerBuilder.<Boolean>newBuilder()
                //retryIf 重试条件
                .retryIfException()
                .retryIfRuntimeException()
                .retryIfExceptionOfType(Exception.class)
                //返回false也需要重试
                .retryIfResult(Predicates.equalTo(retryPropertiesConfig.getRetryResult()))
                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(retryPropertiesConfig.getRetryTime(), TimeUnit.SECONDS))
                //停止策略 : 尝试请求6次
                .withStopStrategy(StopStrategies.stopAfterAttempt(retryPropertiesConfig.getStopTime()))
                //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                .build();
        return retry;
    }
}
