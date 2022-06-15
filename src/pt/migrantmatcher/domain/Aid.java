package pt.migrantmatcher.domain;

public abstract class Aid {
		
	private boolean availability;
	private Voluntary vol;
	private String info;
	private Type type;
	
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
	
	protected void setType (Type type) {
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
	
	public Type getType() {
		return type;
	}
}
