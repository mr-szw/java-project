package com.dawei.boot.boothelloword.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dawei.boot.boothelloword.mapper.entry.UserBillLogPo;


/**
 * @author sinbad on 2021/3/1.
 */
@Mapper
public interface UserBillLogOpMapper {

	/**
	 * 入库记录
	 */
	int insetUserBillOpLog(UserBillLogPo userBillLogPo);


}
