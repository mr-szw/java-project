package com.dawei.boot.boothelloword.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sinbad on 2020/4/22.
 */
@Controller
@RequestMapping(value = "/helloworld/page")
public class PageController {





	@GetMapping(value = "/page")
	public String showIndexPage() {
		return "/views/indexPage.html";
	}

	@GetMapping(value = "/upload")
	public String toUpLoadPage() {
		return "/views/upload.html";
	}




}
