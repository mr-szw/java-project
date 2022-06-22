package com.dawei.boot.boothelloword.graphql.fetcher;

import com.dawei.boot.boothelloword.graphql.entry.AuthorInfo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author sinbad on 2021/5/11.
 */
public class AuthorDataFetcher implements DataFetcher<AuthorInfo> {
	@Override
	public AuthorInfo get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

		Object authorIdObj = dataFetchingEnvironment.getArgument("authorId");

		int authorId = authorIdObj == null ? 0 : (int) authorIdObj;
		System.out.println(authorId);

		AuthorInfo authorInfo = new AuthorInfo();
		authorInfo.setAuthorId(12);
		authorInfo.setAuthorName("臭的名字");

		return authorInfo;
	}


}
