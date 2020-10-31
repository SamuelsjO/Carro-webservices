package com.samuelTI.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuelTI.api.domain.User;
import com.samuelTI.api.domain.UserService;
import com.samuelTI.api.domain.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping()
	public ResponseEntity<?> get() {
		List<UserDTO> list = service.getUsers();
		return ResponseEntity.ok(list);
	}
	
	
	
	@GetMapping("/info")
	public UserDTO userInfo(@AuthenticationPrincipal User user) {
		return UserDTO.create(user);
	}
}
