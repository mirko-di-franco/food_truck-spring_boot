package com.generation.food_truckspring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.food_truckspring_boot.entity.Piatti;

@Repository
public interface PiattiRepo extends JpaRepository<Piatti, Long> {

	List<Piatti> findByMarchiId(long idMarchio);
}
