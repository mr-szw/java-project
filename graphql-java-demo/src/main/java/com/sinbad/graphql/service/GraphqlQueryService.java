package com.sinbad.graphql.service;

import com.google.gson.Gson;
import com.sinbad.graphql.fetcher.GraphQLDataFetchers;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sinbad on 2023/1/10 - 11:15 PM.
 **/
@Service
public class GraphqlQueryService {


    public String query() {
        // 1. 定义Schema, 一般会定义在一个schema文件中
        String schema = "type Query{hello: String}";
        // 2. 解析Schema
        SchemaParser schemaParser = new SchemaParser();

        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        // 为Schema 中 hello方法绑定 获取数据的方法
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                // 这里绑定的是最简单的静态数据数据获取器, 正常使用时,获取数据的方法返回一个DataFetcher实现即可
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
                .build();
        // 将 TypeDefinitionRegistry 与 RuntimeWiring 结合起来生成 GraphQLSchema
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        // 实例化GraphQL, GraphQL为执行GraphQL语言的入口
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        // 执行查询
        ExecutionResult executionResult = graphQL.execute("{hello}");
        // 打印执行结果
        System.out.println(executionResult.getData().toString());
        return executionResult.getData().toString();


    }





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


    public static void test(String[] args) throws IOException {

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
