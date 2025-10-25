package com.mate.im.notice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuanxmo
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = "com.mate.im.notice")
public class MateIMNoticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MateIMNoticeApplication.class, args);
    }
}
