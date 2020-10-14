package com.samuelTI.api.domain.dto;

import org.modelmapper.ModelMapper;

import com.samuelTI.api.domain.Carro;

public class CarroDTO {

	private Long id;
	private String nome;
	private String tipo;
	
	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}
	
	
	public CarroDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
