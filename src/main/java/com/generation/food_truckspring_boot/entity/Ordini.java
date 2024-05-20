package com.generation.food_truckspring_boot.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordini")
public class Ordini {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate data_ordine;
	
	private int numero_ordine;
	
	private BigDecimal totale_ordine;

	
	
	
	//GET E SETTER
	
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
	
	
	
	
}
