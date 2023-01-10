package com.sinbad.graphql.graphql.config;

import com.sinbad.graphql.graphql.datafetcher.GraphqlDataFetcher;
import com.sinbad.graphql.graphql.util.GraphqlToolsUtil;
import com.sinbad.graphql.manager.service.GraphqlEntryQueryService;
import com.sinbad.graphql.po.GraphqlDataSourcesDto;
import com.sinbad.graphql.po.GraphqlEntryDto;
import com.sinbad.graphql.vo.response.RtListResponse;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * graphql 构建配置
 *
 * @author sinbad on 2023/1/11 - 12:24 AM.
 **/
@Configuration
public class GraphQLProviderConfiguration {

    private GraphQL GRAPH_QL;

    @Bean
    public GraphQL graphQL() {
        return this.GRAPH_QL;
    }

    @Autowired
    private List<GraphqlDataFetcher<?>> graphqlDataFetcherList;

    private static Map<String, GraphqlDataFetcher<?>> graphqlDataFetcherMap;
    @Autowired
    private GraphqlEntryQueryService graphqlEntryQueryService;

    @PostConstruct
    public void initGraphQL() {
        //获取配置文件
        String schema = "type BookEntry {" +
                "  id: ID" +
                "  name: String" +
                "  pageCount: Int" +
                "  author: Author" +
                "}" +
                "type AuthorEntry {" +
                "  id: ID" +
                "  firstName: String" +
                "  lastName: String" +
                "}";
        graphqlDataFetcherMap = new HashMap<>();
        for (GraphqlDataFetcher<?> graphqlDataFetcher : graphqlDataFetcherList) {
            graphqlDataFetcherMap.put(GraphqlToolsUtil.buildDataFetcherKey(graphqlDataFetcher), graphqlDataFetcher);
        }
        //构建graphql
        GRAPH_QL = GraphQL.newGraphQL(buildGraphQLSchema(schema)).build();
    }


    private GraphQLSchema buildGraphQLSchema(String schemaInput) {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaInput);
        return new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, buildRuntimeWiring());
    }

    private RuntimeWiring buildRuntimeWiring() {

        需要参考 com.sinbad.graphql.service.GraphqlQueryService.buildWiring()

        return RuntimeWiring.newRuntimeWiring()
                .type("topQuery", builder -> {
                    RtListResponse<GraphqlEntryDto> dtoRtListResponse = graphqlEntryQueryService.getAllGraphqlEntry();
                    for (GraphqlEntryDto graphqlEntryDto : dtoRtListResponse.getDataList()) {
                        String entryKey = graphqlEntryDto.getEntryKey();
                        GraphqlDataSourcesDto graphqlDataSourcesDto = graphqlEntryDto.getGraphqlDataSourcesDto();
                        String buildDatasourceKey = GraphqlToolsUtil.buildDatasourceKey(graphqlDataSourcesDto);
                        GraphqlDataFetcher<?> graphqlDataFetcher = graphqlDataFetcherMap.get(buildDatasourceKey);
                        builder.dataFetcher(entryKey, graphqlDataFetcher);
                    }
                })
                .build();
    }

}
