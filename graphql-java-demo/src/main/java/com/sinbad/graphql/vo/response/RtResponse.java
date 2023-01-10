package com.sinbad.graphql.vo.response;

import com.sinbad.graphql.enums.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用的response
 * @author sinbad on 2023/1/10 - 11:46 PM.
 **/
@Data
public class RtResponse<T> implements Serializable {

    private int code;

    private String message;

    private T data;


    public RtResponse() {
    }

    public RtResponse(int code) {
        this.code = code;
        this.message = ErrorEnum.getDescByCode(code);
    }

    public RtResponse(int code, T data) {
        this.code = code;
        this.message = ErrorEnum.getDescByCode(code);
        this.data = data;
    }

    public static <T> RtResponse<T> success(T result) {
        RtResponse<T> response = new RtResponse<>();
        response.setCode(ErrorEnum.SUCCESS.getCode());
        response.setMessage(ErrorEnum.SUCCESS.getDesc());
        response.setData(result);
        return response;
    }
}
