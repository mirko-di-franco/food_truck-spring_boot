package com.generation.food_truckspring_boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.entity.Piatti;
import com.generation.food_truckspring_boot.repository.PiattiRepo;

@Service
public class PiattiServ {

	@Autowired
	PiattiRepo piattiRepo;
	
	public Optional<Piatti> piattoPerId(long idPiatto){
		Optional<Piatti> piatto = piattiRepo.findById(idPiatto);
		return piatto;
	}
}
