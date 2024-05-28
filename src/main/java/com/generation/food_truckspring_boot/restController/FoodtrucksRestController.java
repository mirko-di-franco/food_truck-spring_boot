package com.generation.food_truckspring_boot.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.food_truckspring_boot.dto.TrucksMarchioDTO;
import com.generation.food_truckspring_boot.dto.VisibilitaTruck;
import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.repository.FoodtrucksRepo;
import com.generation.food_truckspring_boot.repository.MarchiRepo;
import com.generation.food_truckspring_boot.service.FoodtrucksServ;
import com.generation.food_truckspring_boot.service.MarchiServ;

@CrossOrigin("*")
@RestController
@RequestMapping("api/foodtrucks")
public class FoodtrucksRestController {
	
	@Autowired
	FoodtrucksServ foodtrucksServ;
	
	@Autowired
	MarchiServ marchiServ;
	
	@Autowired
	FoodtrucksRepo foodtruckRepo;
	
	@Autowired
	MarchiRepo marchiRepo;
	
	
	@GetMapping
	public List<Marchi> getAll(){
		//prendo tutti i trucks
		//List<Foodtrucks> trucks = foodtrucksServ.listaTrucks();
		//prendo tutti i marchi
		List<Marchi> marchi = marchiServ.listaMarchi();
		
		//MarchiTrucksDTO marchiTrucks = new MarchiTrucksDTO();
		
		//marchiTrucks.setMarchi(marchi);
		//marchiTrucks.setTrucks(trucks);
		
		
		return marchi;
	}
	
	
	 @GetMapping("/marchio/id/{marchioId}")
	    public TrucksMarchioDTO getFoodtrucksByMarchioID(@PathVariable long marchioId) {
		 	//prendo tutti i trucks per id del marchio
	        List<Foodtrucks> trucks = foodtrucksServ.trucksPerMarchio(marchioId);
	        
	        Optional<Marchi> marchio = marchiServ.ricercaMarchio(marchioId);
	        
	        TrucksMarchioDTO trucksMarchio = new TrucksMarchioDTO();
	        //trucksMarchio.setTrucks(trucks);
	        trucksMarchio.setMarchio(marchio.get());
	        
	        return trucksMarchio;
	    }
	
	 

	 @GetMapping("/marchio/nome/{nomeMarchio}")
	    public List<Foodtrucks> getFoodtrucksByNomeMarchio(@PathVariable String nomeMarchio) {
	        List<Foodtrucks> trucks = foodtruckRepo.findByMarchiNome(nomeMarchio);
	        return trucks;
	    }
	 
	 
	 @GetMapping("/marchio/{marchioId}/truck/{idTruck}")
	 public ResponseEntity<?> getFoodtruckById(@PathVariable("marchioId") long marchioId,@PathVariable("idTruck") long idTruck){
		 
		 Optional<Marchi> marchi = marchiServ.ricercaMarchio(marchioId);
		 Optional<Foodtrucks> truck = foodtrucksServ.truckPerId(idTruck);
		 
		 if(truck.isEmpty()) {
			 return new ResponseEntity<>(new Foodtrucks(), HttpStatus.NOT_FOUND);
		 }else {
			 return new ResponseEntity<>(truck.get(), HttpStatus.OK);
		 }
	 }
	
	 /*
	 @GetMapping("/marchio/{marchioId}/truck/{idTruck}")
	 public Foodtrucks getFoodtruckByIdProva(@PathVariable("marchioId") long marchioId,@PathVariable("idTruck") long idTruck){
		 
		 Optional<Foodtrucks> truck = marchiRepo.findByMarchiAndFoodtrucksID(marchioId, idTruck);
		 return truck.get();
	 }
	*/
	 
	 
	 @GetMapping("/trucks")
	 public List<Foodtrucks> listaSoloTrucks(){
		 List<Foodtrucks> trucks = foodtrucksServ.listaTrucks();
		 return trucks;
 	 }
	 
	 
	 //CAMBIO VISIBILITA
	 @PutMapping("/{idTruck}/disponibilita")
	 public ResponseEntity<VisibilitaTruck> modificaVisibilita(@PathVariable("idTruck") long idTruck){
		 try {
			 boolean newVisibilita = foodtrucksServ.aggiornaVisibilita(idTruck);
			 VisibilitaTruck visibilitaOggetto = new VisibilitaTruck();
			 visibilitaOggetto.setDisponibilita(newVisibilita);
			 return ResponseEntity.ok(visibilitaOggetto);
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		 }
	 }

}
