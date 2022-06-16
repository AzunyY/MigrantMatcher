package pt.migrantmatcher.domain;

public abstract class Aid {
		
	private boolean availability;
	private Voluntary vol;
	private String info;
	private TYPE type;
	
	/**
	 * Vai-se usar o pattern: Template method  
	 **/
	protected Aid() {
		availability = true;
	}
	
	public boolean isAidAvailable() {
		return this.availability;
	}
	
	protected void setInfo(String info) {
		this.info = info;
	}
	
	protected void setType (TYPE type) {
		this.type = type;
	}
	
	public void setVol(Voluntary vol) {
		this.vol = vol;
	}

	public void putAidToNotAvailable() {				
		availability = false;
	}

	public int getVol() {
		return vol.getTel();
	}
	
	public String toString() {
		return "Tipo - " + this.type + "| Desc - " + this.info;
	}

	public String getInfo() {
		return info;
	}
	
	public TYPE getType() {
		return type;
	}

	public boolean getAvailability() {
		return availability;
	}
}
