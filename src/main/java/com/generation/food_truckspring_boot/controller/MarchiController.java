package com.generation.food_truckspring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.service.MarchiServ;

@Controller
@RequestMapping("/marchi")
public class MarchiController {

	@Autowired
	MarchiServ marchiServ;
	
	// significa che questa rotta funziona sia con "http://localhost:8080/marchi" oppure con "http://localhost:8080/marchi/"
		@GetMapping({"", "/"})
		//il MODEL è un interfaccia di spring che viene utilizzata per passare dati dal controller alla vista
		public String listaUtenti(Model model) {
			List<Marchi> marchi = marchiServ.listaMarchi();
			model.addAttribute("marchi", marchi);
			
			return "marchi/indexMarchi";
		}
}
