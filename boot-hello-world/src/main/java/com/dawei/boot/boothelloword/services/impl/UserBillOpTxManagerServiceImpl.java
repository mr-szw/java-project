package com.dawei.boot.boothelloword.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dawei.boot.boothelloword.mapper.UserBillLogOpMapper;
import com.dawei.boot.boothelloword.mapper.UserBillOpMapper;
import com.dawei.boot.boothelloword.mapper.entry.UserBillLogPo;
import com.dawei.boot.boothelloword.support.RtResponse;
import com.dawei.boot.boothelloword.utils.GsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinbad on 2021/3/1.
 */
@Slf4j
@Service
public class UserBillOpTxManagerServiceImpl {

	@Resource
	private UserBillLogOpMapper userBillLogOpMapper;

	@Resource
	private UserBillOpMapper userBillOpMapper;


	/**
	 * 记账并更新金额
	 *
	 * @param userBillLog 流水数据记录
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RtResponse<String> recordLogAndUpdateNum(UserBillLogPo userBillLog) {
		log.info("[Transactional] Start: Do update user money, updateUserMoneyRt={}",
				GsonUtil.toJson(userBillLog));
		long userId = userBillLog.getUserId();
		int goldType = userBillLog.getGoldType();
		long goldNum = userBillLog.getGoldNum();
		String channelOpId = userBillLog.getOpId();

		int insetUserBillOpLog = userBillLogOpMapper.insetUserBillOpLog(userBillLog);

		try {

			System.out.println(1 / 0);
		} catch (Exception exception) {
			throw new RuntimeException();
		}
		// 2、更新
		int updateBill = userBillOpMapper.updateBillByUserIdAndLastVersion(userId, goldType,
				goldNum, channelOpId, System.currentTimeMillis());
		if (updateBill == 0) {
			//TODO ??? 更新不成功
			log.info("Try again update user money, userId={} channelId={}", userId, channelOpId);
		} else if (updateBill != 1) {
			String errMsg = String.format(
					"[Abnormal operation] : update User=%d Money error, goldType=%d opNum=%d, channelId=%s",
					userId, goldType, goldNum, channelOpId);
			log.error("[Error operation] errInfo={}", errMsg);
			throw new IllegalStateException(errMsg);
		} else {
			log.info("Op success, userId={} channelId={}", userId, channelOpId);
		}
		log.info(
				"[Transactional] End: Do update finish ,userId={}, channelOpId={}",
				userId, channelOpId);
		return RtResponse.success();
	}


}
