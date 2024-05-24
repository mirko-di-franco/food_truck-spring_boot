package com.generation.food_truckspring_boot.restController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.entity.Piatti;
import com.generation.food_truckspring_boot.service.PiattiServ;

@CrossOrigin("*")
@RestController
@RequestMapping("api/piatti")
public class PiattiRestController {

	@Autowired
	PiattiServ piattiServ;
	
	
	@GetMapping("/piatto/{piattoId}")
	public ResponseEntity<?> findPiattoById(@PathVariable("piattoId") long idPiatto){
		
		Optional<Piatti> piatto = piattiServ.piattoPerId(idPiatto);
		
		if(piatto.isEmpty()) {
			return new ResponseEntity<Piatti>(new Piatti(), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Piatti>(piatto.get(), HttpStatus.OK);
		}
	}
	
}
