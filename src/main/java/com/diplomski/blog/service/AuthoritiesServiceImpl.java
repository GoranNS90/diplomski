package com.diplomski.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diplomski.blog.model.Authorities;
import com.diplomski.blog.model.User;
import com.diplomski.blog.repository.AuthoritiesRepository;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	private static final String ROLE_USER = "ROLE_USER";

	@Override
	public void addAuthorities(User user) {
		Authorities authorities = new Authorities();
		authorities.setUsername(user.getUsername());
		authorities.setAuthority(ROLE_USER);
		authoritiesRepository.save(authorities);
	}

}
