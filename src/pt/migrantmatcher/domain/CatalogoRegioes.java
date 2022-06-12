package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

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

	public void pedeNotif(Regiao regiao, Migrantes migCurr) {
		regiao.addObserver(migCurr);
	}

	public Optional<String> exists(String name) {
		
		for(int i = 0; i < listRegs.size(); i++)
			if(listRegs.get(i).getName().equals(name))
				return Optional.of(listRegs.get(i).getName());
	
		return Optional.empty();
	}

	public List<String> getRegioesNomes() {
			List <String> listReg = new ArrayList<>();
			
			for(int i  = 0; i < this.listRegs.size(); i++)
				listReg.add(this.listRegs.get(i).getName());
			
			return listReg;
	}
}
