package com.generation.food_truckspring_boot.dto;

import java.math.BigDecimal;

import com.generation.food_truckspring_boot.entity.Alimentazione;
import com.generation.food_truckspring_boot.entity.Portata;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PiattoDTO {
	
	@NotEmpty(message = "Il nome é richiesto")
	private String nome;
	
	@NotEmpty(message = "descrizione richiesta")
	private String descrizione;
	
	@NotEmpty(message = "immagine richiesta")
	private String immagine;
	
	@NotNull(message = "coordinaterichieste")
	private Alimentazione alimentazione;
	
	@NotEmpty(message = "prezzo è richiesta")
	private BigDecimal prezzoListino;
	
	@NotEmpty(message = "Portata è richiesta")
	private Portata portata;
	
	@NotEmpty(message = "il marchio è richiesto")
	private long marchioId;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public Alimentazione getAlimentazione() {
		return alimentazione;
	}

	public void setAlimentazione(Alimentazione alimentazione) {
		this.alimentazione = alimentazione;
	}

	public BigDecimal getPrezzoListino() {
		return prezzoListino;
	}

	public void setPrezzoListino(BigDecimal prezzoListino) {
		this.prezzoListino = prezzoListino;
	}

	public Portata getPortata() {
		return portata;
	}

	public void setPortata(Portata portata) {
		this.portata = portata;
	}

	public long getMarchioId() {
		return marchioId;
	}

	public void setMarchioId(long marchioId) {
		this.marchioId = marchioId;
	}

	
}
