package com.dawei.boot.boothelloword.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dawei.boot.boothelloword.mapper.entry.UserBillPo;


/**
 * @author sinbad on 2021/3/1.
 */
@Mapper
public interface UserBillOpMapper {

	/**
	 * 入库记录
	 */
	int insertUserBill(UserBillPo userBillPo);

	/**
	 * 查询账单
	 *
	 * @param userId userId 分表
	 * @param goldType 金币类型
	 */
	UserBillPo selectUserBill(@Param("userId") long userId, @Param("goldType") int goldType);

	/**
	 * 修改账单
	 *
	 * @param userId userId 分表
	 * @param goldType 金币类型
	 * @see com.xiaomi.planet.bill.api.enums.GoldTypeEnums
	 * @param opNum 操作数量
	 * @param lastVersion 版本比对
	 */
	int updateBillByUserIdAndLastVersion(@Param("userId") long userId,
			@Param("goldType") int goldType, @Param("opNum") long opNum,
			@Param("lastVersion") String lastVersion, @Param("updateTime") long updateTime);
}
