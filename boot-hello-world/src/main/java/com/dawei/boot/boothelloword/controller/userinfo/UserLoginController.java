package com.dawei.boot.boothelloword.controller.userinfo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dawei.boot.boothelloword.constants.CommentConstants;
import com.dawei.boot.boothelloword.pojo.DemoPojo;
import com.dawei.boot.boothelloword.pojo.UserInfo;
import com.dawei.boot.boothelloword.utils.GsonUtil;
import com.dawei.boot.boothelloword.utils.VerifyCodeUtil;


/**
 * @author Dawei 2019/3/14
 */
@Controller
@RequestMapping(value = "/user")
public class UserLoginController {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    /**
     *  登陆主接口
     */
    @PostMapping(value = "/login")
    public String userLogin(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(System.currentTimeMillis());
        session.setAttribute("userInfo", GsonUtil.toJson(userInfo));
        DemoPojo demoPojo = new DemoPojo();
        demoPojo.setUserName("ABC");
        request.setAttribute("demoPojo", demoPojo);

        //重定向 redirect 客户端的二次请求 || 转发 : forward  服务器内部操作
        return "redirect:/home/page/info";
    }


    /**
     * 返回登陆视图
     */
    @GetMapping(value = {"/login", ""})
    public String toUserLogin() {
        logger.info("To user login  page .... ");
        return "/freemarker/login";
    }

    /**
     * 获取验证码
     */
    @GetMapping(value = "/login/code")
    public void getIdentifyingCode(HttpServletRequest request, HttpServletResponse response) {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(CommentConstants.VALIDATE_CODE, verifyCode);
        logger.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
        //写给浏览器
        try {
            ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            logger.error("Failed to out put image --- ");
        }
    }






}
