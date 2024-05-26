package com.generation.food_truckspring_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.entity.Ruolo;
import com.generation.food_truckspring_boot.entity.Utenti;
import com.generation.food_truckspring_boot.repository.UtentiRepo;

@Service
public class UtentiServ {

	@Autowired
	UtentiRepo utentiRepo;
	
	
	//METODO TUTTI UTENTI
	public List<Utenti> listaUtenti(){
		//se vuoi vedere l'ordine al contrario nella vista
		List<Utenti> utenti = utentiRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		//List<Utenti> utenti = utentiRepo.findAll();
		return utenti;
	}
	
	
	//METODO AGGIUNTA UTENTE
	public Utenti aggiungiOModifica(Utenti utente) {
		Utenti newUtente = utentiRepo.save(utente);
		return newUtente;
	}
	
	
	//METODO RICERCA UTENTE
	public Optional<Utenti> ricercaUtente(long idUtente){
		Optional<Utenti> utente = utentiRepo.findById(idUtente);
		return utente;
	}
	
	
	//METODO ELIMINAZIONE UTENTE
	public void eliminaUtente(Utenti utente) {
		utentiRepo.delete(utente);
	}
	
	public Optional<Utenti> findByEmailAndPasswordAndRuolo(String email, String password, Ruolo ruolo){
		return utentiRepo.findByEmailAndPasswordAndRuolo(email, password, ruolo.ADMIN);
		
	}


	public Optional<Utenti> findByEmailAndPassword(String email, String password) {
		return utentiRepo.findByEmailAndPassword(email, password);
	}
	
	
	public Optional<Utenti> findByEmail(String email){
		return utentiRepo.findByEmail(email);
	}
	
	
	
}
