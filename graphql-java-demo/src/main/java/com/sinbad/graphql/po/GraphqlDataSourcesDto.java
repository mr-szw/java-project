package com.sinbad.graphql.po;

import com.sinbad.graphql.enums.DatasourceType;
import lombok.Data;

/**
 * 数据元-表实体定义
 * @author sinbad on 2023/1/10 - 11:30 PM.
 **/
@Data
public class GraphqlDataSourcesDto {


    /**
     * 数据源id
     */
    private long id;

    /**
     * 数据源 名字
     */
    private String datasourceName;

    /**
     * 数据源 key
     */
    private String datasourceKey;

    /**
     * 数据源 类型
     * @see DatasourceType
     */
    private int datasourceType;
}
