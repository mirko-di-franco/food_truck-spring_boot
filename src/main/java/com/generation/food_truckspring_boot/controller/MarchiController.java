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
import com.generation.food_truckspring_boot.dto.PiattoDTO;
import com.generation.food_truckspring_boot.dto.TruckDTO;
import com.generation.food_truckspring_boot.entity.Foodtrucks;
import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.entity.Piatti;
import com.generation.food_truckspring_boot.service.FoodtrucksServ;
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

	@Autowired
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
		@GetMapping("/nuovoTruck/{marchioId}")
		public String paginaNuovoTruck(Model model,@PathVariable("marchioId")long marchioId) {
			TruckDTO truckDTO = new TruckDTO();
			truckDTO.setMarchioId(marchioId);
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
		
		
		//------------------------------------FORM MODIFICA TRUCK--------------------
		@GetMapping("/modificaTruck")
		public String modificaTruck(Model model, @RequestParam long idTruck ) {
			try {
				Foodtrucks truck = foodtrucksServ.truckPerId(idTruck).get();
				model.addAttribute("truck", truck);
				
				TruckDTO truckDTO = new TruckDTO();
				
				truckDTO.setNome(truck.getNome());
				truckDTO.setDescrizione(truck.getDescrizione());
				truckDTO.setIndirizzo(truck.getIndirizzo());
				truckDTO.setCoordinateGps(truck.getCoordinateGps());
				truckDTO.setDisponibilita(truck.isDisponibilita());
				truckDTO.setImmagine(truck.getImmagine());
				truckDTO.setMarchioId(truck.getMarchi().getId());
				
				model.addAttribute("truckDTO", truckDTO);
				
			}catch(Exception e) {
				System.out.println("Exception: "+e.getMessage());
				return "marchi/indexMarchi";
			}
			return "marchi/modificaTruck";
		}
		
		
		
		@PostMapping("/modificaTruck")
		public String modificaTruck(Model model, @RequestParam long idTruck, @Valid @ModelAttribute TruckDTO truckDTO, BindingResult result) {
			
			try {
				Foodtrucks truck = foodtrucksServ.truckPerId(idTruck).get();
				model.addAttribute("truck", truck);
			
				if(result.hasErrors()) {
					return "marchio/modificaMarchio";
				}
				
				
				truck.setNome(truckDTO.getNome());
				truck.setDescrizione(truckDTO.getDescrizione());
				truck.setIndirizzo(truckDTO.getIndirizzo());
				truck.setCoordinateGps(truckDTO.getCoordinateGps());
				truck.setDisponibilita(truckDTO.isDisponibilita());
				truck.setImmagine(truckDTO.getImmagine());
				
				
				foodtrucksServ.aggiuntaOModificaTruck(truck);
				
			}catch(Exception e) {
				System.out.println("Exception: "+e.getMessage());
				System.err.println("truck non trovato");
			}
			
			//SE SONO RIUSCITO A MODIFICA ALLORA MANDO ALLA HOME DEGLI UTENTI
			return "redirect:/marchi";
			}
		
		//ELIMINAZIONE TRUCK
		
		@GetMapping("/eliminazioneTruck")
		public String eliminazioneTruck(@RequestParam long idTruck, Model model) {
			try {
				Foodtrucks truck = foodtrucksServ.truckPerId(idTruck).get();
				
				foodtrucksServ.eliminaTruck(truck);
				 model.addAttribute("eliminazione", true);
			     model.addAttribute("messaggio", "Piatto eliminato con successo!");
				
			}catch(Exception e) {
				System.out.println("Errore: "+e.getMessage());
				 model.addAttribute("eliminazione", false);
			     model.addAttribute("messaggio", "Si è verificato un errore durante l'eliminazione del Piatto.");
			    
				 
			}
			
			return "redirect:/marchi";
		}
		//----------------------------------------------------------------------------
		
		
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
		
		//---------------------PIATTI-------------------------------//
		
		//RESTITUISCE TUTTI I PIATTO PER ID DEL MARCHIO
		@GetMapping("/piatti/{idMarchio}")
		public String paginaPiattiMarchio(Model model, @PathVariable("idMarchio") long idMarchio ) {
			
			List<Piatti> piatti = piattiServ.listaPiattiByIdMarchio(idMarchio);
			model.addAttribute("piatti", piatti);
			
			
			return "marchi/listaPiattiMarchio";
		}
		
		//-----------------------------------------------
		//FORM CREAZIONE NUOVO PIATTO
		@GetMapping("/nuovoPiatto/{idMarchio}")
		public String paginaNuovoPiatto(Model model,@PathVariable("idMarchio") long marchioId) {
			PiattoDTO piattoDTO = new PiattoDTO();
			piattoDTO.setMarchioId(marchioId);
			model.addAttribute("piattoDTO", piattoDTO);
			
			return "marchi/creazionePiatto";
		}

				
				@PostMapping("/nuovoPiatto")
				//@valid : esegue la validazione di TRUCKDTO utilizzando le annotazioni di validazione presenti nella classe TRUCKDTO, come @NotNull, @Size
				//@ModelAttribute: Spring cerca di legare i dati del form (o altri parametri della richiesta) a un'istanza di TRUCKDTO.
				//@BindingResult: contiene i risultati della validazione di truckDTO. Può essere usato per verificare se ci sono stati errori di binding o di validazione.
				public String nuovoPiatto(@Valid @ModelAttribute PiattoDTO piattoDTO, BindingResult result) {
					
					if(result.hasErrors()) {
						return "marchi/creazionePiatto";
					}
					Optional<Marchi> marchio=marchiServ.ricercaMarchio(piattoDTO.getMarchioId());
					 if (!marchio.isPresent()) {
					        // Aggiungi un messaggio di errore personalizzato al BindingResult
					        result.rejectValue("marchioId", "error.truckDTO", "Il marchio con l'ID specificato non esiste.");
					        return "marchi/creazionePiatto";
					    }
					
					Piatti piatto = new Piatti();
					piatto.setNome(piattoDTO.getNome());
					piatto.setDescrizione(piattoDTO.getDescrizione());
					piatto.setImmagine(piattoDTO.getImmagine());
					piatto.setAlimentazione(piattoDTO.getAlimentazione());
					piatto.setPrezzoListino(piattoDTO.getPrezzoListino());
					piatto.setPortata(piattoDTO.getPortata());
					piatto.setMarchi(marchio.get());
					
					
					piattiServ.aggiungiModificaPiatti(piatto);
					
					
					return "redirect:/marchi/piatti/"+piattoDTO.getMarchioId();
				}
				
				//ELIMINAZIONE PIATTO
				
				@GetMapping("/eliminazionePiatto")
				public String eliminazionePiatto(@RequestParam long idPiatto, Model model) {
					try {
						Piatti piatto = piattiServ.piattoPerId(idPiatto).get();
						
						piattiServ.eliminaPiatto(piatto);
						 model.addAttribute("eliminazione", true);
					     model.addAttribute("messaggio", "Piatto eliminato con successo!");
						
					}catch(Exception e) {
						System.out.println("Errore: "+e.getMessage());
						 model.addAttribute("eliminazione", false);
					     model.addAttribute("messaggio", "Si è verificato un errore durante l'eliminazione del Piatto.");
					    
						 
					}
					
					return "redirect:/marchi";
				}
				
				//-----------------------------------------------
				
				//MODIFICA PIATTO
				@GetMapping("/modificaPiatto")
				public String paginaModificaPiatto(Model model, @RequestParam long idPiatto ) {
					try {
						Piatti piatto = piattiServ.piattoPerId(idPiatto).get();
						model.addAttribute("piatto", piatto);
						
						PiattoDTO piattoDTO = new PiattoDTO();
						
						piattoDTO.setNome(piatto.getNome());
						piattoDTO.setDescrizione(piatto.getDescrizione());
						piattoDTO.setImmagine(piatto.getImmagine());
						piattoDTO.setAlimentazione(piatto.getAlimentazione());
						piattoDTO.setPrezzoListino(piatto.getPrezzoListino());
						piattoDTO.setPortata(piatto.getPortata());
						piattoDTO.setMarchioId(piatto.getMarchi().getId());
						
						
						model.addAttribute("piattoDTO", piattoDTO);
						
					}catch(Exception e) {
						System.out.println("Exception: "+e.getMessage());
						return "marchi/indexMarchi";
					}
					return "marchi/modificaPiatto";
				}
				
				
				
				@PostMapping("/modificaPiatto")
				public String modificaPiatto(Model model, @RequestParam long idPiatto, @Valid @ModelAttribute PiattoDTO piattoDTO, BindingResult result) {
					
					try {
						Piatti piatto = piattiServ.piattoPerId(idPiatto).get();
						model.addAttribute("piatto", piatto);
					
						if(result.hasErrors()) {
							return "marchi/modificaPiatto";
						}
						
						
						piatto.setNome(piattoDTO.getNome());
						piatto.setDescrizione(piattoDTO.getDescrizione());
						piatto.setImmagine(piattoDTO.getImmagine());
						piatto.setAlimentazione(piattoDTO.getAlimentazione());
						piatto.setPrezzoListino(piattoDTO.getPrezzoListino());
						piatto.setPortata(piattoDTO.getPortata());
						
						piattiServ.aggiungiModificaPiatti(piatto);
						
					}catch(Exception e) {
						System.out.println("Exception: "+e.getMessage());
						System.err.println("marchio non trovato");
					}
					
					//SE SONO RIUSCITO A MODIFICA ALLORA MANDO ALLA HOME DEGLI UTENTI
					return "redirect:/marchi";
					}
}
