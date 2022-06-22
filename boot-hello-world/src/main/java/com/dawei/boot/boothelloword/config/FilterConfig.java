package com.dawei.boot.boothelloword.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dawei.boot.boothelloword.old.filter.SecondFilter;
import com.dawei.boot.boothelloword.old.servlet.SecondServlet;

/**
 * @author sinbad on 2020/4/22.
 */
@Configuration
public class FilterConfig {


	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new SecondFilter());
		bean.addUrlPatterns("/second");
		return bean;
	}
}
