package com.sinbad.demo.graphql.demo.fetcher.pojo;

import lombok.Data;

@Data
public class BookPojo {

	private String id;
	private String name;
	private long pageCount;
	private Author author;
}
