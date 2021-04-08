package com.deybson.sisDeCarros.api.model;

import javax.validation.constraints.NotBlank;

public class CarInput {
	
	@NotBlank
	private int ano;
	
	@NotBlank
	private String licensePlate;
	
	@NotBlank
	private String model;
	
	@NotBlank
	private String color;
	
	@NotBlank
	private Long idUser;

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	
	
}

