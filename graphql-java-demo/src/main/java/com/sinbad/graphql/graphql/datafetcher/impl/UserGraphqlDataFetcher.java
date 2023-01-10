package com.sinbad.graphql.graphql.datafetcher.impl;

import com.sinbad.graphql.entry.AuthorEntry;
import com.sinbad.graphql.graphql.datafetcher.GraphqlDataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author sinbad on 2023/1/11 - 12:52 AM.
 **/
@Component
public class UserGraphqlDataFetcher implements GraphqlDataFetcher<AuthorEntry> {


    @Override
    public String datasourceKey() {
        return "user_dataFetcher";
    }

    @Override
    public int datasourceType() {
        return 0;
    }

    @Override
    public AuthorEntry get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return null;
    }
}
