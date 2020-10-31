package com.samuelTI.api.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuelTI.api.domain.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRep;
	
	public List<UserDTO> getUsers() {
		List<UserDTO> list = userRep.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
		return list;
	}
}
