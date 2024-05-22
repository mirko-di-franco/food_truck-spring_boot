package com.generation.food_truckspring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.service.FoodtrucksServ;

@Controller
@RequestMapping("/trucks")
public class TrucksController {

	@Autowired
	FoodtrucksServ foodtrucksServ;
	
	// significa che questa rotta funziona sia con "http://localhost:8080/trucks" oppure con "http://localhost:8080/trucks/"
		@GetMapping({"", "/"})
		//il MODEL è un interfaccia di spring che viene utilizzata per passare dati dal controller alla vista
		public String listaUtenti(Model model) {
			List<Foodtrucks> trucks = foodtrucksServ.listaTrucks();
			model.addAttribute("trucks", trucks);
			
			return "trucks/indexTrucks";
		}
}
