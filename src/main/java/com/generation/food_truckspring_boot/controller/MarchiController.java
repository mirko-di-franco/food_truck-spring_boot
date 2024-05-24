package com.generation.food_truckspring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.food_truckspring_boot.dto.MarchiDTO;
import com.generation.food_truckspring_boot.entity.Marchi;
import com.generation.food_truckspring_boot.service.MarchiServ;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/marchi")
public class MarchiController {

	@Autowired
	MarchiServ marchiServ;
	
	// significa che questa rotta funziona sia con "http://localhost:8080/marchi" oppure con "http://localhost:8080/marchi/"
		@GetMapping({"", "/"})
		//il MODEL è un interfaccia di spring che viene utilizzata per passare dati dal controller alla vista
		public String listaUtenti(Model model) {
			List<Marchi> marchi = marchiServ.listaMarchi();
			model.addAttribute("marchi", marchi);
			
			return "marchi/indexMarchi";
		}
		
		
		
		//FORM CREAZIONE 
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
}
