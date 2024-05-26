package com.generation.food_truckspring_boot.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.repository.UtentiRepo;

@CrossOrigin("*")
@RestController
public class ChekEmailRestController {

	@Autowired 
	UtentiRepo utentiRepo;
	
	@GetMapping("/registrati")
	public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
		boolean exists=utentiRepo.existsByEmail(email);
		return ResponseEntity.ok(exists);
	}
	
}
