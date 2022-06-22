package com.dawei.boot.boothelloword.services;

import com.dawei.boot.boothelloword.mapper.entry.UserBillLogPo;
import com.dawei.boot.boothelloword.support.RtResponse;

/**
 * @author sinbad on 2021/3/1.
 */
public interface UserBillOpManagerService {

	long getUserMoneyNum(long userId, int goldType);

	RtResponse<String> putUserGoldRecord(UserBillLogPo userBillLog);

	/**
	 * 扣减用户金币记录数据
	 *
	 * @param userBillLog 用户记录数据
	 * @return 返回操作情况 错误信息会
	 */
	RtResponse<String> decrUserGoldRecord(UserBillLogPo userBillLog);



}
