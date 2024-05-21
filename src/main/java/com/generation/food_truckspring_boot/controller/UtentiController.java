package com.generation.food_truckspring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.food_truckspring_boot.dto.UtentiDTO;
import com.generation.food_truckspring_boot.entity.Utenti;
import com.generation.food_truckspring_boot.service.UtentiServ;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/utenti")
public class UtentiController {

	@Autowired
	UtentiServ utentiServ;
	
	
	
	// significa che questa rotta funziona sia con "http://localhost:8080/api/utenti" oppure con "http://localhost:8080/api/utenti/"
	@GetMapping({"", "/"})
	//il MODEL è un interfaccia di spring che viene utilizzata per passare dati dal controller alla vista
	public String listaUtenti(Model model) {
		List<Utenti> utenti = utentiServ.listaUtenti();
		model.addAttribute("utenti", utenti);
		
		return "utenti/index";
	}
	
	
	
	//FORM CREAZIONE 
	@GetMapping("/nuovoUtente")
	public String paginaNuovoUtente(Model model) {
		UtentiDTO utenteDTO = new UtentiDTO();
		model.addAttribute("utenteDTO", utenteDTO);
		return "utenti/creazioneUtente";
	}
	
	
	//-------
	@PostMapping("/nuovoUtente")
	//@valid : esegue la validazione di UtentiDTO utilizzando le annotazioni di validazione presenti nella classe UtentiDTO, come @NotNull, @Size
	//@ModelAttribute: Spring cerca di legare i dati del form (o altri parametri della richiesta) a un'istanza di UtentiDTO.
	//@BindingResult: contiene i risultati della validazione di UtentiDTO. Può essere usato per verificare se ci sono stati errori di binding o di validazione.
	public String nuovoUtente(@Valid @ModelAttribute UtentiDTO utenteDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			return "utenti/creazioneUtente";
		}
		
		Utenti utente = new Utenti();
		
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setData_nascita(utenteDTO.getData_nascita());
		utente.setEmail(utenteDTO.getEmail());
		utente.setPassword(utenteDTO.getPassword());
		utente.setRuolo(utenteDTO.getRuolo());
		
		utentiServ.aggiungiOModifica(utente);
		
		return "redirect:/utenti";
	}
	
	
	
	@GetMapping("/modificaUtente")
	public String paginaModificaUtente(Model model, @RequestParam long idUtente ) {
		try {
			Utenti utente = utentiServ.ricercaUtente(idUtente).get();
			model.addAttribute("utente", utente);
			
			UtentiDTO utenteDTO = new UtentiDTO();
			
			utenteDTO.setNome(utente.getNome());
			utenteDTO.setCognome(utente.getCognome());
			utenteDTO.setData_nascita(utente.getData_nascita());
			utenteDTO.setEmail(utente.getEmail());
			utenteDTO.setPassword(utente.getPassword());
			utenteDTO.setRuolo(utente.getRuolo());
			
			model.addAttribute("utenteDTO", utenteDTO);
			
		}catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return "utenti/index";
		}
		return "utenti/modificaUtente";
	}
	
	
	
	
	@PostMapping("/modificaUtente")
	public String modificaUtente(Model model, @RequestParam long idUtente, @Valid @ModelAttribute UtentiDTO utenteDTO, BindingResult result) {
		
		try {
			Utenti utente = utentiServ.ricercaUtente(idUtente).get();
			model.addAttribute("utente", utente);
		
			if(result.hasErrors()) {
				return "utenti/modificaUtente";
			}
			
			
			utente.setNome(utenteDTO.getNome());
			utente.setCognome(utenteDTO.getCognome());
			utente.setEmail(utenteDTO.getEmail());
			utente.setPassword(utenteDTO.getPassword());
			utente.setRuolo(utenteDTO.getRuolo());
			
			utentiServ.aggiungiOModifica(utente);
			
		}catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			System.err.println("Utente non trovato");
		}
		
		//SE SONO RIUSCITO A MODIFICA ALLORA MANDO ALLA HOME DEGLI UTENTI
		return "redirect:/utenti";
		}
	
	
	
	
	@GetMapping("/eliminazione")
	public String eliminazioneUtente(@RequestParam long idUtente) {
		try {
			Utenti utente = utentiServ.ricercaUtente(idUtente).get();
			
			utentiServ.eliminaUtente(utente);
			
		}catch(Exception e) {
			System.out.println("Errore: "+e.getMessage());
		}
		
		return "redirect:/utenti";
	}
	
		
	
	
}
