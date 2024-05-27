package com.generation.food_truckspring_boot.dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TruckDTO {
	
	//messaggio errore se non compili il campo
		@NotEmpty(message = "Il nome é richiesto")
		private String nome;
		
		@NotEmpty(message = "descrizione richiesta")
		private String descrizione;
		
		@NotEmpty(message = "indirizzo richiesto")
		private String indirizzo;
		
		@NotNull(message = "coordinaterichieste")
		private String coordinateGps;
		
		@NotEmpty(message = "disponibilta è richiesta")
		private boolean disponibilita;
		
		@NotEmpty(message = "L'immagine è richiesta")
		private String immagine;
		
		@NotEmpty(message = "il marchio è richiesto")
		private long marchioId;


		
		//GET E SET 
	

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDescrizione() {
			return descrizione;
		}

		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}

		public String getIndirizzo() {
			return indirizzo;
		}

		public void setIndirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
		}

		public String getCoordinateGps() {
			return coordinateGps;
		}

		public void setCoordinateGps(String coordinateGps) {
			this.coordinateGps = coordinateGps;
		}

		public boolean isDisponibilita() {
			return disponibilita;
		}

		public void setDisponibilita(boolean disponibilita) {
			this.disponibilita = disponibilita;
		}

		public String getImmagine() {
			return immagine;
		}

		public void setImmagine(String immagine) {
			this.immagine = immagine;
		}

		public long getMarchioId() {
			return marchioId;
		}

		public void setMarchioId(long marchioid) {
			this.marchioId = marchioid;
		}

		
		
		
		
		

}
