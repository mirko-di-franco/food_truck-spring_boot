package com.generation.food_truckspring_boot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "trucks")
public class Foodtrucks {

	/**
	 * @param id
	 * @param nome
	 * @param descrizione
	 * @param indirizzo
	 * @param coordinateGps
	 * @param disponibilità
	 * @param immagine
	 */
	public Foodtrucks(long id, String nome, String descrizione, String indirizzo, String coordinateGps,
			boolean disponibilità, String immagine) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.indirizzo = indirizzo;
		this.coordinateGps = coordinateGps;
		this.disponibilità = disponibilità;
		this.immagine = immagine;
	}
	/**
	 * 
	 */
	public Foodtrucks() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String nome;
	@Column(columnDefinition = "TEXT", nullable = false)
	private String descrizione;
	@Column(nullable = false)
	private String indirizzo;
	@Column(nullable = false)
	private String coordinateGps;
	@Column(nullable = false)
	private boolean disponibilità;
	@Column(nullable = false)
	private String immagine;
	
	//ONE TO MANY A ORDINI
	@OneToMany(mappedBy = "foodtrucks")
	private List<Ordini> ordini;
	
	//MANY TO ONE A MARCHI
	@ManyToOne
	@JoinColumn(name = "marchio_id")
	@JsonIgnore
	private Marchi marchi;
	
	
	
	
	
	public Marchi getMarchi() {
		return marchi;
	}
	public void setMarchi(Marchi marchi) {
		this.marchi = marchi;
	}
	public List<Ordini> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<Ordini> ordini) {
		this.ordini = ordini;
	}
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
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCoordinateGps() {
		return coordinateGps;
	}
	public void setCoordinateGps(String coordinateGps) {
		this.coordinateGps = coordinateGps;
	}
	public boolean isDisponibilità() {
		return disponibilità;
	}
	public void setDisponibilità(boolean disponibilità) {
		this.disponibilità = disponibilità;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	
	
	
}
