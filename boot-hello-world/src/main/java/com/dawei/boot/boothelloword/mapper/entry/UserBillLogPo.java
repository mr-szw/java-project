package com.dawei.boot.boothelloword.mapper.entry;

import lombok.Data;

/**
 * @author sinbad on 2021/3/1. 用户账单记录
 */
@Data
public class UserBillLogPo {

	/**
	 * 主键ID 长度60
	 * <p>
	 * 出入账渠道ID 保证唯一 <br>
	 * 目前可以传入活动和规则+时间的组合 或者 订单ID <br>
	 * </p>
	 */
	private String opId;
	// 用户的小米ID 分表键
	private long userId;
	// 出入账金额
	private long goldNum;
	/**
	 * 金币类型
	 * 
	 */
	private int goldType;
	/**
	 * 出入账类型 1 入账 2 出账
	 * 
	 */
	private int billType;

	// 入账渠道类型 1:活动
	private int channelType;

	// 出入账原因 可以描述类型
	private String billMark;

	// '数据入库时间'
	private long createTime;

}
