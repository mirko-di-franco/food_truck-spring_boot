package com.generation.food_truckspring_boot.dto;

import com.generation.food_truckspring_boot.entity.Marchi;

public class MarchioDTO {

	Marchi marchio; 
	
	
	private String nome;
	
	private String video;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marchi getMarchio() {
		return marchio;
	}

	public void setMarchio(Marchi marchio) {
		this.marchio = marchio;
	}
	
	
	
}
