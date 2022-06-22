package com.dawei.boot.boothelloword.redis;

/**
 * @author by Dawei on 2019/8/22.
 */
public interface RedisChannelSender {


    /**
     * 通过主题号发送消息
     * @param topic 主题号
     * @param messageBody 消息体
     */
    default void pushMessageByChannel(String topic, Object messageBody) {}


}
