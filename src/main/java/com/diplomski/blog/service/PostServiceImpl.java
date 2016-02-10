package com.diplomski.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diplomski.blog.model.Post;
import com.diplomski.blog.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public void addPost(Post post) {
		postRepository.save(post);
	}

	@Override
	public List<Post> getAll() {
		return (List<Post>) postRepository.findAll();
	}

	@Override
	public Post findPostById(int id) {
		return postRepository.findOne(id);
	}

	@Override
	public void deletePost(int id) {
		postRepository.delete(id);
	}

}
