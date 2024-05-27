package com.generation.food_truckspring_boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.food_truckspring_boot.dto.MarchiDTO;
import com.generation.food_truckspring_boot.dto.TruckDTO;
import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Marchi;

import com.generation.food_truckspring_boot.service.FoodtrucksServ;

import com.generation.food_truckspring_boot.entity.Piatti;

import com.generation.food_truckspring_boot.service.MarchiServ;
import com.generation.food_truckspring_boot.service.PiattiServ;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/marchi")
public class MarchiController {

	@Autowired
	MarchiServ marchiServ;
	
	@Autowired

	FoodtrucksServ foodtrucksServ;

	PiattiServ piattiServ;

	
	// significa che questa rotta funziona sia con "http://localhost:8080/marchi" oppure con "http://localhost:8080/marchi/"
		@GetMapping({"", "/"})
		//il MODEL è un interfaccia di spring che viene utilizzata per passare dati dal controller alla vista
		public String listaUtenti(Model model) {
			List<Marchi> marchi = marchiServ.listaMarchi();
			model.addAttribute("marchi", marchi);
			
			return "marchi/indexMarchi";
		}
		//-------------------------------------------metodo per lista furgoni di un solo marchio si collega a listaTruckId.html
		@GetMapping("/listaTrucks/{marchioId}")
		public String listaTruckPerIdMarchio(Model model, @PathVariable("marchioId") Long marchioId) {
			List<Foodtrucks>foodtrucks=foodtrucksServ.trucksPerMarchio(marchioId);
			model.addAttribute("foodtrucks", foodtrucks);
			return "marchi/listaTruckId";
		}
		
		//-----------------------------------------------
		//FORM CREAZIONE  TRUCK
		@GetMapping("/nuovoTruck")
		public String paginaNuovoTruck(Model model) {
			TruckDTO truckDTO = new TruckDTO();
			model.addAttribute("truckDTO", truckDTO);
			return "marchi/creazioneTruck";
		}
		
		@PostMapping("/nuovoTruck")
		//@valid : esegue la validazione di TRUCKDTO utilizzando le annotazioni di validazione presenti nella classe TRUCKDTO, come @NotNull, @Size
		//@ModelAttribute: Spring cerca di legare i dati del form (o altri parametri della richiesta) a un'istanza di TRUCKDTO.
		//@BindingResult: contiene i risultati della validazione di truckDTO. Può essere usato per verificare se ci sono stati errori di binding o di validazione.
		public String nuovoTruck(@Valid @ModelAttribute TruckDTO truckDTO, BindingResult result) {
			
			if(result.hasErrors()) {
				return "marchi/creazioneTruck";
			}
			Optional<Marchi> marchio=marchiServ.ricercaMarchio(truckDTO.getMarchioId());
			 if (!marchio.isPresent()) {
			        // Aggiungi un messaggio di errore personalizzato al BindingResult
			        result.rejectValue("marchioId", "error.truckDTO", "Il marchio con l'ID specificato non esiste.");
			        return "marchi/creazioneTruck";
			    }
			
			Foodtrucks foodtrucks = new Foodtrucks();
			foodtrucks.setNome(truckDTO.getNome());
			foodtrucks.setDescrizione(truckDTO.getDescrizione());
			foodtrucks.setIndirizzo(truckDTO.getIndirizzo());
			foodtrucks.setCoordinateGps(truckDTO.getCoordinateGps());
			foodtrucks.setDisponibilita(truckDTO.isDisponibilita());
			foodtrucks.setMarchi(marchio.get());
			foodtrucks.setImmagine(truckDTO.getImmagine());
			
			foodtrucksServ.aggiuntaOModificaTruck(foodtrucks);
			
			
			return "redirect:/marchi/listaTrucks/"+truckDTO.getMarchioId();
		}
		
		//-----------------------------------------------
		
		
		//FORM CREAZIONE MARCHIO
		@GetMapping("/nuovoMarchio")
		public String paginaNuovoUtente(Model model) {
			MarchiDTO marchiDTO = new MarchiDTO();
			model.addAttribute("marchiDTO", marchiDTO);
			return "marchi/creazioneMarchio";
		}
		
		
		
