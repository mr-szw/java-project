package com.sinbad.graphql.po;

import lombok.Data;

/**
 * 数据元-实体字段定义
 * @author sinbad on 2023/1/10 - 11:30 PM.
 **/
@Data
public class GraphqlEntryFiledDto {


    /**
     * 实体id
     */
    private long id;


    /**
     * 实体名称
     */
    private String filedName;

    /**
     * 实体 key
     */
    private String filedKey;

    /**
     * 数据源信息
     */
    private GraphqlDataSourcesDto graphqlDataSourcesDto;

}
