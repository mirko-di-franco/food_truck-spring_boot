package com.generation.food_truckspring_boot.service;

import java.util.List;
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
	
	
	//LISTA PIATTI PER ID MARCHIO
	public List<Piatti> listaPiattiByIdMarchio(long idMarchio){
		List<Piatti> piatti = piattiRepo.findByMarchiId(idMarchio);
		return piatti;
	}
	
	//salva aggiungi piatti 
	public Piatti aggiungiModificaPiatti(Piatti piatto) {
		Piatti nuovoPiatto=piattiRepo.save(piatto);
		return nuovoPiatto;
	}
	
	
	//eliminazione piatto
	public void eliminaPiatto(Piatti piatto) {
		piattiRepo.delete(piatto);
	}
}
