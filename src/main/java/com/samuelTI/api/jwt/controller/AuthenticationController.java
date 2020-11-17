package com.samuelTI.api.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.samuelTI.api.domain.DadosLogin;
import com.samuelTI.api.domain.User;
import com.samuelTI.api.jwt.dto.UserAuthenticateDTO;
import com.samuelTI.api.jwt.services.UserAuthenticationService;

@RestController
public class AuthenticationController {

	private UserAuthenticationService authenticationService;
	
	@Autowired
	public AuthenticationController(UserAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	public AuthenticationController() {
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserAuthenticateDTO> autenticar(@RequestBody DadosLogin dados, @RequestHeader String Authorization){
		User user = authenticationService.authenticate(dados, Authorization);
		return new ResponseEntity<UserAuthenticateDTO>(UserAuthenticateDTO.toDTO(user, "Bearer "), HttpStatus.ACCEPTED);
	}
}
