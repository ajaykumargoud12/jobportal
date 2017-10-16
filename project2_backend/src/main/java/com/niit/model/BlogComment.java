package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="blogcomment_batch4")
public class BlogComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="blogpost_id")
	private BlogPost blogPost;
	@ManyToOne
	@JoinColumn(name="username")
	private User commentedBy;
	private Date commentesOn;
	private String commentText;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BlogPost getBlogPost() {
		return blogPost;
	}
	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}
	public User getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}
	public Date getCommentesOn() {
		return commentesOn;
	}
	public void setCommentesOn(Date commentesOn) {
		this.commentesOn = commentesOn;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	
	

}
