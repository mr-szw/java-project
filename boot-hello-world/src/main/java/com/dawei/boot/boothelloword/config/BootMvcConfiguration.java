package com.dawei.boot.boothelloword.config;

import com.dawei.boot.boothelloword.interceptors.AllRunInterceptor;
import com.dawei.boot.boothelloword.interceptors.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Dawei 2019/3/14
 * Boot 用于配置Mvc属性所使用的类
 */
@Configuration
public class BootMvcConfiguration implements WebMvcConfigurer {


    //拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
         * 配置登陆校验拦截器
         * 拦截除了游客访问和登陆的所有請求
         */
//        registry.addInterceptor(new UserLoginInterceptor())
//                .addPathPatterns("/**/**")
//                //开后门： 登陆，游客，根目录（登陆)，验证码
//                .excludePathPatterns("/user/login", "/**/guest/**", "/", "/user/login/code")
//                .excludePathPatterns("/home/page/info")
//                .excludePathPatterns("/**/test/**");

        //全局拦截 用于通用配置
        registry.addInterceptor(new AllRunInterceptor()).addPathPatterns("/**/**");
    }

    //跨域访问配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")      //允许的请求路径
            .allowedOrigins("http://hello-world.com")  //允许的来源域名
            .allowedMethods("POST", "GET");            //允许的请求方法
    }

    //格式化
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //将请求中的 字符串时间 转换为Date 类型
        //registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    //URI 到视图的映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //对于请求 index.html 将返回视图index.ftl
        registry.addViewController("/index.html").setViewName("/index.ftl");
        //将index.do 请求重定向到index.ftl界面
        registry.addRedirectViewController("/index.do","/index.ftl");
    }
}
