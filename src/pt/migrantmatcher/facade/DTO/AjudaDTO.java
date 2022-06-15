package pt.migrantmatcher.facade.DTO;

import pt.migrantmatcher.domain.Tipo;

public class AjudaDTO {

	private String info;
	private Tipo tipo;
	
	public AjudaDTO(String info, Tipo tipo) {
		this.info = info;
		this.tipo = tipo;
	}
	
	public String getInfo() {
		return "Ajuda - " + this.tipo + " | Info - " + this.info;
	}

	public Tipo getTipo() {
		return this.tipo;
	}
	
	public String getinfo() {
		return this.info;
	}
}
