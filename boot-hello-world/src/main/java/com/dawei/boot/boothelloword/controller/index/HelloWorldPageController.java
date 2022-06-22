package com.dawei.boot.boothelloword.controller.index;

import com.dawei.boot.boothelloword.pojo.DemoPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by Dawei on 2019/3/12.
 * 用于页面返回的
 */
@Controller
@RequestMapping("/")
public class HelloWorldPageController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);


    /**
     * 返回首页视图
     */
    @RequestMapping(value = "/page/index")
    public String toIndexPage(Model model) {
        logger.info("To index page .... ");
        DemoPojo demoPojo = new DemoPojo();
        demoPojo.setUserName("ANC");
        model.addAttribute(demoPojo);
        return "/freemarker/index";
    }


    /**
     * 返回文件上传视图
     */
    @RequestMapping(value = "/page/upload")
    public String toUpLoadPage() {
        logger.info("To upload page .... ");
        return "/freemarker/upload";
    }



    /**
     * 项目根路径 跳转
     */
    @GetMapping(value = "")
    public String rootRequest() {
        logger.info("To login page .... ");
        return "/freemarker/login";
    }






}
