package com.eric.retry.conf;

import com.eric.retry.prop.RetryPropertiesConfig;
import com.eric.retry.service.RetryStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shiyu.du
 * @date 2020/10/13
 * @describe
 */

@Configuration
@ConditionalOnClass(RetryStrategyService.class)
@EnableConfigurationProperties(RetryPropertiesConfig.class)
public class RetryConfig {

    @Autowired
    private RetryPropertiesConfig retryPropertiesConfig;



    @Bean
    @ConditionalOnBean
    public RetryStrategyService retryStrategyService(){
        return new RetryStrategyService(retryPropertiesConfig);
    }

}
