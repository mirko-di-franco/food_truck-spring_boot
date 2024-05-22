package com.generation.food_truckspring_boot.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.repository.FoodtrucksRepo;
import com.generation.food_truckspring_boot.service.FoodtrucksServ;
import com.generation.food_truckspring_boot.service.MarchiServ;

@RestController
@RequestMapping("api/foodtrucks")
public class FoodtrucksRestController {
	
	@Autowired
	FoodtrucksServ foodtrucksServ;
	
	@Autowired
	MarchiServ marchiServ;
	
	@Autowired
	FoodtrucksRepo foodtruckRepo;
	
	
	@GetMapping
	public List<Foodtrucks> getAll(){
		List<Foodtrucks> trucks = foodtrucksServ.listaTrucks();
		return trucks;
	}
	
	
	 @GetMapping("/marchio/{marchioId}")
	    public List<Foodtrucks> getFoodtrucksByBrandId(@PathVariable long marchioId) {
	        List<Foodtrucks> trucks = foodtrucksServ.trucksPerMarchio(marchioId);
	        return trucks;
	    }
	
	
	

}
