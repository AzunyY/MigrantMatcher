package pt.migrantmatcher.facade.DTO;

import pt.migrantmatcher.domain.Type;

public class AidDTO {

	private String info;
	private Type type;
	
	public AidDTO(String info, Type type) {
		this.info = info;
		this.type = type;
	}
	
	public String getAidInfo() {
		
		return "Aid - " + this.type + " | Info - " + this.info;
		
	}

	public Type getType() {
		return this.type;
	}
	
	public String getInfo() {
		return this.info;
	}
}
