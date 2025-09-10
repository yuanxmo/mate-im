package com.mate.im.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mate.im.gateway")
public class MateIMGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MateIMGatewayApplication.class, args);
    }
}
