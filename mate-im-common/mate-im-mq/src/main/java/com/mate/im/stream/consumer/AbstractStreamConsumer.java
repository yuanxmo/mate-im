package com.mate.im.stream.consumer;

import com.alibaba.fastjson.JSON;
import com.mate.im.stream.param.MessageBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import static com.mate.im.stream.producer.StreamProducer.ROCKET_MQ_MESSAGE_ID;
import static com.mate.im.stream.producer.StreamProducer.ROCKET_TAGS;

/**
 * MQ 消费基类
 *
 * @author yuanxmo
 */
@Slf4j
public class AbstractStreamConsumer {

    /**
     * 从 message 中解析出消息对象
     * @param message
     * @param type
     * @return
     * @param <T>
     */
    public static <T> T getMessage(Message<MessageBody> message, Class<T> type) {
        String messageId = message.getHeaders().get(ROCKET_MQ_MESSAGE_ID, String.class);
        String tag = message.getHeaders().get(ROCKET_TAGS, String.class);
        String topic = message.getHeaders().get("ROCKET_TOPIC", String.class);
        Object object = JSON.parseObject(message.getPayload().getBody(), type);
        log.info(
                "Received message topic: {} messageId: {}, object: {}, tag: {}",
                topic, messageId, JSON.toJSONString(object), tag
        );
        return (T) object;
    }
}
