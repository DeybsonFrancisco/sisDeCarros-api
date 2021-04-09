package com.deybson.sisDeCarros.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarInput {
	
	@NotNull
	private int year;
	
	@NotBlank
	private String licensePlate;
	
	@NotBlank
	private String model;
	
	@NotBlank
	private String color;
	
	@NotNull
	private Long userId;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId= userId;
	}
	
	
	
}

