package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class CatalogoRegioes {
	
	//A lista das regioes deve estar no configuration
	private List <Regiao> listRegs;
	
	public CatalogoRegioes() {
		listRegs = new ArrayList<>();
	}
	
	public List<Regiao> getRegioes() {
		return this.listRegs;
	}

}
