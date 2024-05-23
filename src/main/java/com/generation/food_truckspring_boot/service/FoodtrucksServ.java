package com.generation.food_truckspring_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.repository.FoodtrucksRepo;

@Repository
public class FoodtrucksServ {

	@Autowired
	FoodtrucksRepo foodtrucksRepo;
	
	//LISTA TUTTI I TRUCKS
	public List<Foodtrucks> listaTrucks(){
		List<Foodtrucks> trucks = foodtrucksRepo.findAll();
		return trucks;
	}
	
	//LISTA TRUCKS PER MARCHIO
	public List<Foodtrucks> trucksPerMarchio(long id){
        List<Foodtrucks> trucksPerMarchioo = foodtrucksRepo.findByMarchiId(id);
        return trucksPerMarchioo;
    }
	
	
	//LISTA TRUCKS PER MARCHIO
		public Optional<Foodtrucks> truckPerId( long idTruck){
			Optional<Foodtrucks> truck = foodtrucksRepo.findById(idTruck);
			return truck;
		}

	
}
