package com.samuelTI.api.jwt.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.samuelTI.api.domain.User;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserResponserDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;

	public UserResponserDTO(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public UserResponserDTO() {

	}

	public UserResponserDTO toDTO(User user) {
		return new UserResponserDTO(user.getNome(), user.getEmail(), user.getSenha());
	}

	public Long getId() {
		return id;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
