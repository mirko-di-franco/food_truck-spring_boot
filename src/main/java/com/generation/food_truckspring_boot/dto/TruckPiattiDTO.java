package com.generation.food_truckspring_boot.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.entity.Piatti;

public class TruckPiattiDTO {

	Foodtrucks foodtrucks;
	
	List<Piatti> piatti;
	
	@JsonIgnore
	Marchi marchi;
	
	private String nomeMarchio;
	private String videoMarchio;

	public Foodtrucks getFoodtrucks() {
		return foodtrucks;
	}

	public void setFoodtrucks(Foodtrucks foodtrucks) {
		this.foodtrucks = foodtrucks;
	}

	public List<Piatti> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatti> piatti) {
		this.piatti = piatti;
	}

	public Marchi getMarchi() {
		return marchi;
	}

	public void setMarchi(Marchi marchi) {
		this.marchi = marchi;
	}

	public String getNomeMarchio() {
		return nomeMarchio;
	}

	public void setNomeMarchio(String nomeMarchio) {
		this.nomeMarchio = nomeMarchio;
	}

	public String getVideoMarchio() {
		return videoMarchio;
	}

	public void setVideoMarchio(String videoMarchio) {
		this.videoMarchio = videoMarchio;
	}
	
	
}
