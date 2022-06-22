package com.dawei.boot.boothelloword.service;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.dawei.boot.boothelloword.BootHelloWorldApplicationTests;
import com.dawei.boot.boothelloword.mapper.entry.UserBillLogPo;
import com.dawei.boot.boothelloword.services.UserBillOpManagerService;
import com.dawei.boot.boothelloword.support.RtResponse;
import com.dawei.boot.boothelloword.utils.GsonUtil;


class UserBillOpManagerServiceTest extends BootHelloWorldApplicationTests {

	private static long userId = 1404277092L;

	@Resource
	private UserBillOpManagerService userBillOpManagerService;


	@Test
	public void putUserGoldRecord() {
		UserBillLogPo userBillLogPo = new UserBillLogPo();
		userBillLogPo.setGoldNum(1);
		userBillLogPo.setGoldType(1);
		userBillLogPo.setChannelType(1);
		userBillLogPo.setBillMark("Test" + new Date());
		userBillLogPo.setOpId(UUID.randomUUID().toString());
		userBillLogPo.setUserId(userId);
		userBillLogPo.setBillType(1);
		RtResponse<String> stringRtResponse = userBillOpManagerService
				.putUserGoldRecord(userBillLogPo);
		System.out.println(GsonUtil.toJson(stringRtResponse));
	}

	@Test
	public void decrUserGoldRecord() {
		UserBillLogPo userBillLogPo = new UserBillLogPo();
		userBillLogPo.setGoldNum(1L);
		userBillLogPo.setGoldType(1);
		userBillLogPo.setChannelType(1);
		userBillLogPo.setBillMark("Test" + new Date());
		userBillLogPo.setOpId(UUID.randomUUID().toString());
		userBillLogPo.setUserId(userId);
		userBillLogPo.setBillType(2);
		RtResponse<String> stringRtResponse = userBillOpManagerService
				.decrUserGoldRecord(userBillLogPo);
		System.out.println(GsonUtil.toJson(stringRtResponse));
	}


	public static void main(String[] args) {
	}


}