package com.diplomski.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diplomski.blog.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
