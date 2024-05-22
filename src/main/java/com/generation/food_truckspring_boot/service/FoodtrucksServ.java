package com.generation.food_truckspring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.repository.FoodtrucksRepo;

@Repository
public class FoodtrucksServ {

	@Autowired
	FoodtrucksRepo foodtrucksRepo;
	
	public List<Foodtrucks> listaTrucks(){
		List<Foodtrucks> trucks = foodtrucksRepo.findAll();
		return trucks;
	}
	
	
}
