package com.deybson.sisDeCarros.api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserRepresantation {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthDay;
	private String login;
	private String password ;
	private String phone;
	private List<CarRepresantation> cars;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<CarRepresantation> getCars() {
		return cars;
	}
	public void setCars(List<CarRepresantation> cars) {
		this.cars = cars;
	}
	
	

}
