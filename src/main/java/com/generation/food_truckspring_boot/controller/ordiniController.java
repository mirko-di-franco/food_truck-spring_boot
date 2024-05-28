package com.generation.food_truckspring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.food_truckspring_boot.entity.Ordini;
import com.generation.food_truckspring_boot.service.OrdiniServ;

@Controller
@RequestMapping("/ordini")
public class ordiniController {

	@Autowired
	OrdiniServ ordiniServ;
	
	@GetMapping({"", "/"})
	public String listaOrdini(Model model) {
		List<Ordini> ordini = ordiniServ.listaOrdini();
		model.addAttribute("ordini", ordini);
		
		return "ordini/indexOrdini";
	}
}
