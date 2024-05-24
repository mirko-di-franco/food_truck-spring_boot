package com.generation.food_truckspring_boot.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.generation.food_truckspring_boot.entity.Piatti;

public class OrdiniDTO {

	private LocalDate data_ordine;
	
	private int numero_ordine;
	
	private BigDecimal totale_ordine;
	
	List<Piatti> piatti;
	
	private long utente;
	
	private long truck;

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

	public List<Piatti> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatti> piatti) {
		this.piatti = piatti;
	}

	public long getUtente() {
		return utente;
	}

	public void setUtente(long utente) {
		this.utente = utente;
	}

	public long getTruck() {
		return truck;
	}

	public void setTruck(long truck) {
		this.truck = truck;
	}

	
}
