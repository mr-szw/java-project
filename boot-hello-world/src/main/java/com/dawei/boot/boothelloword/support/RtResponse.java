package com.dawei.boot.boothelloword.support;

import java.io.Serializable;

import com.dawei.boot.boothelloword.enums.ErrorCodeEnums;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sinbad on 2020/4/20
 */
@Data
@AllArgsConstructor
public class RtResponse<T> implements Serializable {

	private int status = 200;
	private String message;
	private long time;
	private T entity;

	public RtResponse() {
	}

	public static <T> RtResponse<T> success() {
		RtResponse<T> response = new RtResponse<>();
		response.setStatus(200);
		response.setMessage("success");
		return response;
	}

	public static <T> RtResponse<T> successMsg(String message) {
		RtResponse<T> response = new RtResponse<>();
		response.setStatus(200);
		response.setMessage(message);
		return response;
	}

	public static <T> RtResponse<T> success(T result) {
		RtResponse<T> response = new RtResponse<>();
		response.setStatus(200);
		response.setMessage("success");
		response.setEntity(result);
		return response;
	}

	public static <T> RtResponse<T> copyRtResponse(RtResponse<?> rtResponse) {
		RtResponse<T> response = new RtResponse<>();
		response.setStatus(rtResponse.getStatus());
		response.setMessage(rtResponse.getMessage());
		return response;

	}

	/**
	 * 有自定义错误异常信息
	 */
	public static <T> RtResponse<T> error(ErrorCodeEnums errorCodeEnums) {
		return error(errorCodeEnums.getErrorCode(), errorCodeEnums.getErrorMag());
	}

	/**
	 * 有自定义错误异常信息
	 */
	public static <T> RtResponse<T> error(String errorMessage) {
		return error(ErrorCodeEnums.ERROR.getErrorCode(), errorMessage);
	}

	/**
	 * 有自定义错误异常信息
	 */
	public static <T> RtResponse<T> error(Integer code, String msg) {
		RtResponse<T> response = new RtResponse<>();
		response.setStatus(code);
		response.setMessage(msg);
		return response;
	}

	public boolean successRt() {
		return this.status == 200;
	}

	public static <T> RtResponse<T> paramError(String msg) {
		RtResponse<T> response = new RtResponse<>();
		response.setStatus(ErrorCodeEnums.PARAM_INVALID.getErrorCode());
		response.setMessage(msg);
		return response;
	}

	public static <T> RtResponse<T> paramError() {
		RtResponse<T> response = new RtResponse<>();
		response.setStatus(ErrorCodeEnums.PARAM_INVALID.getErrorCode());
		response.setMessage(ErrorCodeEnums.PARAM_INVALID.getErrorMag());
		return response;
	}

	public static <T> RtResponse<T> alertError(String message) {
		RtResponse<T> response = new RtResponse<>();
		response.setStatus(ErrorCodeEnums.ERROR_ALERT.getErrorCode());
		response.setMessage(message);
		return response;
	}

}
