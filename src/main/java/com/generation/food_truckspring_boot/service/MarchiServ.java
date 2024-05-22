package com.generation.food_truckspring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.food_truckspring_boot.repository.MarchiRepo;

@Service
public class MarchiServ {

	@Autowired
	MarchiRepo marchiRepo;
	
	
	
}
