package com.dawei.boot.boothelloword.services.sub;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dawei.boot.boothelloword.services.Test2ExtendsServiceImpl;
import com.dawei.boot.boothelloword.services.TestExtendsService;

import lombok.Setter;



public class FatherClass {


	@Resource
	private Test2ExtendsServiceImpl test2ExtendsService;


	protected TestExtendsService testExtendsService;


	public String printName() {
		test2ExtendsService.printName();
		System.out.println("1234");
		return testExtendsService.printName();
	}



}
