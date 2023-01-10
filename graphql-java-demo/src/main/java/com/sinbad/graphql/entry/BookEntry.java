package com.sinbad.graphql.entry;

import lombok.Data;

/**
 * @author sinbad on 2023/1/11 - 12:53 AM.
 **/
@Data
public class BookEntry {

    private String bookName;

    private AuthorEntry bookAuthor;
}
