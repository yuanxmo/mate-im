package com.mate.im.stream.config;

import com.mate.im.stream.producer.StreamProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanxmo
 */
@Configuration
public class StreamConfiguration {
    @Bean
    public StreamProducer streamProducer() {
        return new StreamProducer();
    }
}
