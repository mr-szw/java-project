//package com.sinbad.demo.graphql.demo.fetcher;
//
//import com.sinbad.demo.graphql.demo.fetcher.pojo.BookPojo;
//import graphql.schema.DataFetcher;
//import graphql.schema.DataFetchingEnvironment;
//
//import java.util.Map;
//
//public class BookDateFetcher implements DataFetcher<BookPojo> {
//	@Override
//	public BookPojo get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
//		Map<String, Object> arguments = dataFetchingEnvironment.getArgument("book");
//		return book = gson.get().fromJson(gson.get().toJson(arguments), java.awt.print.Book.class);
//	}
//}
