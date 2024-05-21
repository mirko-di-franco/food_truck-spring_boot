package com.generation.food_truckspring_boot.dto;

import java.time.LocalDate;

import com.generation.food_truckspring_boot.entity.Ruolo;

import jakarta.validation.constraints.NotEmpty;

public class UtentiDTO {

	//messaggio errore se non compili il campo
	@NotEmpty(message = "Il nome é richiesto")
	private String nome;
	
	@NotEmpty(message = "Il cognome é richiesto")
	private String cognome;
	
	@NotEmpty(message = "La data di nascita é richiesta")
	private LocalDate data_nascita;
	
	@NotEmpty(message = "L'email é richiesta")
	private String email;
	
	@NotEmpty(message = "La password é richiesta")
	private String password;
	
	@NotEmpty(message = "La password é richiesta")
	private Ruolo ruolo;

	
	
	//GETTER E SETTER
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(LocalDate data_nascita) {
		this.data_nascita = data_nascita;
	}

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

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	
	
	
}
