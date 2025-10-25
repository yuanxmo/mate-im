package com.mate.im.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.mate.im.auth"})
public class MateIMAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(MateIMAuthApplication.class, args);
    }
}
