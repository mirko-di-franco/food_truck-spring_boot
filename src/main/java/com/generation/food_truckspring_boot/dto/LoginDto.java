package com.generation.food_truckspring_boot.dto;

import jakarta.validation.constraints.NotEmpty;


//DTO LOGIN PER ACCEDERE AL PANNELLO DI CONTROLLO DELL'AMMINISTRATORE 
public class LoginDto {
	@NotEmpty(message = "L'email é richiesta")
	private String email;
	
	@NotEmpty(message = "La password é richiesta")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
