package com.mate.im.stream.producer;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.mate.im.stream.param.MessageBody;
import org.apache.rocketmq.common.message.MessageConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

public class StreamProducer {
    private static Logger logger = LoggerFactory.getLogger(StreamProducer.class);

    public static final int DELAY_LEVEL_1_M = 5;

    public static final int DELAY_LEVEL_2_M = 4;

    public static final String ROCKET_MQ_MESSAGE_ID = "ROCKET_MQ_MESSAGE_ID";

    public static final String ROCKET_TAGS = "ROCKET_TAGS";

    public static final String ROCKET_MQ_TOPIC = "ROCKET_MQ_TOPIC";

    @Autowired
    private StreamBridge streamBridge;

    /**
     * 发送消息，不额外设置消息头
     * @param bingingName
     * @param tag
     * @param message
     * @return
     */
    public boolean send(String bingingName, String tag, String message) {
        // 构建消息对象
        MessageBody messageBody = buildMessageBody(message);
        return getResult(messageBody, bingingName, tag, null);
    }

    /**
     * 发送延时消息
     * @param bingingName
     * @param tag
     * @param message
     * @param delayLevel RocketMQ支持18个级别的延时消息，分别对应1s、5s、10s、30s、1m、2m、3m、4m、5m、6m、7m、8m、9m、10m、20m、30m、1h、2h、3h。
     * @return
     */
    public boolean send(String bingingName, String tag, String message, int delayLevel) {
        MessageBody messageBody = buildMessageBody(message);
        return getResult(messageBody, bingingName, tag, new HashMap<>() {{ put(MessageConst.PROPERTY_SHARDING_KEY, delayLevel); }});
    }

    /**
     * 发送消息，自定义设置消息头（单个消息头自定义）
     * @param bingingName
     * @param tag
     * @param message
     * @param headerKey
     * @param headerValue
     * @return
     */
    public boolean send(String bingingName, String tag, String message, String headerKey, String headerValue) {
        MessageBody messageBody = buildMessageBody(message);
        return getResult(messageBody, bingingName, tag, new HashMap<>() {{ put(headerKey, headerValue); }});
    }

    /**
     * 发送消息，自定义设置消息头（多个消息头自定义）
     */
    public boolean send(String bingingName, String tag, String message, Map<String, Object> headers) {
        MessageBody messageBody = buildMessageBody(message);
        return getResult(messageBody, bingingName, tag, headers);
    }

    /**
     * 抽取构建消息体逻辑
     * @param message
     * @return
     */
    private MessageBody buildMessageBody(String message) {
        return new MessageBody()
                .setIdentifier(UUID.randomUUID().toString())
                .setBody(message);
    }

    /**
     * 抽取发送消息逻辑
     * @param messageBody
     * @param bingingName
     * @param tag
     * @param headers
     * @return
     */
    private boolean getResult(MessageBody messageBody, String bingingName, String tag, Map<String, Object> headers) {
        logger.info("Send message: {}, {}, {}", bingingName, tag, JSON.toJSONString(messageBody));
        MessageBuilder<MessageBody> middleObj = MessageBuilder.withPayload(messageBody).setHeader("TAGS", tag);
        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                switch (entry.getValue()) {
                    case String realHeaderValue -> middleObj.setHeader(entry.getKey(), realHeaderValue);
                    case Integer realHeaderValue -> middleObj.setHeader(entry.getKey(), realHeaderValue);
                    default -> {
                        middleObj.setHeader(entry.getKey(), entry.getValue().toString());
                        logger.warn("Send message header value type not have unique function, headerKey: {}, headerValue: {}", entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        boolean result = streamBridge.send(bingingName, middleObj.build());
        logger.info("Send result: {}, {}, {}", bingingName, tag, result);
        return result;
    }
}
