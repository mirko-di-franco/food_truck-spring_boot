package com.generation.food_truckspring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//BUTTON LOGOUT SE PREMUTO LA SESSIONE FINISCE E RITORNA A LOGIN
@Controller
public class LogoutController {

	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if (session!=null) {
			session.invalidate();
			
		}
		return "redirect:/login";
		
	}
	
}
