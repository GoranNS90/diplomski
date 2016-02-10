package com.diplomski.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diplomski.blog.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	public User findByUsername(String username);
}
