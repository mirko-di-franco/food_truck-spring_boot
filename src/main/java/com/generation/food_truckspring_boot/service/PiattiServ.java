package com.generation.food_truckspring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.repository.PiattiRepo;

@Service
public class PiattiServ {

	@Autowired
	PiattiRepo piattiRepo;
}
