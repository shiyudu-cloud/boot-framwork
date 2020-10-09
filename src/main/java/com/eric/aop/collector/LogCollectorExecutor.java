package com.eric.aop.collector;

import com.eric.aop.model.AopLogData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shiyu.du
 * @describe
 * @date 2020/9/21
 **/
@Slf4j
@Component
public class LogCollectorExecutor {

    @Resource
    private LogCollector collector;

    private Map<Class<? extends LogCollector>, LogCollector> collectors = new HashMap<>();


    private ApplicationContext applicationContext;

    public LogCollectorExecutor(@Autowired ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * AsyncMode log collection 异步模式日志收集
     *
     * @param clz     日志收集器Class对象
     * @param logData 日志数据
     */
    @Async
    public void asyncExecute(Class<? extends LogCollector> clz, AopLogData logData) {
        execute(clz, logData);
    }

    /**
     * 同步模式收集日志
     *
     * @param clz     日志收集器Class对象
     * @param logData 日志数据
     */
    public void execute(Class<? extends LogCollector> clz, AopLogData logData) {
        getExecuteLogCollector(clz).collect(logData);
    }

    /**
     * Get the specified log collector 获取指定的日志收集器
     *
     * @param clz 日志收集器Class对象
     * @return 获取指定的日志收集器
     */
    private LogCollector getExecuteLogCollector(Class<? extends LogCollector> clz) {
        if (clz != NothingCollector.class) {
            LogCollector c;
            try {
                c = applicationContext.getBean(clz);
            } catch (Exception e) {
                c = collectors.get(clz);
                if (c == null) {
                    c = BeanUtils.instantiateClass(clz);
                    collectors.put(clz, c);
                }
            }
            return c;
        } else {
            return collector;
        }
    }
}
