package com.dawei.boot.boothelloword.services.sub;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawei.boot.boothelloword.services.Test1ExtendsServiceImpl;

@Service
public class SubClass extends FatherClass{

//
//	@Autowired
//	public void setTestExtendsService(Test1ExtendsServiceImpl test1ExtendsService) {
//		super.testExtendsService = test1ExtendsService;
//	}

	@Autowired
	public SubClass( Test1ExtendsServiceImpl test1ExtendsService) {
		super.testExtendsService = test1ExtendsService;
	}

}
