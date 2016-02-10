package com.diplomski.blog.service;

import com.diplomski.blog.model.Comment;

public interface CommentService {

	public void addComment(Comment comment);

	public boolean removeComment(int id);
	
	public Comment findCommentById(int id);

}
