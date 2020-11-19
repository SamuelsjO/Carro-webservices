package com.samuelTI.api.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.samuelTI.api.domain.User;
import com.samuelTI.api.jwt.dto.UserAuthenticateDTO;
import com.samuelTI.api.jwt.dto.UserRegistrationDTO;
import com.samuelTI.api.jwt.services.UserRegistrationService;

@RestController
public class UserRegistrationController {

	private UserRegistrationService userRegistrationService;

	@Autowired
	public UserRegistrationController(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}
	
	public UserRegistrationController() {
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<UserAuthenticateDTO> registrate(@RequestBody UserRegistrationDTO userRegistrationDTO){
		User user = userRegistrationService.registrate(userRegistrationDTO.toUser());
		return new ResponseEntity<UserAuthenticateDTO>(UserAuthenticateDTO.toDTO(user, "Bearer "), HttpStatus.CREATED);
		
	}
}
