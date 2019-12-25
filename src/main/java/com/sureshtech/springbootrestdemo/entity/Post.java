package com.sureshtech.springbootrestdemo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.sureshtech.springbootrestdemo.views.PostView;


@Entity
@Table(name="POSTS")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(PostView.ExternalView.class)
	private Integer id;
	
	@JsonView(PostView.ExternalView.class)
	@Column(name="title", nullable = false,length=150)
	private String title;
	
	@JsonView(PostView.ExternalView.class)
	@Lob
	@Column(name="content", nullable = false,columnDefinition = "TEXT")
	private String content;
	
	@JsonView(PostView.InternalView.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn = new Date();
	
	
	@JsonView({PostView.InternalView.class , PostView.PartnerView.class})
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;
	
	@JsonManagedReference // Commentout this annotation if you want to use @jsonIgnore in Comment.java 
	@OneToMany(cascade = {CascadeType.ALL} , orphanRemoval = true) // with out this cascade property Comments will not be saved when you save the Post object
	@JoinColumn(name="post_id")
	private List<Comment> comments;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	

}
