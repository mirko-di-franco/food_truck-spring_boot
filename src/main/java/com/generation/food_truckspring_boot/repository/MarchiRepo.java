package com.generation.food_truckspring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.generation.food_truckspring_boot.entity.Marchi;

@Repository
public interface MarchiRepo extends JpaRepository<Marchi, Long> {
	
	@Query(value = "SELECT m.nome FROM Marchi m")
	List<Marchi> findByNome();
	



	
	
}
