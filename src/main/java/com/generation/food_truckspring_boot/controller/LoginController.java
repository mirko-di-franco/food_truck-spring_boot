package com.generation.food_truckspring_boot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.generation.food_truckspring_boot.entity.Ruolo;
import com.generation.food_truckspring_boot.entity.Utenti;
import com.generation.food_truckspring_boot.service.UtentiServ;

import jakarta.servlet.http.HttpSession;




@Controller
public class LoginController {

	@Autowired
	UtentiServ utentiServ;
	
	
	
	
	// Sessione Http lato server
		// può anche venire iniettata come parametro di un singolo metodo
		@Autowired
		HttpSession session;
	
		
		@GetMapping("/login")
		public String loginpage(Model model) {
			model.addAttribute("messaggio", "");
			model.addAttribute("esito", true);
			model.addAttribute("utente", new Utenti());
			return "login";
		}
		
		@PostMapping("/login")
		public String login(Utenti utente, Ruolo ruolo, Model model) { // dati provenienti dal form vengono settati nello user

			// Tiene traccia della sessione attraverso un cookie "JSESSIONID";
			// viene recuperato automaticamente con @SessionAttributes("utente") negli altri controllers
			
			// setto l'utente in sessione
			Optional<Utenti>utenteOpt = utentiServ.findByEmailAndPasswordAndRuolo(utente.getEmail(), utente.getPassword(),utente.getRuolo());
			if (utenteOpt.isPresent() && utenteOpt.get().getRuolo().equals(ruolo)) {
				session.setAttribute("utente", utenteOpt.get()); 
				return "redirect:/";
				
				
			}else {
				
				model.addAttribute("messaggio", "accesso negato");
				model.addAttribute("esito", false);
				model.addAttribute("utente", utente);
				
				return "login";
			}
			
		}
		
		@GetMapping("/logout")
		public String logout() {
			
			// cancello la sessione, e con esso l'utente salvato
			session.invalidate();
			return "redirect:/";
		}
	
}
