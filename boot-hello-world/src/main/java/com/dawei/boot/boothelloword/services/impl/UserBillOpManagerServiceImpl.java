package com.dawei.boot.boothelloword.services.impl;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.dawei.boot.boothelloword.mapper.UserBillLogOpMapper;
import com.dawei.boot.boothelloword.mapper.UserBillOpMapper;
import com.dawei.boot.boothelloword.mapper.entry.UserBillLogPo;
import com.dawei.boot.boothelloword.mapper.entry.UserBillPo;
import com.dawei.boot.boothelloword.services.UserBillOpManagerService;
import com.dawei.boot.boothelloword.support.RtResponse;
import com.dawei.boot.boothelloword.utils.GsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinbad on 2021/3/1.
 */
@Slf4j
@Service
public class UserBillOpManagerServiceImpl implements UserBillOpManagerService {

	@Resource
	private UserBillLogOpMapper userBillLogOpMapper;

	@Resource
	private UserBillOpMapper userBillOpMapper;


	@Resource
	private UserBillOpTxManagerServiceImpl userBillOpTxManagerService;


	/**
	 * 获取用户的账户余额
	 *
	 * @param userId 用户Id
	 * @return 金额数
	 */
	@Override
	public long getUserMoneyNum(long userId, int goldType) {
		log.info("Get user money num, userId={} goldType={}", userId, goldType);
		UserBillPo userBillPo = assureOpBillExist(userId, goldType);
		return userBillPo.getGoldCount();
	}

	/**
	 * 增加用户金币记录数据
	 *
	 * @param userBillLog 用户记录数据
	 * @return 返回操作情况 错误信息会
	 */
	@Override
	public RtResponse<String> putUserGoldRecord(UserBillLogPo userBillLog) {
		log.info("Put user gold record, userBillLog={}", GsonUtil.toJson(userBillLog));
		return opUserMoneyPipeline(userBillLog);
	}

	/**
	 * 扣减用户金币记录数据
	 *
	 * @param userBillLog 用户记录数据
	 * @return 返回操作情况 错误信息会
	 */
	@Override
	public RtResponse<String> decrUserGoldRecord(UserBillLogPo userBillLog) {
		log.info("Decr user gold record, userBillLog={}", GsonUtil.toJson(userBillLog));
		return opUserMoneyPipeline(userBillLog);
	}

	/**
	 * 金币操作流水线
	 * <p>
	 * 1、确定入账出账类型，确定金额操作数 <br>
	 * 2、确保存在个人户头，做后续操作 事务开始 <br>
	 * 3、确定账户 余额可用 <br>
	 * 4、写入出入账流水 <br>
	 * 5、更新账户数据
	 * </p>
	 * 
	 * @param userBillLog 操作记录数据
	 * @return 操作结果
	 */
	private RtResponse<String> opUserMoneyPipeline(UserBillLogPo userBillLog) {
		long userId = userBillLog.getUserId();
		int goldType = userBillLog.getGoldType();
		long goldNum = userBillLog.getGoldNum();
		String channelOpId = userBillLog.getOpId();
		int billType = userBillLog.getBillType();
		userBillLog.setCreateTime(System.currentTimeMillis());
		if (billType == 2) {
			goldNum = -goldNum;
		}
		userBillLog.setGoldNum(goldNum);

		// 确保用户账号初始化过
		assureOpBillExist(userId, goldType);
		// 校验账单数据
		UserBillPo userBillPoRecord = userBillOpMapper.selectUserBill(userId, goldType);
		if (userBillPoRecord.getGoldCount() + goldNum < 0) {
			return RtResponse.alertError("非法操作，余额不足！");
		}
		RtResponse<String> updateResult;

		try {
			updateResult = userBillOpTxManagerService.recordLogAndUpdateNum(userBillLog);
		} catch (Exception exception) {
			log.error(
					"Do record log and update money failed, userId={}, channelOpId={}, goldType={} e=",
					userId, channelOpId, goldType, exception);
			updateResult = RtResponse.error(exception.getMessage());
		}
		return updateResult;

	}


	/**
	 * 确保账户存在 记账的总额
	 *
	 * @param userId 用户Id
	 * @param goldType 账本类型
	 */
	private UserBillPo assureOpBillExist(long userId, int goldType) {
		UserBillPo userBillPoRecord = userBillOpMapper.selectUserBill(userId, goldType);
		if (userBillPoRecord == null) {
			// 3、初始化数据 替换查询结果
			userBillPoRecord = buildNewBill(userId, goldType);
			try {
				userBillOpMapper.insertUserBill(userBillPoRecord);
			} catch (DuplicateKeyException duplicateKeyException) {
				log.warn("Has some one insert this data, userId={} goldType={}", userId, goldType);
				userBillPoRecord = userBillOpMapper.selectUserBill(userId, goldType);
			}
		}
		return userBillPoRecord;
	}


	private UserBillPo buildNewBill(long userId, int goldType) {
		UserBillPo userBillPo = new UserBillPo();
		userBillPo.setUserId(userId);
		userBillPo.setGoldType(goldType);
		userBillPo.setGoldCount(0);
		userBillPo.setUpdateVersion("");
		userBillPo.setUpdateTime(System.currentTimeMillis());
		userBillPo.setCreateTime(System.currentTimeMillis());
		return userBillPo;
	}

}
