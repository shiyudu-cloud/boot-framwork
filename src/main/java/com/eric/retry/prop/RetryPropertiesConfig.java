package com.eric.retry.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author shiyu.du
 * @date 2020/10/13
 * @describe
 */
@ConfigurationProperties("common.retry")
public class RetryPropertiesConfig {

    /**
     * 返回false重试 默认为 false
     *
     */
    private Boolean retryResult = false;
    /**
     * 多少秒重试一次 ， 单位 （s） 默认为 5
     */
    private Integer retryTime = 5;

    /**
     * 重试次数 默认是 10
     */
    private Integer stopTime = 10;

    public Boolean getRetryResult() {
        return retryResult;
    }

    public void setRetryResult(Boolean retryResult) {
        this.retryResult = retryResult;
    }

    public Integer getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime) {
        this.retryTime = retryTime;
    }

    public Integer getStopTime() {
        return stopTime;
    }

    public void setStopTime(Integer stopTime) {
        this.stopTime = stopTime;
    }
}
