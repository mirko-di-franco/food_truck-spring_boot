package com.generation.food_truckspring_boot.dto;

import java.util.List;

import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.entity.Piatti;

public class TruckPiattiDTO {

	Foodtrucks foodtrucks;
	
	List<Piatti> piatti;
	
	Marchi marchi;

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
	
	
}
