package com.dawei.boot.boothelloword.controller.index;

import com.dawei.boot.boothelloword.annotation.OperationCutPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Dawei on 2019/3/12.
 */
@RestController
@OperationCutPoint
@RequestMapping("/hello")
public class HelloWorldController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/world")
    public String sayHelloWorld(String name) {

        logger.info("Your name is ={}", name);
        return (name + " Hello world!");
    }

    @RequestMapping("/world/{id}")
    public String sayHelloWorld(@PathVariable Long id) {

        logger.info("Your id is ={}", id);
        return (id + " Hello world!");
    }
}
