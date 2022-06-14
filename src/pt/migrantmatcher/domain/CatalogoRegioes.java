package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class CatalogoRegioes {
	
	//A lista das regioes deve estar no configuration
	private List <Regiao> listRegs;
	
	public CatalogoRegioes(List <String> reg) {
		
		listRegs = new ArrayList <>();
		
		for(String s : reg) 
			this.listRegs.add(new Regiao(s));
	}

	public List<String> getRegioes() {
			List <String> listReg = new ArrayList<>(); //1.1
			
			for(int i  = 0; i < this.listRegs.size(); i++)
				listReg.add(this.listRegs.get(i).getName()); //1.2, 1.3
			
			return listReg;
	}
}
