package com.generation.food_truckspring_boot.restController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.dto.OrdiniDTO;
import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Ordini;
import com.generation.food_truckspring_boot.entity.Piatti;
import com.generation.food_truckspring_boot.entity.Utenti;
import com.generation.food_truckspring_boot.service.FoodtrucksServ;
import com.generation.food_truckspring_boot.service.OrdiniServ;
import com.generation.food_truckspring_boot.service.PiattiServ;
import com.generation.food_truckspring_boot.service.UtentiServ;

@CrossOrigin("*")
@RestController
@RequestMapping("api/ordini")
public class OrdiniRestController {
	
	@Autowired
	OrdiniServ ordiniServ;
	
	@Autowired
	UtentiServ utentiServ;
	
	@Autowired
	FoodtrucksServ foodtruckServ;
	
	@Autowired
	PiattiServ piattiServ;
	
	
	@GetMapping
	public List<Ordini> getAll(){
		List<Ordini> ordini = ordiniServ.listaOrdini();
		return ordini;
	}
	
	
	
	@PostMapping("/utente/{idUtente}/truck/{idTruck}")
	public ResponseEntity<?> aggiuntaOrdine(@RequestBody OrdiniDTO ordineDTO, @PathVariable("idUtente") long idUtente, @PathVariable("idTruck") long idTruck){
		
		Optional<Utenti> utente = utentiServ.ricercaUtente(idUtente);
		Optional<Foodtrucks> truckPerId = foodtruckServ.truckPerId(idTruck);
		
		
		
		
		
		Ordini ordine = new Ordini();
		
		
		ordine.setData_ordine(ordineDTO.getData_ordine());
		ordine.setNumero_ordine(ordineDTO.getNumero_ordine());
		ordine.setTotale_ordine(ordineDTO.getTotale_ordine());
		ordine.setFoodtrucks(truckPerId.get());
		ordine.setUtenti(utente.get());
		//creo una arraylist vuota di piatti
		List<Piatti> piatti = new ArrayList<>();
		//faccio il foreach
		ordineDTO.getPiatti().stream().forEach(p -> {
			//cerco il piatto per id
			Optional<Piatti> piattoPerId = piattiServ.piattoPerId(p.getId());
			//e lo aggiungo alla arraylist
			piatti.add(piattoPerId.get());
		});
		//aggiungo l arraylist all'ordine
		ordine.setPiatti(piatti);
		
		
		
				
	Ordini newOrdine = ordiniServ.aggiuntaOModificaOrdine(ordine);
		
		return new ResponseEntity<Ordini>(newOrdine, HttpStatus.OK);
		
	}
	
	
	

}
