package com.diplomski.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diplomski.blog.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer>{

}
