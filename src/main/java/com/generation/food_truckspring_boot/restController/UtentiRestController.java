package com.generation.food_truckspring_boot.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.dto.UtentiDTO;
import com.generation.food_truckspring_boot.entity.Utenti;
import com.generation.food_truckspring_boot.service.UtentiServ;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/utenti")
public class UtentiRestController {

	@Autowired
	UtentiServ utentiServ;
	
	@GetMapping("")
	//il MODEL è un interfaccia di spring che viene utilizzata per passare dati dal controller alla vista
	public List<Utenti> listaUtenti(){
		List<Utenti> utenti = utentiServ.listaUtenti();
		return utenti;
	}
	
	
	@GetMapping("/{idUtente}")
	public ResponseEntity<?> ricercaUtente(@PathVariable("idUtente") long idUtente){
		Optional<Utenti> utente = utentiServ.ricercaUtente(idUtente);
		if(utente.isEmpty()) {
			return new ResponseEntity<>(new Utenti(), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(utente.get(), HttpStatus.OK);
		}
	}
	
	
	@PostMapping
	public ResponseEntity<?> aggiuntaUtente(@RequestBody UtentiDTO utenteDTO){
		
		if (utenteDTO.getCognome() == null || utenteDTO.getCognome().isEmpty()) {
	        return new ResponseEntity<>("Il campo 'cognome' non può essere nullo o vuoto", HttpStatus.BAD_REQUEST);
	    }
		
		Utenti utente = new Utenti(
					utenteDTO.getNome(),
					utenteDTO.getCognome(),
					utenteDTO.getData_nascita(),
					utenteDTO.getEmail(),
					utenteDTO.getPassword(),
					utenteDTO.getRuolo());
		
		Utenti newUtente = utentiServ.aggiungiOModifica(utente);
		
		return new ResponseEntity<>(newUtente, HttpStatus.OK);
	}
}
