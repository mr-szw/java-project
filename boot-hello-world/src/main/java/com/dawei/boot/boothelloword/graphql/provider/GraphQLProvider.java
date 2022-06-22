package com.dawei.boot.boothelloword.graphql.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import com.dawei.boot.boothelloword.graphql.fetcher.BookDataFetcher;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

/**
 * 构建graphql 并且完成其初始化
 *
 * @author sinbad on 2021/5/11.
 */
public class GraphQLProvider {


	private static GraphQL GRAPHQL;
	private static BookDataFetcher bookDataFetcher = new BookDataFetcher();
	private static String GraphQl_File = "schema {\n" +
			"    query: BookQuery\n" +
			"}\n" +
			"\n" +
			"type BookQuery {\n" +
			"    GetBookById(id: Int) : BookInfo\n" +
			"}\n" +
			"\n" +
			"type BookInfo {\n" +
			"    id: Int,\n" +
			"    bookName: String,\n" +
			"    buildingTime: Int,\n" +
			"    authorInfo: AuthorInfo\n" +
			"}\n" +
			"type AuthorInfo {\n" +
			"    id: Int,\n" +
			"    authorName: String\n" +
			"}";

	public static Map<String, Object> graphQLQuery(String sdl, String query) {
		ExecutionResult executionResult = GRAPHQL.execute(query);
		List<GraphQLError> graphQLErrorList = executionResult.getErrors();
		if (CollectionUtils.isEmpty(graphQLErrorList)) {
			return executionResult.getData();
		} else {
			for (GraphQLError graphQLError : graphQLErrorList) {
				System.out.println(graphQLError.toSpecification());
			}
		}

		return null;
	}

	private static void init() {
		try {
			buildGraphQL();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void buildGraphQL() throws FileNotFoundException {
		GraphQLSchema graphQLSchema = buildGraphQLSchema();
		GRAPHQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	private static GraphQLSchema buildGraphQLSchema() throws FileNotFoundException {
		File graphqlFile = ResourceUtils.getFile("classpath:graphQLTest.graphqls");
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(graphqlFile);
		//TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(GraphQl_File);
		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
				.type("BookQuery", builder ->
						builder.dataFetcher("BookMarkInfo", bookDataFetcher)
				).
						build();

		return new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
	}

	public static void main(String[] args) {
		init();
	}
}

