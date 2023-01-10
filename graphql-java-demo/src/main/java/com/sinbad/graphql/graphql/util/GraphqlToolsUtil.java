package com.sinbad.graphql.graphql.util;

import com.sinbad.graphql.graphql.datafetcher.GraphqlDataFetcher;
import com.sinbad.graphql.po.GraphqlDataSourcesDto;

/**
 * @author sinbad on 2023/1/11 - 1:05 AM.
 **/
public class GraphqlToolsUtil {


    public static String buildDatasourceKey(GraphqlDataSourcesDto dataSourcesDto) {
        return dataSourcesDto.getDatasourceKey() + "_" + dataSourcesDto.getDatasourceType();
    }

    public static String buildDataFetcherKey(GraphqlDataFetcher<?> graphqlDataFetcher) {
        return graphqlDataFetcher.datasourceKey() + "_" + graphqlDataFetcher.datasourceType();
    }
}
