package com.sinbad.graphql.manager.service;

import com.google.common.collect.Lists;
import com.sinbad.graphql.po.GraphqlDataSourcesDto;
import com.sinbad.graphql.po.GraphqlEntryDto;
import com.sinbad.graphql.vo.response.RtListResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinbad on 2023/1/11 - 12:42 AM.
 **/
@Service
public class GraphqlEntryQueryService {

    public RtListResponse<GraphqlEntryDto> getAllGraphqlEntry() {

        GraphqlDataSourcesDto authorGraphqlDataSourcesDto = new GraphqlDataSourcesDto();
        authorGraphqlDataSourcesDto.setDatasourceKey("user_dataFetcher");
        authorGraphqlDataSourcesDto.setDatasourceType(0);

        GraphqlDataSourcesDto bookGraphqlDataSourcesDto = new GraphqlDataSourcesDto();
        bookGraphqlDataSourcesDto.setDatasourceKey("book_dataFetcher");
        bookGraphqlDataSourcesDto.setDatasourceType(0);

        GraphqlEntryDto authorGraphqlEntryDto = new GraphqlEntryDto();
        authorGraphqlEntryDto.setEntryName("AuthorEntry");
        authorGraphqlEntryDto.setEntryKey("AuthorEntry");
        authorGraphqlEntryDto.setGraphqlDataSourcesDto(authorGraphqlDataSourcesDto);

        GraphqlEntryDto bookGraphqlEntryDto = new GraphqlEntryDto();
        bookGraphqlEntryDto.setEntryName("BookEntry");
        bookGraphqlEntryDto.setEntryKey("BookEntry");
        bookGraphqlEntryDto.setGraphqlDataSourcesDto(bookGraphqlDataSourcesDto);

        return RtListResponse.success(Lists.newArrayList(authorGraphqlEntryDto, bookGraphqlEntryDto));
    }
}
