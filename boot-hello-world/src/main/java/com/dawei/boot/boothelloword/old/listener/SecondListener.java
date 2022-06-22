package com.dawei.boot.boothelloword.old.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author sinbad on 2020/4/22.
 */
@WebListener
public class SecondListener implements ServletContextListener {


	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		System.out.println(contextEvent.getServletContext().getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {

	}
}
