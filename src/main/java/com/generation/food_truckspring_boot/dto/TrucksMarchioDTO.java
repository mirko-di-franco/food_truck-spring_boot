package com.generation.food_truckspring_boot.dto;

import java.util.List;

import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Marchi;

public class TrucksMarchioDTO {

	Marchi marchio;
	
	List<Foodtrucks> trucks;

	public Marchi getMarchio() {
		return marchio;
	}

	public void setMarchio(Marchi marchio) {
		this.marchio = marchio;
	}

	public List<Foodtrucks> getTrucks() {
		return trucks;
	}

	public void setTrucks(List<Foodtrucks> trucks) {
		this.trucks = trucks;
	}

	
	
}
