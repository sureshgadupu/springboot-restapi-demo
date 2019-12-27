package com.sureshtech.springbootrestdemo.dto;

import java.util.Date;

public class PostMsDto {
	private int id;
	private String title;
	private String postContent;
	private Date createdDate;
	
	
	public PostMsDto() {
		super();
	}
	
	public PostMsDto(int id, String title, String postContent, Date createdDate) {
		super();
		this.id = id;
		this.title = title;
		this.postContent = postContent;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	} 
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
