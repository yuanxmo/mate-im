package com.mate.im.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication(scanBasePackages = "com.mate.im.user")
public class MateIMUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(MateIMUserApplication.class, args);
    }
}
