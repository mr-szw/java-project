package com.dawei.boot.boothelloword.enums;

import lombok.Getter;

/**
 * 错误码
 */
@Getter
public enum ErrorCodeEnums {

	// 需要大家梳理解析维护
	OK(0, "ok"),
	// 主要
	SUCCESS(200, "success"),

	SIGN_MSG_INVALID(203, "签名信息不正确"),

	PARAM_INVALID(400, "传递参数不合法"),

	ERROR_UNAUTHORIZED(401, "no login"),

	ERROR(500, "服务器出错啦"),

	ERROR_ALERT(640, "失败并展示提示信息"),

	ERROR_TOO_FAST(13333, "你手太快了!"),

	NO_AUTH_CALL_ERROR(6999, "本接口暂不对外开放"),

	SERVER_ERROR(80000, "未知错误");

	private final int errorCode;
	private final String errorMag;

	ErrorCodeEnums(int errorCode, String errorMag) {
		this.errorCode = errorCode;
		this.errorMag = errorMag;
	}

}
