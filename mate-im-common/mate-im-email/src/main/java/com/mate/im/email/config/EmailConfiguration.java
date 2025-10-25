package com.mate.im.email.config;

import com.mate.im.email.EmailService;
import com.mate.im.email.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件配置类
 *
 * @author yuanxmo
 */
@Slf4j
@Configuration
@EnableConfigurationProperties
public class EmailConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public EmailService emailService() {
        return new EmailServiceImpl();
    }
}