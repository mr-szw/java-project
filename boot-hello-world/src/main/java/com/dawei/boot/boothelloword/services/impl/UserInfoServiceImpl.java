package com.dawei.boot.boothelloword.services.impl;

import com.dawei.boot.boothelloword.pojo.UserInfo;
import com.dawei.boot.boothelloword.services.IUserInfoService;
import javax.annotation.Resource;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author by Dawei on 2019/3/22. 用户信息操作服务
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

//    @Resource
//    private SchedulerJobMapper schedulerJobMapper;

    //Redis String K-V template
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //@Resource
    private ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;


    private RedisKeyValueTemplate redisKeyValueTemplate;
    /**
     * 通过登陆信息获取用户信息
     *
     * @param loginName 登陆名
     * @return 用户信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserInfo getUserInfoById(String loginName) {

        String redisKey = "Boot:Test:Template:key:1";
        stringRedisTemplate.opsForValue().set(redisKey, "Key1");
        stringRedisTemplate.delete(redisKey);
        stringRedisTemplate.opsForList().leftPush(redisKey, "Value", "Key1");
        stringRedisTemplate.delete(redisKey);
        stringRedisTemplate.opsForSet().add(redisKey, "Key1");
        stringRedisTemplate.delete(redisKey);
        stringRedisTemplate.opsForZSet().add(redisKey, "Key1",  10.0);
        stringRedisTemplate.delete(redisKey);
        stringRedisTemplate.opsForHash().put(redisKey, "Key1",  "Key2");
        stringRedisTemplate.delete(redisKey);


        //发布订阅
        stringRedisTemplate.convertAndSend("ListenerTopicA", "MessageBody");
        stringRedisTemplate.convertAndSend("ListenerTopicB", "MessageBody");
        stringRedisTemplate.convertAndSend("ListenerTopicC", "MessageBody");

        //reactiveRedisTemplate.convertAndSend(redisKey, "MessageBody");


        String value = stringRedisTemplate.opsForValue().get(redisKey);


        return null;
    }

}
