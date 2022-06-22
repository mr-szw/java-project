package com.dawei.boot.boothelloword.graphql.entry;

import lombok.Data;

/**
 * @author sinbad on 2021/5/12.
 */
@Data
public class BookInfo {

	private String bookId;
	private String bookName;

	private AuthorInfo authorInfo;



}
