package com.samuelTI.api.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samuelTI.api.domain.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private String login;
	private String nome;
	private String email;

	// Token jwt

	private String token;

	private List<String> roles;

	public static UserDTO create(User user) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO dto = modelMapper.map(user, UserDTO.class);

		dto.roles = user.getRoles().stream().map(role -> role.getNome()).collect(Collectors.toList());
		return dto;
	}

	public static UserDTO create(User user, String token) {
		UserDTO dto = create(user);
		dto.token = token;
		return dto;
	}

	public String toJson() throws JsonProcessingException {
		ObjectMapper m = new ObjectMapper();
		return m.writeValueAsString(this);
	}

	public UserDTO() {

	}

	public UserDTO(String login, String nome, String email, String token, List<String> roles) {
		super();
		this.login = login;
		this.nome = nome;
		this.email = email;
		this.token = token;
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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
