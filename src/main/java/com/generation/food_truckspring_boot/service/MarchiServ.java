package com.generation.food_truckspring_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.repository.MarchiRepo;

@Service
public class MarchiServ {

	@Autowired
	MarchiRepo marchiRepo;
	
	//TUTTI I MARCHI
	public List<Marchi> listaMarchi(){
		List<Marchi> marchi = marchiRepo.findAll();
		return marchi;
	}
	
	
	//RICERCA PER MARCHIO
	public Optional<Marchi> ricercaMarchio(long idMarchio){
		Optional<Marchi> marchio = marchiRepo.findById(idMarchio);
		return marchio;
	}
	
	
}
