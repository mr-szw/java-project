//package com.dawei.boot.boothelloword.redis.receiver;
//
//import java.nio.charset.StandardCharsets;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.stereotype.Component;
//
//import com.dawei.boot.boothelloword.utils.GsonUtil;
//
///**
// * @author by Dawei on 2019/4/30.
// * Redis 消息监听
// */
////@Component
//public class RedisChannelListener implements MessageListener {
//
//    private static final Logger logger = LoggerFactory.getLogger(RedisChannelListener.class);
//    /**
//     * 监听消息内容
//     * @param message 传送的消息 包含消息渠道和消息体
//     * @param pattern 订阅模式
//     */
//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        String patternType = new String(pattern, StandardCharsets.UTF_8);
//        logger.info("get message info message is ={} patternType={}", GsonUtil.toJson(message), patternType);
//
//        byte[] channel = message.getChannel();
//
//        byte[] body = message.getBody();
//
//        String channelName = new String(channel, StandardCharsets.UTF_8);
//
//
//
//    }
//}
