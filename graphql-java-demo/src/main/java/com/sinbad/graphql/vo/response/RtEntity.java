package com.sinbad.graphql.vo.response;

import java.io.Serializable;
import java.util.List;

import com.sinbad.graphql.enums.ErrorEnum;
import lombok.Data;

/**
 * @author sinbad on 2020/4/20
 */
@Data
public class RtEntity<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public RtEntity() {
    
    }


    public RtEntity(int code) {
        this.code = code;
        this.message = ErrorEnum.getDescByCode(code);
    }

    public RtEntity(int code, T data) {
        this.code = code;
        this.message = ErrorEnum.getDescByCode(code);
        this.data = data;
    }

    public static <T> RtEntity<T> success(T result) {
        RtEntity<T> response = new RtEntity<>();
        response.setCode(ErrorEnum.SUCCESS.getCode());
        response.setMessage(ErrorEnum.SUCCESS.getDesc());
        response.setData(result);
        return response;
    }
    
    
}
