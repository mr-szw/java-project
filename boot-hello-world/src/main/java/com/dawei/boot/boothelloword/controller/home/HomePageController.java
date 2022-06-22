package com.dawei.boot.boothelloword.controller.home;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawei.boot.boothelloword.pojo.ResultDto;
import com.dawei.boot.boothelloword.pojo.UserInfo;
import com.dawei.boot.boothelloword.services.IUserInfoService;

/**
 * @author Dawei 2019/3/24
 */
@RestController
@RequestMapping(value = "/home")
public class HomePageController {

    @Resource
    private IUserInfoService userInfoService;

    /**
     * 首页信息
     */
    @GetMapping(value = "/page/info")
    public Object homePageInfertion() {
        ResultDto<String> resultDto = new ResultDto<>();

        UserInfo userInfoById = userInfoService.getUserInfoById("");
        resultDto.setSuccess();
        return resultDto;
    }


}