		@PostMapping("/nuovoMarchio")
		//@valid : esegue la validazione di UtentiDTO utilizzando le annotazioni di validazione presenti nella classe UtentiDTO, come @NotNull, @Size
		//@ModelAttribute: Spring cerca di legare i dati del form (o altri parametri della richiesta) a un'istanza di UtentiDTO.
		//@BindingResult: contiene i risultati della validazione di UtentiDTO. Può essere usato per verificare se ci sono stati errori di binding o di validazione.
		public String nuovoUtente(@Valid @ModelAttribute MarchiDTO marchioDTO, BindingResult result) {
			
			if(result.hasErrors()) {
				return "marchi/creazioneMarchio";
			}
			
			Marchi marchio = new Marchi();
			
			marchio.setNome(marchioDTO.getNome());
			marchio.setDescrizione(marchioDTO.getDescrizione());
			marchio.setGenere(marchioDTO.getGenere());
			marchio.setLogo(marchioDTO.getLogo());
			marchio.setVideo(marchioDTO.getVideo());
			
			marchiServ.aggiuntaOModificaMarchio(marchio);
			
			return "redirect:/marchi";
		}
		
		
		@GetMapping("/modificaMarchio")
		public String paginaModificaUtente(Model model, @RequestParam long idMarchio ) {
			try {
				Marchi marchio = marchiServ.ricercaMarchio(idMarchio).get();
				model.addAttribute("marchio", marchio);
				
				MarchiDTO marchiDTO = new MarchiDTO();
				
				marchiDTO.setNome(marchio.getNome());
				marchiDTO.setDescrizione(marchio.getDescrizione());
				marchiDTO.setGenere(marchio.getGenere());
				marchiDTO.setLogo(marchio.getLogo());
				marchiDTO.setVideo(marchio.getVideo());
				
				model.addAttribute("marchiDTO", marchiDTO);
				
			}catch(Exception e) {
				System.out.println("Exception: "+e.getMessage());
				return "marchi/indexMarchi";
			}
			return "marchi/modificaMarchio";
		}
		
		
		
		@PostMapping("/modificaMarchio")
		public String modificaUtente(Model model, @RequestParam long idMarchio, @Valid @ModelAttribute MarchiDTO marchiDTO, BindingResult result) {
			
			try {
				Marchi marchio = marchiServ.ricercaMarchio(idMarchio).get();
				model.addAttribute("marchio", marchio);
			
				if(result.hasErrors()) {
					return "marchio/modificaMarchio";
				}
				
				
				marchio.setNome(marchiDTO.getNome());
				marchio.setDescrizione(marchiDTO.getDescrizione());
				marchio.setGenere(marchiDTO.getGenere());
				marchio.setLogo(marchiDTO.getLogo());
				marchio.setVideo(marchiDTO.getVideo());
				
				marchiServ.aggiuntaOModificaMarchio(marchio);
				
			}catch(Exception e) {
				System.out.println("Exception: "+e.getMessage());
				System.err.println("marchio non trovato");
			}
			
			//SE SONO RIUSCITO A MODIFICA ALLORA MANDO ALLA HOME DEGLI UTENTI
			return "redirect:/marchi";
			}
		
		
		
		@GetMapping("/eliminazione")
		public String eliminazioneUtente(@RequestParam long idMarchio, Model model) {
			try {
				Marchi marchio = marchiServ.ricercaMarchio(idMarchio).get();
				
				marchiServ.eliminaMarchio(marchio);
				 model.addAttribute("eliminazione", true);
			     model.addAttribute("messaggio", "Marchio eliminato con successo!");
				
			}catch(Exception e) {
				System.out.println("Errore: "+e.getMessage());
				 model.addAttribute("eliminazione", false);
			     model.addAttribute("messaggio", "Si è verificato un errore durante l'eliminazione del Marchio.");
			    
				 
			}
			
			return "redirect:/marchi";
		}
		
		
		//RESTITUISCE TUTTI I PIATTO PER ID DEL MARCHIO
		@GetMapping("/piatti/{idMarchio}")
		public String paginaPiattiMarchio(Model model, @PathVariable("idMarchio") long idMarchio ) {
			
			List<Piatti> piatti = piattiServ.listaPiattiByIdMarchio(idMarchio);
			model.addAttribute("piatti", piatti);
			
			
			return "marchi/listaPiattiMarchio";
		}
		
		
		//FORM CREAZIONE NUOVO PIATTO
		@GetMapping("/nuovoPiatto/{idMarchio}")
		public String paginaNuovoPiatto(Model model) {
			MarchiDTO marchiDTO = new MarchiDTO();
			model.addAttribute("marchiDTO", marchiDTO);
			
			return "marchi/creazioneMarchio";
		}
}
