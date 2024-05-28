package com.generation.food_truckspring_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.dto.NomeMarchiDTO;
import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.repository.MarchiRepo;
import com.generation.food_truckspring_boot.repository.OrdiniRepo;

@Service
public class MarchiServ {

	@Autowired
	MarchiRepo marchiRepo;
	
	@Autowired
	OrdiniRepo ordiniRepo;
	
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
	
	
	//AGGIUNTA O MODIFICA MARCHIO
	public Marchi aggiuntaOModificaMarchio(Marchi marchio) {
		Marchi newMarchio = marchiRepo.save(marchio);
		return newMarchio;
	}
	
	public List<NomeMarchiDTO> nomeMarchioooo(){
		List<Marchi> marchi=marchiRepo.findAll();
		
		List<NomeMarchiDTO>nomeMarchiDTO=new ArrayList<NomeMarchiDTO>();
		//ciclo i marchi
		for (Marchi marchio : marchi) {
			NomeMarchiDTO nome= new NomeMarchiDTO(marchio.getNome());
			nomeMarchiDTO.add(nome);
			
		}
		
		
		return nomeMarchiDTO;
		
		
	}
	
}
