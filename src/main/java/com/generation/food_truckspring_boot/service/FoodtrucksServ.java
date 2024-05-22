package com.generation.food_truckspring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.generation.food_truckspring_boot.repository.FoodtrucksRepo;

@Repository
public class FoodtrucksServ {

	@Autowired
	FoodtrucksRepo foodtrucksRepo;
}
