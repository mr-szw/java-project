package com.dawei.boot.boothelloword.support;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author sinbad on 2020/4/20
 */
@Data
public class RtEntity<T> implements Serializable {

	private int total;
	private int limit;
	private String after;
	private List<T> records;

	public RtEntity() {
	}

	public static <T> RtEntity<T> copyRtEntity(RtEntity<?> rtEntity) {
		RtEntity<T> resultEntity = new RtEntity<>();
		resultEntity.setAfter(rtEntity.getAfter());
		resultEntity.setLimit(rtEntity.getLimit());
		resultEntity.setTotal(rtEntity.getTotal());
		return resultEntity;

	}
}
