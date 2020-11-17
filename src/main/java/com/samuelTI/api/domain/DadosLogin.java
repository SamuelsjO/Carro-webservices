package com.samuelTI.api.domain;

public class DadosLogin {
	private String senha;
	private String email;
	
	
	public DadosLogin() {
		
	}


	public DadosLogin(String senha, String email) {
		super();
		this.senha = senha;
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
