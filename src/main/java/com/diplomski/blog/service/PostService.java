package com.diplomski.blog.service;

import java.util.List;

import com.diplomski.blog.model.Post;

public interface PostService {
	
	public void addPost(Post post);
	
	public List<Post> getAll();
	
	public Post findPostById(int id);
	
	public void deletePost(int id);
}
