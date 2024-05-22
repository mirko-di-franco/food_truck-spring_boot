package com.generation.food_truckspring_boot.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.service.MarchiServ;

@RestController
@RequestMapping("api/marchi")
public class MarchiRestController {

	@Autowired
	MarchiServ marchiServ;
	
	
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
	
	
}
