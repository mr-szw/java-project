package com.dawei.boot.boothelloword.mapper.entry;

import lombok.Data;

/**
 * @author sinbad on 2021/3/1. 用户账单记录
 */
@Data
public class UserBillPo {

	private long id;

	// 用户的小米ID 分表键
	private long userId;
	/**
	 * 金币类型
	 * 
	 */
	private int goldType;
	// 入账总金额
	private long goldCount;

	// '数据入库时间'
	private long createTime;
	// '更新时间'
	private long updateTime;

	// '乐观锁'
	private String updateVersion;

}
