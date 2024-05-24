package com.generation.food_truckspring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.entity.Ordini;
import com.generation.food_truckspring_boot.repository.OrdiniRepo;

@Service
public class OrdiniServ {
	
	@Autowired
	OrdiniRepo ordiniRepo;

	public List<Ordini> listaOrdini(){
		List<Ordini> ordini = ordiniRepo.findAll();
		return ordini;
	}
}
