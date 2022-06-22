package com.dawei.boot.boothelloword.services;

import org.springframework.stereotype.Service;

import com.dawei.boot.boothelloword.services.sub.FatherClass;

/**
 * @author by Dawei on 2019/3/22. 用户信息操作服务
 */
@Service
public class Test1ExtendsServiceImpl extends FatherClass implements TestExtendsService {


    @Override
    public String printName() {
        System.out.println("Test---1");
        return "Test---1";
    }


}
