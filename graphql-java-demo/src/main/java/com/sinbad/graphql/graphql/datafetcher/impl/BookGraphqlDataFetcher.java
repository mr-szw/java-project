package com.sinbad.graphql.graphql.datafetcher.impl;

import com.sinbad.graphql.entry.BookEntry;
import com.sinbad.graphql.graphql.datafetcher.GraphqlDataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author sinbad on 2023/1/11 - 12:52 AM.
 **/
@Component
public class BookGraphqlDataFetcher implements GraphqlDataFetcher<BookEntry> {
    @Override
    public String datasourceKey() {
        return "book_dataFetcher";
    }

    @Override
    public int datasourceType() {
        return 0;
    }

    @Override
    public BookEntry get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return null;
    }
}
