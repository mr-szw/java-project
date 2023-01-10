package com.sinbad.graphql.po;

import lombok.Data;

import java.util.List;

/**
 * 数据元-表实体定义
 * @author sinbad on 2023/1/10 - 11:30 PM.
 **/
@Data
public class GraphqlEntryDto {


    /**
     * 实体id
     */
    private long id;


    /**
     * 实体名称
     */
    private String entryName;

    /**
     * 实体 key
     */
    private String entryKey;

    /**
     * 数据源信息
     * ?
     */
    private GraphqlDataSourcesDto graphqlDataSourcesDto;

    /**
     * 实体字段
     */
    private List<GraphqlEntryFiledDto> graphqlEntryFiledDtoList;
}
