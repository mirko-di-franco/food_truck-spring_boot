package com.generation.food_truckspring_boot.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.dto.TrucksMarchio;
import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.repository.FoodtrucksRepo;
import com.generation.food_truckspring_boot.repository.MarchiRepo;
import com.generation.food_truckspring_boot.service.FoodtrucksServ;
import com.generation.food_truckspring_boot.service.MarchiServ;

@CrossOrigin("*")
@RestController
@RequestMapping("api/foodtrucks")
public class FoodtrucksRestController {
	
	@Autowired
	FoodtrucksServ foodtrucksServ;
	
	@Autowired
	MarchiServ marchiServ;
	
	@Autowired
	FoodtrucksRepo foodtruckRepo;
	
	@Autowired
	MarchiRepo marchiRepo;
	
	
	@GetMapping
	public List<Foodtrucks> getAll(){
		List<Foodtrucks> trucks = foodtrucksServ.listaTrucks();
		return trucks;
	}
	
	
	 @GetMapping("/marchio/id/{marchioId}")
	    public TrucksMarchio getFoodtrucksByMarchioID(@PathVariable long marchioId) {
		 	//prendo tutti i trucks per id del marchio
	        List<Foodtrucks> trucks = foodtrucksServ.trucksPerMarchio(marchioId);
	        
	        Optional<Marchi> marchio = marchiServ.ricercaMarchio(marchioId);
	        
	        TrucksMarchio trucksMarchio = new TrucksMarchio();
	        trucksMarchio.setTrucks(trucks);
	        trucksMarchio.setMarchio(marchio.get());
	        
	        return trucksMarchio;
	    }
	
	 

	 @GetMapping("/marchio/nome/{nomeMarchio}")
	    public List<Foodtrucks> getFoodtrucksByNomeMarchio(@PathVariable String nomeMarchio) {
	        List<Foodtrucks> trucks = foodtruckRepo.findByMarchiNome(nomeMarchio);
	        return trucks;
	    }
	 
	 
	 @GetMapping("/marchio/{marchioId}/truck/{idTruck}")
	 public ResponseEntity<?> getFoodtruckById(@PathVariable("marchioId") long marchioId,@PathVariable("idTruck") long idTruck){
		 
		 Optional<Marchi> marchi = marchiServ.ricercaMarchio(marchioId);
		 Optional<Foodtrucks> truck = foodtrucksServ.truckPerId(idTruck);
		 
		 if(truck.isEmpty()) {
			 return new ResponseEntity<>(new Foodtrucks(), HttpStatus.NOT_FOUND);
		 }else {
			 return new ResponseEntity<>(truck.get(), HttpStatus.OK);
		 }
	 }
	
	 /*
	 @GetMapping("/marchio/{marchioId}/truck/{idTruck}")
	 public Foodtrucks getFoodtruckByIdProva(@PathVariable("marchioId") long marchioId,@PathVariable("idTruck") long idTruck){
		 
		 Optional<Foodtrucks> truck = marchiRepo.findByMarchiAndFoodtrucksID(marchioId, idTruck);
		 return truck.get();
	 }
	*/

}
