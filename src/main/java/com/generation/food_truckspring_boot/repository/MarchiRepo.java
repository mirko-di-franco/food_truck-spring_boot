package com.generation.food_truckspring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.food_truckspring_boot.entity.Marchi;

@Repository
public interface MarchiRepo extends JpaRepository<Marchi, Long> {

}
