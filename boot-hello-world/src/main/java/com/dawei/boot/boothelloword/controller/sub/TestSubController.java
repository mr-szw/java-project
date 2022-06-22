package com.dawei.boot.boothelloword.controller.sub;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawei.boot.boothelloword.services.sub.SubClass;

/**
 * @author Dawei 2019/3/13
 * 文件上传
 */
@RestController
@RequestMapping("/api/test/extends")
public class TestSubController {

    private static final Logger logger = LoggerFactory.getLogger(TestSubController.class);


    @Resource
    private SubClass subClass;

    @GetMapping("/test/sub")
    public String test() {
        logger.info("To up load file ....");
        subClass.printName();
        return "resultDto";
    }



}
