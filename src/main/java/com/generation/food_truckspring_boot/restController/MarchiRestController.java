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

import com.generation.food_truckspring_boot.dto.TruckPiattiDTO;
import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Genere;
import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.entity.Piatti;
import com.generation.food_truckspring_boot.service.FoodtrucksServ;
import com.generation.food_truckspring_boot.service.MarchiServ;

@CrossOrigin("*")
@RestController
@RequestMapping("api/marchi")
public class MarchiRestController {

	@Autowired
	MarchiServ marchiServ;
	
	@Autowired
	FoodtrucksServ foodtruckServ;
	
	
	@GetMapping
	public List<Marchi> getAll(){
		List<Marchi> marchi = marchiServ.listaMarchi();
		return marchi;
	}
	
	
	@GetMapping("/{idMarchio}")
	public ResponseEntity<?> ricercaMarchio(@PathVariable("idMarchio") long idMarchio){
		Optional<Marchi> marchio = marchiServ.ricercaMarchio(idMarchio);
		if(marchio.isEmpty()) {
			return new ResponseEntity<>(new Marchi(), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(marchio.get(), HttpStatus.OK);
		}
	}
	
	
	
	@GetMapping("truck/{truckId}")
	public ResponseEntity<TruckPiattiDTO> getPiatti(@PathVariable("truckId") long truckId) {
		
		Optional<Foodtrucks> truckPerId = foodtruckServ.truckPerId(truckId);
		
		if(!truckPerId.isEmpty()) {
			Foodtrucks foodtruck = truckPerId.get();
			
			//prendo i piatti 
			List<Piatti> piatti = foodtruck.getMarchi().getPiatti();
			Marchi marchio = foodtruck.getMarchi();
			String nomeMarchio = marchio.getNome();
			String videoMarchio = marchio.getVideo();
			Genere genereMarchio = marchio.getGenere();
			
			TruckPiattiDTO truckPiatti = new TruckPiattiDTO();
			truckPiatti.setPiatti(piatti);
			truckPiatti.setFoodtrucks(foodtruck);
			truckPiatti.setNomeMarchio(nomeMarchio);
			truckPiatti.setGenere(genereMarchio);
			truckPiatti.setVideoMarchio(videoMarchio);
			return new ResponseEntity<TruckPiattiDTO>(truckPiatti, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<TruckPiattiDTO>(new TruckPiattiDTO(), HttpStatus.NOT_FOUND);
		}
	}
	
	
}
