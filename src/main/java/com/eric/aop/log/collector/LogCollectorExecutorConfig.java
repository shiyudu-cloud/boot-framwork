package com.eric.aop.log.collector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;

/**
 * @author shiyu.du
 * @describe
 * @date 2020/9/21
 **/
@Slf4j
@EnableAsync
@Configuration
@ComponentScan
public class LogCollectorExecutorConfig implements AsyncConfigurer {

    /**
     * 默认返回空的收集器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = LogCollector.class)
    public LogCollector logCollector(){
        return new NothingCollector();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex,method,params) -> log.error("LogCollectorExecutor execution Exception [method: " + method + " ,params: " + Arrays.toString(params) + " ]", ex);
    }


    /**
     * 配置日志收集器线程池
     * @return
     */
    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(2);
        threadPoolExecutor.setMaxPoolSize(4);
        threadPoolExecutor.setQueueCapacity(254);
        threadPoolExecutor.setThreadNamePrefix("LogCollectorAsyncExecutor-");
        threadPoolExecutor.setRejectedExecutionHandler((r,exec) ->{
            log.error("LogCollectorAsyncExecutor thread queue is full,activeCount:" + exec.getActiveCount() + ",Subsequent collection tasks will be rejected,please check your LogCollector or config your Executor");
        });
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }

}
