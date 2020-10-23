package com.samuelTI.api.domain.dto;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samuelTI.api.domain.User;

public class UserDTO {

	private String login;
	private String nome;
	private String email;
	
	// Token jwt
	
	private String token;
	
	public static UserDTO create(User user, String token) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO dto = modelMapper.map(user, UserDTO.class);
		dto.token = token;
		return dto;
	}
	
	public String toJson() throws JsonProcessingException {
		ObjectMapper m = new ObjectMapper();
		return m.writeValueAsString(this);
	}
	
	
	public UserDTO() {
		
	}


	public UserDTO(String login, String nome, String email, String token) {
		super();
		this.login = login;
		this.nome = nome;
		this.email = email;
		this.token = token;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
}
