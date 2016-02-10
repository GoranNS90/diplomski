package com.diplomski.blog.service;

import com.diplomski.blog.model.User;

public interface UserService {
	
	public void addUser(User user);
	
	public User findUserByUsername(String username);
}
