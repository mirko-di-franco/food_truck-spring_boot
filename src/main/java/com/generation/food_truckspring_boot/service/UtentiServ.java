package com.generation.food_truckspring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.entity.Utenti;
import com.generation.food_truckspring_boot.repository.UtentiRepo;

@Service
public class UtentiServ {

	@Autowired
	UtentiRepo utentiRepo;
	
	
	public List<Utenti> listaUtenti(){
		List<Utenti> utenti = utentiRepo.findAll();
		return utenti;
	}
}
