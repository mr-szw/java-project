package com.sinbad.graphql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 数据源类型枚举
 *
 * @author sinbad on 2023/1/11 - 12:47 AM.
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DatasourceType {

    UNKNOWN(-1, "unknown"),

    MYSQL(1, "Mysql"),
    CACHE_REDIS(10, "redis cache"),
    RPC_THRIFT(20, "rpc thrift"),
    RPC_HTTP(21, "rpc http");

    private int code;

    private String desc;
}
