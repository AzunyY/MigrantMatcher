package pt.migrantmatcher.domain;

/*
 * Template method + factory method 
 **/
public abstract class Ajuda {
		
	private boolean availability;
	private Voluntario vol;
	/**
	 * Vai-se usar o pattern: Template method  
	 **/
	protected Ajuda() {
		availability = true;
	}
	
	public boolean isLivre() {
		return this.availability;
	}
	
	public void setVol(Voluntario vol) {
		this.vol = vol;
	}

	public void setNotLivre(Migrantes mig) {
		//vamos ter de usar genericos
		vol.enviaSMS("O migrante, " + ((Individual) mig).getNome() + " quer a ajuda: " + this.toString());
		availability = false;
	};
}
