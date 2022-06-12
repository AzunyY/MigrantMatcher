package pt.migrantmatcher.domain;

import java.util.List;

public class CatalogoRegioes {
	
	//A lista das regioes deve estar no configuration
	private List <Regiao> listRegs;
	
	public CatalogoRegioes(List <String> reg) {
		for(String s : reg)
			this.listRegs.add(new Regiao(s));
	}

	public List<Regiao> getRegioes() {
		return this.listRegs;
	}

}
