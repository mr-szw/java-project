package com.dawei.boot.boothelloword.old.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注解方式设置servlet
 * @author sinbad on 2020/4/22.
 */
@WebServlet(name = "first-servlet", urlPatterns = "/*")
public class FirstServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Do get");
		super.doGet(req, resp);

	}
}
