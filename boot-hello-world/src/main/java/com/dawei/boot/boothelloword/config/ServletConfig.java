package com.dawei.boot.boothelloword.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dawei.boot.boothelloword.old.servlet.SecondServlet;

/**
 * @author sinbad on 2020/4/22.
 */
@Configuration
public class ServletConfig {


	@Bean
	public ServletRegistrationBean getServletRegistrationBean() {
		ServletRegistrationBean bean = new ServletRegistrationBean<>(new SecondServlet());
		bean.addUrlMappings("/second");
		return bean;
	}
}
