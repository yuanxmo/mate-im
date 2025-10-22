package com.mate.im.stream.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息
 *
 * @author yuanxmo
 */
@Data
@Accessors(chain = true)
public class Message {
    /**
     * 消息 id
     */
    private String messageId;

    /**
     * 消息体
     */
    private String body;
}
