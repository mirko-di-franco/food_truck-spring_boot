package com.generation.food_truckspring_boot.restController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.entity.Ordini;
import com.generation.food_truckspring_boot.service.OrdiniServ;
import com.generation.food_truckspring_boot.service.UtentiServ;

@RestController
@RequestMapping("api/ordini")
public class OrdiniRestController {
	
	@Autowired
	OrdiniServ ordiniServ;
	
	@Autowired
	UtentiServ utentiServ;
	
	
	@GetMapping
	public List<Ordini> getAll(){
		List<Ordini> ordini = ordiniServ.listaOrdini();
		return ordini;
	}
	
	
	
	

}
