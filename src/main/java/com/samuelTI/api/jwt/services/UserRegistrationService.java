package com.samuelTI.api.jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuelTI.api.domain.User;
import com.samuelTI.api.domain.UserRepository;

@Service
public class UserRegistrationService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;
	
//	@Autowired
//	public UserRegistrationService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
	
	public User registrate(User user) {
		user.setToken(tokenService.generatetoken(user));
		return userRepository.save(user);
	}
}
