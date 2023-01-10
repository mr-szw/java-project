package com.sinbad.graphql.graphql.datafetcher;

import com.sinbad.graphql.enums.DatasourceType;
import graphql.schema.DataFetcher;

/**
 * graphql的抽象显现类型
 * @author sinbad on 2023/1/11 - 12:38 AM.
 **/
public interface GraphqlDataFetcher<T> extends DataFetcher<T> {

    /**
     * 数据源 key
     */
    String datasourceKey();

    /**
     * 数据源 类型
     * @see DatasourceType
     */
    int datasourceType();
}
