package com.diplomski.blog.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "content")
	private String content;

	@Column(name = "date")
	private Date date;

	@JsonBackReference("role")
	@JoinColumn(name = "post", referencedColumnName="id")
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Post post;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		return df.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
