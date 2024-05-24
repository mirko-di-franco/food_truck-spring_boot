package com.generation.food_truckspring_boot.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.food_truckspring_boot.entity.Ruolo;
import com.generation.food_truckspring_boot.entity.Utenti;



@Repository
public interface UtentiRepo extends JpaRepository<Utenti, Long>{

	Optional<Utenti>  findByEmailAndPasswordAndRuolo(String email, String password,Ruolo ruolo);
	
	Optional<Utenti> findByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
}
