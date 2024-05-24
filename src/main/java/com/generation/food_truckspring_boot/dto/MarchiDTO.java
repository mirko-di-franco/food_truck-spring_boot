package com.generation.food_truckspring_boot.dto;

import com.generation.food_truckspring_boot.entity.Genere;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MarchiDTO {

	@NotEmpty(message = "Il nome é richiesto")
	private String nome;
	
	@NotEmpty(message = "La descrizione é richiesta")
	private String descrizione;
	
	@NotNull(message = "Il genere é richiesto")
	private Genere genere;
	
	@NotEmpty(message = "Il logo é richiesto")
	private String logo;
	
	@NotEmpty(message = "Il logo é richiesto")
	private String video;

	
	
	
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

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	
	
	
}
