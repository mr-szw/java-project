package com.sinbad.demo.graphql.demo.fetcher;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.sinbad.demo.graphql.demo.fetcher.pojo.BookPojo;
import graphql.schema.DataFetcher;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GraphQLDataFetchers {


	private static ThreadLocal<Gson> gson = ThreadLocal.withInitial(Gson::new);


	private static List<Map<String, String>> books = Arrays.asList(

			ImmutableMap.of("id", "book-1",
					"name", "Harry Potter and the Philosopher's Stone",
					"pageCount", "223",
					"authorId", "author-1"),
			ImmutableMap.of("id", "book-2",
					"name", "Moby Dick",
					"pageCount", "635",
					"authorId", "author-2"),
			ImmutableMap.of("id", "book-3",
					"name", "Interview with the vampire",
					"pageCount", "371",
					"authorId", "author-3")
	);
	private static List<Map<String, String>> authors = Arrays.asList(
			ImmutableMap.of("id", "author-1",
					"firstName", "Joanne",
					"lastName", "Rowling"),
			ImmutableMap.of("id", "author-2",
					"firstName", "Herman",
					"lastName", "Melville"),
			ImmutableMap.of("id", "author-3",
					"firstName", "Anne",
					"lastName", "Rice")
	);

	public DataFetcher<BookPojo> getAllBooks() {
		return dataFetchingEnvironment -> {
			Map<String, Object> arguments = dataFetchingEnvironment.getArgument("book");
			BookPojo book = gson.get().fromJson(gson.get().toJson(arguments), BookPojo.class);
			return book;
		};
	}

	public DataFetcher<Map<String, String>> getBookByIdDataFetcher() {
		// dataFetchingEnvironment 封装了查询中带有的参数
		return dataFetchingEnvironment -> {
			String bookId = dataFetchingEnvironment.getArgument("id");
			Map<String, String> matchBook = books
					.stream()
					.filter(book -> book.get("id").equals(bookId))
					.findFirst()
					.orElse(null);
			return matchBook;
		};
	}

	public DataFetcher<Map<String, String>> getAuthorDataFetcher() {
		// 这里因为是通过Book查询Author数据的子查询，所以dataFetchingEnvironment.getSource()中封装了Book对象的全部信息
		//即GraphQL中每个字段的Datafetcher都是以自顶向下的方式调用的，父字段的结果是子Datafetcherenvironment的source属性。
		return dataFetchingEnvironment -> {
			Map<String, String> book = dataFetchingEnvironment.getSource();
			String authorId = book.get("authorId");
			Map<String, String> matchAuthorMap = authors
					.stream()
					.filter(author -> author.get("id").equals(authorId))
					.findFirst()
					.orElse(null);
			return matchAuthorMap;
		};
	}
}