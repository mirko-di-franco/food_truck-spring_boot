package com.generation.food_truckspring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.food_truckspring_boot.entity.Foodtrucks;

@Repository
public interface FoodtrucksRepo extends JpaRepository<Foodtrucks, Long> {

	List<Foodtrucks> findByMarchiId(long marchioId);
	
	List<Foodtrucks> findByMarchiNome(String nomeMarchio);
	
	
	
	
	
		
}
