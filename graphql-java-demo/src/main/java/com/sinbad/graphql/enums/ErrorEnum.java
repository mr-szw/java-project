package com.sinbad.graphql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常码定义
 * @author sinbad on 2023/1/10 - 11:22 PM.
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorEnum {

    UNKNOWN(-1, "unknown"),
    SUCCESS(0, "成功"),
    ERROR(1, "系统异常"),
    ERROR_PARAM(2, "参数异常");

    private int code;

    private String desc;

    public static String getDescByCode(int code) {
        for (ErrorEnum value : values()) {
            if (value.getCode() == code) {
                return value.getDesc();
            }
        }
        return UNKNOWN.getDesc();
    }
}