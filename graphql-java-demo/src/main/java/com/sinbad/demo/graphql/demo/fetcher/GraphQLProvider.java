package com.sinbad.demo.graphql.demo.fetcher;

import com.google.gson.Gson;
import graphql.ExecutionInput;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GraphQLProvider {


	private static GraphQLDataFetchers graphQLDataFetchers = new GraphQLDataFetchers();
	private  static  GraphQL GRAPHQL_LOCAL;

	public static void init() throws IOException {

		String sdl = "type Query {" +
				"    hello: String" +
				"    bookById(id: ID!): Book" +
				"    books(book: BookInput): [Book]" +
				"}" +
				"type Mutation {" +
				"    hello: String" +
				"}" +
				"input BookInput {" +
				"  id: ID" +
				"  name: String" +
				"}" +
				"type Book {" +
				"  id: ID" +
				"  name: String" +
				"  pageCount: Int" +
				"  author: Author" +
				"}" +
				"type Author {" +
				"  id: ID" +
				"  firstName: String" +
				"  lastName: String" +
				"}";
		GraphQLSchema graphQLSchema = buildSchema(sdl);
		GRAPHQL_LOCAL = GraphQL.newGraphQL(graphQLSchema).build();
	}
	private static GraphQLSchema buildSchema(String sdl) {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	}
	private static RuntimeWiring buildWiring() {
		return RuntimeWiring.newRuntimeWiring()
				// 仅仅是体验Mutation这个功能,返回一个字符串
				.type("Mutation", builder -> builder.dataFetcher("hello", new StaticDataFetcher("Mutation hello world")))
				// 返回字符串
				.type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("Query hello world")))
				// 通过id查询book
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
				// 查询所有的book
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("books", graphQLDataFetchers.getAllBooks()))
				// 查询book中的author信息
				.type(TypeRuntimeWiring.newTypeWiring("Book").dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
				.build();
	}


	public static void main(String[] args) throws IOException {

		init();

		GraphQL graphQL = GRAPHQL_LOCAL;



		Map<String, Object> variablesMap = new HashMap<>();
		variablesMap.put("bookId", "book-1");
		String query = "query BookById($bookId: ID){ " +
		"  bookById(id: $bookId){" +
		"    id" +
		"    name" +
		"    pageCount" +
		"    author {" +
		"      firstName" +
		"      lastName" +
		"    }" +
		"  }" +
		"}";
		ExecutionInput executionInput = ExecutionInput.newExecutionInput()
					// 需要执行的查询语言
					.query(query)
					// 执行操作的名称，默认为null
					.operationName(null)
					// 获取query语句中定义的变量的值
					.variables(variablesMap)
					.build();
			// 执行并返回结果
		Map<String, Object> stringObjectMap = graphQL.execute(executionInput).toSpecification();
		System.out.println(new Gson().toJson(stringObjectMap));
		System.out.println("Done");
	}
}
