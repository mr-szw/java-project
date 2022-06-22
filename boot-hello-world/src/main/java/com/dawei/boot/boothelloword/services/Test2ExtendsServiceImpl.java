package com.dawei.boot.boothelloword.services;

import org.springframework.stereotype.Service;

/**
 * @author by Dawei on 2019/3/22. 用户信息操作服务
 */
@Service
public class Test2ExtendsServiceImpl implements TestExtendsService {


    @Override
    public String printName() {
        System.out.println("Test---2");
        return "Test---2";
    }


}
