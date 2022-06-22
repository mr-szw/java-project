//package com.dawei.boot.boothelloword.redis.sender;
//
//
//import com.dawei.boot.boothelloword.redis.RedisChannelSender;
//import com.dawei.boot.boothelloword.utils.GsonUtil;
//
//import javax.annotation.Resource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
///**
// * @author by Dawei on 2019/8/22.
// */
////@Service
//public class RedisChannelSenderImpl implements RedisChannelSender {
//
//    private static final Logger logger = LoggerFactory.getLogger(RedisChannelSenderImpl.class);
//
//    //Redis String K-V template
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//    /**
//     * 通过主题号发送消息
//     * @param topic 主题号
//     * @param messageBody 消息体
//     */
//    @Override
//    public void pushMessageByChannel(String topic, Object messageBody) {
//        logger.info("To push Message By Channel, topic={}, messageBody={}", topic, GsonUtil.toJson(messageBody));
//        try {
//            stringRedisTemplate.convertAndSend(topic, messageBody);
//            logger.error("covert message success !!! ");
//        } catch (Exception e) {
//            logger.error("covert message failed, e=", e);
//        }
//    }
//
//
//}
