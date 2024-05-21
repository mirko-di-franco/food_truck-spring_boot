package com.generation.food_truckspring_boot.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordini")
public class Ordini {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate data_ordine;
	
	private int numero_ordine;
	
	@Column(precision = 8, scale = 2)
	private BigDecimal totale_ordine;

	
	//CREO LA RELAZIONE, MANY TO ONE VA INSERITA NELLA TABELLA CHE HA LA FOREIGN KEY
	@ManyToOne
	// GLI DICO QUAL'é LA FOREIGN KEY CHE VERRà COLLEGATA ALL'ID DI UTENTI
	@JoinColumn(name = "utente_id")
	//RELAZIONE CON L'ENTITà DELLA CLASSE USER
	@JsonIgnore
	private Utenti utenti;
	
	
	//GETTER E SETTER
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData_ordine() {
		return data_ordine;
	}

	public void setData_ordine(LocalDate data_ordine) {
		this.data_ordine = data_ordine;
	}

	public int getNumero_ordine() {
		return numero_ordine;
	}

	public void setNumero_ordine(int numero_ordine) {
		this.numero_ordine = numero_ordine;
	}

	public BigDecimal getTotale_ordine() {
		return totale_ordine;
	}

	public void setTotale_ordine(BigDecimal totale_ordine) {
		this.totale_ordine = totale_ordine;
	}

	public Utenti getUtenti() {
		return utenti;
	}

	public void setUtenti(Utenti utenti) {
		this.utenti = utenti;
	}
	
	
	
	
}
