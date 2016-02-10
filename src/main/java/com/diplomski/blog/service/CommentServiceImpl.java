package com.diplomski.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diplomski.blog.model.Comment;
import com.diplomski.blog.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void addComment(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public boolean removeComment(int id) {
		commentRepository.delete(id);
		return true;
	}

	@Override
	public Comment findCommentById(int id) {
		return commentRepository.findOne(id);
	}

}
