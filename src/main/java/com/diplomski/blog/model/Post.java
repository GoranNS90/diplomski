package com.diplomski.blog.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "date")
	private Date date;

	@Column(name = "content")
	private String content;

	@Column(name = "title")
	private String title;

	@Column(name = "username")
	private String username;

	@JsonManagedReference("role")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments = new ArrayList<Comment>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		return df.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
