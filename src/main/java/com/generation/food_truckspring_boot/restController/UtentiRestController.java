package com.generation.food_truckspring_boot.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.entity.Utenti;
import com.generation.food_truckspring_boot.service.UtentiServ;

@RestController
@RequestMapping("api/utenti")
public class UtentiRestController {

	@Autowired
	UtentiServ utentiServ;
	
	@GetMapping("/lista")
	//il MODEL è un interfaccia di spring che viene utilizzata per passare dati dal controller alla vista
	public List<Utenti> listaUtenti(){
		List<Utenti> utenti = utentiServ.listaUtenti();
		return utenti;
	}
	
	
	@GetMapping("/{idUtente}")
	public ResponseEntity<?> ricercaUtente(long idUtente){
		Optional<Utenti> utente = utentiServ.ricercaUtente(idUtente);
		if(utente.isEmpty()) {
			return new ResponseEntity<>(new Utenti(), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
