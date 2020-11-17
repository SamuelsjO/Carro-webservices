package com.samuelTI.api.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.samuelTI.api.jwt.services.UserRegistrationService;

@RestController
public class UserRegistrationController {

	private UserRegistrationService userRegistrationService;

	@Autowired
	public UserRegistrationController() {
		return;
	}
}
