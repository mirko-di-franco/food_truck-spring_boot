package com.generation.food_truckspring_boot.dto;

import java.util.List;

import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Marchi;

public class MarchiTrucksDTO {

	List<Marchi> marchi;
	
	List<Foodtrucks> trucks;
	
	
	

	public List<Marchi> getMarchi() {
		return marchi;
	}

	public void setMarchi(List<Marchi> marchi) {
		this.marchi = marchi;
	}

	public List<Foodtrucks> getTrucks() {
		return trucks;
	}

	public void setTrucks(List<Foodtrucks> trucks) {
		this.trucks = trucks;
	}
	
	
	
	
}
