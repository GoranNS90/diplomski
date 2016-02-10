package com.diplomski.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diplomski.blog.model.User;
import com.diplomski.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addUser(User user) {
		userRepository.save(user);

	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
