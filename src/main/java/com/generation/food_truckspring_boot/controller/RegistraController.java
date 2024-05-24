//package com.generation.food_truckspring_boot.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.generation.food_truckspring_boot.entity.Utenti;
//import com.generation.food_truckspring_boot.service.UtenteServiceInt;
//
//
//
//
//
//
//
//@Controller
//class RegistraController {
//
//	UtenteServiceInt utenteServiceInt;
//	
//	@GetMapping("/registrazione")
//	public String campoRegistrazione(org.springframework.ui.Model model) {
//		model.addAttribute("utenti",new Utenti());
//		return "registrazione";
//	}
//	
//	@PostMapping("/register")
//	public String registraUtente(@ModelAttribute Utenti utenti,RedirectAttributes redirectAttributes) {
//
//		utenteServiceInt.salva(utenti);
//		redirectAttributes.addFlashAttribute("messaggio","registrazione avvenuta con successo! Fai Login");
//		
//		
//		return "redirect:/login";
//	}
//	
//	
//	
//	
//	
//}
