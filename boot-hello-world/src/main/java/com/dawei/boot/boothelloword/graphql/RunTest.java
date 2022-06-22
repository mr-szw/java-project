package com.dawei.boot.boothelloword.graphql;

import com.dawei.boot.boothelloword.graphql.fetcher.AuthorDataFetcher;
import com.dawei.boot.boothelloword.graphql.fetcher.BookDataFetcher;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import graphql.parser.InvalidSyntaxException;
import graphql.parser.Parser;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author sinbad on 2020/05/13.
 */
public class RunTest {


	private static String schema = "type Query { " +
			"  getBookById ( bookId: String): BookInfo " +
			"  getAuthorInfo ( authorId: Int): AuthorInfo " +
			"} " +
			" type BookInfo { " +
			"  bookId: String " +
			"  bookName: String " +
			"  authorInfo( authorId: Int): AuthorInfo " +
			" } "
			+
			" type AuthorInfo { " +
			"    authorId: Int, " +
			"    authorName: String " +
			"} ";

	private static String queryStr = "query TopQuery($bookId: String, $authorId: Int) { " +
			"getBookById(bookId: $bookId) { " +
			" bookId " +
			" bookName " +
			" authorInfo (authorId: $authorId) {" +
			" authorId " +
			" authorName " +
			"}  " +
			"} " +
			" getAuthorInfo(authorId: $authorId) {" +
			" authorId " +
			" authorName " +
			"}" +
			"} ";


	public static void main(String[] args) {
		//这里是定义schema 的字符串，定义了一个名为hello的查询，返回的数据类型是String
		//schema除了直接通过String字符串定义之外，还可以通过SDL文件（后缀为*.graphqls的文件）或编码的方式定义。


		//缓存 DocumentEntry
		LoadingCache<String, PreparsedDocumentEntry> preparsedDocumentEntryLoadingCache = CacheBuilder.newBuilder()
				.maximumSize(10).
						refreshAfterWrite(1, TimeUnit.MINUTES).
						build(new CacheLoader<String, PreparsedDocumentEntry>() {
							@Override
							public PreparsedDocumentEntry load(@NonNull String ql) {
								System.out.println(ql);
								if (StringUtils.isEmpty(ql)) {
									return null;
								}
								try {
									return getPreparsedDocumentEntryByQl(ql);
								} catch (InvalidSyntaxException e) {
									e.printStackTrace();
									return null;
								}
							}
						});


		PreparsedDocumentProvider preparsedDocumentProvider = (executionInput, function) -> {
			String querySdl = executionInput.getQuery();

			try {
				return preparsedDocumentEntryLoadingCache.get(querySdl);
			} catch (ExecutionException executionException) {
				executionException.printStackTrace();
				return null;
			}
		};


		SchemaParser schemaParser = new SchemaParser();
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

		//实例化一个RuntimeWiring对象，这个对象中关联了一个DataFetcher对象，DataFetcher对象是用来获取数据的，获取数据的方式需要开发人员根据实际情况实现，它只关心返回结果
		//这里是将名为hello的查询关联到一个简单的StaticDataFetcher对象，它返回一个字符串"world"
		RuntimeWiring runtimeWiring = buildRuntimeWiring().build();


		SchemaGenerator schemaGenerator = new SchemaGenerator();


		//将schema定义与RuntimeWiring绑定，生产可执行的schema
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

		//GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
		GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema)
				.preparsedDocumentProvider(preparsedDocumentProvider)
				.build();


		Map<String, Object> variablesMap = new HashMap<>();
		variablesMap.put("bookId", "book-1");
		variablesMap.put("authorId", 123);

		ExecutionInput executionInput = ExecutionInput.newExecutionInput()
				// 需要执行的查询语言
				.query(queryStr)
				// 执行操作的名称，默认为null
				.operationName("TopQuery")
				// 获取query语句中定义的变量的值
				.variables(variablesMap)
				.build();

		//执行hello查询
		ExecutionResult executionResult = graphQL.execute(executionInput);

		//输出查询结果，结果为{hello=world},默认是Map格式的数据
		System.out.println(executionResult.getData().toString());
		System.out.println(executionResult.getErrors());
	}


	private static PreparsedDocumentEntry getPreparsedDocumentEntryByQl(String queryQl) {
		Parser parser = new Parser();
		try {
			return new PreparsedDocumentEntry(parser.parseDocument(queryQl));
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 这里需要处理绑定关系
	 * @return
	 */
	private static RuntimeWiring.Builder buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", builder -> builder.dataFetcher("getBookById", new BookDataFetcher()))
				.type("Query", builder -> builder.dataFetcher("getAuthorInfo", new AuthorDataFetcher()));
	}
}

