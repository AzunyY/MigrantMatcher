package pt.migrantmatcher.facade.DTO;

import pt.migrantmatcher.domain.TYPE;

public class AidDTO {

	private String info;
	private TYPE type;
	private boolean availability;
	private int tel;
	
	public AidDTO(String info, TYPE type, boolean availability, int tel) {
		this.info = info;
		this.availability = availability;
		this.type = type;
		this.tel = tel;
	}
	
	public String getAidInfo() {
		
		return "Aid - " + this.type + " | Info - " + this.info;
		
	}

	public TYPE getType() {
		return this.type;
	}
	
	public String getInfo() {
		return this.info;
	}

	public boolean getAvailability() {
		return this.availability;
	}

	public int getTel() {
		return this.tel;
	}
}
