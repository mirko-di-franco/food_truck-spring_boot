package com.generation.food_truckspring_boot.dto;

import java.math.BigDecimal;

public class NomeMarchiDTO {
    
    /**
	 * @param nomeMarchio
	 * @param totaleOrdini
	 * @param ordini
	 */
	public NomeMarchiDTO(String nomeMarchio, BigDecimal totaleOrdini, long ordini) {
		this.nomeMarchio = nomeMarchio;
		this.totaleOrdini = totaleOrdini;
		this.ordini = ordini;
	}
	public NomeMarchiDTO(String nomeMarchio) {
		this.nomeMarchio = nomeMarchio;
		
	}
	public NomeMarchiDTO( long ordini) {
		
		this.ordini = ordini;
	}
	public NomeMarchiDTO(BigDecimal totaleOrdini) {
		
		this.totaleOrdini = totaleOrdini;
		
	}
	public NomeMarchiDTO() {};
	
	
	
	
	
	private String nomeMarchio;
    private BigDecimal totaleOrdini;
    private long ordini;
	public String getNomeMarchio() {
		return nomeMarchio;
	}
	public void setNomeMarchio(String nomeMarchio) {
		this.nomeMarchio = nomeMarchio;
	}
	public BigDecimal getTotaleOrdini() {
		return totaleOrdini;
	}
	public void setTotaleOrdini(BigDecimal totaleOrdini) {
		this.totaleOrdini = totaleOrdini;
	}
	public long getOrdini() {
		return ordini;
	}
	public void setOrdini(long ordini) {
		this.ordini = ordini;
	}

    
}
