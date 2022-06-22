package com.dawei.boot.boothelloword.controller.redis;

import com.dawei.boot.boothelloword.redis.RedisChannelSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Dawei on 2019/8/22.
 */
@RestController
@RequestMapping("/redis")
public class RedisMessageController {

    private static final Logger logger = LoggerFactory.getLogger(RedisMessageController.class);

    //redis 消息push 广播服务
    //@Resource
    private RedisChannelSender redisChannelSender;

    @RequestMapping("/push")
    public String pushSendMessageByTopic(String topic, String messageBody) {
        logger.info("Push Send Message By Topic={} messageBody={}", topic, messageBody);
        redisChannelSender.pushMessageByChannel(topic, messageBody);
        return (" Hello world!");
    }




}
