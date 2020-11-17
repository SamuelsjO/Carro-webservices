package com.samuelTI.api.jwt.dto;

import com.samuelTI.api.domain.User;

public class UserAuthenticateDTO {

	private String tipo;
	private String email;
	private String nome;
	private String token;

	public UserAuthenticateDTO() {

	}

	public UserAuthenticateDTO(String tipo, String email, String nome, String token) {
		super();
		this.tipo = tipo;
		this.email = email;
		this.nome = nome;
		this.token = token;
	}

	public static UserAuthenticateDTO toDTO(User user, String tipo) {
		return new UserAuthenticateDTO(user.getEmail(), user.getNome(), user.getToken(), tipo);

	}

	public String getTipo() {
		return tipo;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getToken() {
		return token;
	}



}
