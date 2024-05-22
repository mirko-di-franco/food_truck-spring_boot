package com.generation.food_truckspring_boot.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "piatti")
public class Piatti {

	/**
	 * 
	 */
	public Piatti() {
	}
	/**
	 * @param id
	 * @param nome
	 * @param descrizione
	 * @param alimentazione
	 * @param prezzoListino
	 * @param portata
	 */
	public Piatti(long id, String nome, String descrizione, Alimentazione alimentazione, BigDecimal prezzoListino,
			Portata portata) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.alimentazione = alimentazione;
		this.prezzoListino = prezzoListino;
		this.portata = portata;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Alimentazione alimentazione;
	
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal prezzoListino;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Portata portata;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
