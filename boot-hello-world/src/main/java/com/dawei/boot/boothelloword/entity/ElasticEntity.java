//package com.dawei.boot.boothelloword.entity;
//
//import java.util.Date;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//
///**
// * @author by Dawei on 2019/5/5.
// * Es 查询用到的实体
// */
//@Document(indexName = "product", type = "book")
//public class ElasticEntity {
//
//    @Id
//    private String id;
//
//    private String name;
//
//    private String message;
//
//    private Date postDate;
//
//    private String type;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Date getPostDate() {
//        return postDate;
//    }
//
//    public void setPostDate(Date postDate) {
//        this.postDate = postDate;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//}
