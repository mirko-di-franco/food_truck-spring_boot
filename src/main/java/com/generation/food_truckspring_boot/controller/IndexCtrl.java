package com.generation.food_truckspring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.food_truckspring_boot.entity.Utenti;

import jakarta.servlet.http.HttpSession;



//1) Sessione Http lato server
// @SessionAttributes a volte non funziona bene
// si può usare @Autowired HttpSession session come in UserCtrl  
// e poi  session.getAttribute("user") 

@Controller

public class IndexCtrl {
	
	
	@Autowired
	HttpSession httpSession;
	
	
	@RequestMapping("/")
	public String base( Model model) { 
		//user non serve se si usa session.getAttribute("user") 
		// lo user viene inserito AUTOMATICAMENTE nel model per il template
		// non è necessario aggiungerlo a mano
		//model.addAttribute(user)); non serve	
		
		Utenti user=(Utenti) httpSession.getAttribute("utente");
		model.addAttribute("utente",user);
		return "index";
	}
}