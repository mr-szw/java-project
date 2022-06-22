package com.dawei.boot.boothelloword.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dawei.boot.boothelloword.old.listener.SecondListener;
import com.dawei.boot.boothelloword.old.servlet.SecondServlet;

/**
 * @author sinbad on 2020/4/22.
 */
@Configuration
public class ListenerConfig {


	@Bean
	public ServletListenerRegistrationBean getServletListenerRegistrationBean() {
		ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean<>(new SecondListener());
		return bean;
	}
}
