package com.generation.food_truckspring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.entity.Ordini;
import com.generation.food_truckspring_boot.repository.OrdiniRepo;

import jakarta.transaction.Transactional;

@Service
public class OrdiniServ {
	
	@Autowired
	OrdiniRepo ordiniRepo;

	//LISTA TUTTI GLI ORDINI
	public List<Ordini> listaOrdini(){
		List<Ordini> ordini = ordiniRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		return ordini;
	}
	
	
	@Transactional
	//AGGIUNTA ORDINE
	public Ordini aggiuntaOModificaOrdine(Ordini ordine){
		Ordini newOrdine = ordiniRepo.save(ordine);
		return newOrdine;
	}
	
	
	
}
