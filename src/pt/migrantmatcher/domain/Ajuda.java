package pt.migrantmatcher.domain;

/*
 * Template method + factory method 
 **/
public abstract class Ajuda {
		
	private boolean availability;
	private Voluntario vol;
	private String info;
	private Tipo tipo;
	
	/**
	 * Vai-se usar o pattern: Template method  
	 **/
	protected Ajuda() {
		availability = true;
	}
	
	public boolean isLivre() {
		return this.availability;
	}
	
	protected void setInfo(String info) {
		this.info = info;
	}
	
	protected void setTipo (Tipo tipo) {
		this.tipo = tipo;
	}
	
	public void setVol(Voluntario vol) {
		this.vol = vol;
	}

	public void setNotLivre() {				
		availability = false;
	}

	public int getVol() {
		return vol.getTel();
	}

	@Override
	public abstract boolean equals(Object obj);
	public abstract String toString(Object obj);

	public String getInfo() {
		return info;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
}
