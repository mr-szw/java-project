//package com.dawei.boot.boothelloword.config;
//
//import com.dawei.boot.boothelloword.redis.receiver.RedisChannelListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * @author by Dawei on 2019/4/30.
// */
////@Configuration
//public class BootRedisConfiguration {
//
//
//    /**
//     * 对消息体进行序列化工作
//     * 默认采用 : StringRedisSerializer
//     * StringRedisSerializer 继承自  RedisSerializer
//     * 然而RedisSerializer  默认采用JdkSerializationRedisSerializer
//     *
//     */
//    @Bean
//    public MessageListenerAdapter listenerAdapter() {
//        //初始化消息接收处理器
//        MessageListenerAdapter messageListenerAdapter =
//            new MessageListenerAdapter(
//                //自己实现的监听器
//                new RedisChannelListener()
//            );
//        //设置序列化方式 采用默认形式
//        messageListenerAdapter.setSerializer(new StringRedisSerializer());
//        return messageListenerAdapter;
//    }
//
//
//    /**
//     * 初始化redis容器 适配监听处理器
//     * @param connectionFactory 链接工厂
//     */
//    @Bean
//    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        //配置多个监听适配器
//        container.addMessageListener(listenerAdapter(), new PatternTopic("ListenerTopicA"));
//        container.addMessageListener(listenerAdapter(), new PatternTopic("ListenerTopicB"));
//        container.addMessageListener(listenerAdapter(), new PatternTopic("ListenerTopicC"));
//        return container;
//    }
//
//}
