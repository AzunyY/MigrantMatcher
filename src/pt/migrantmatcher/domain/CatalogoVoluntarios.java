package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class CatalogoVoluntarios {
	
	private List <Voluntario> listVoluntarios;
	
	public CatalogoVoluntarios() {
		listVoluntarios = new ArrayList <>();
	}
	
	public Voluntario setVol(int tel) {
		
		Voluntario volCurr = null;
		int i = 0;
		
		while(i < listVoluntarios.size() && volCurr == null) {
			volCurr = listVoluntarios.get(i).getTel() == tel? 
					listVoluntarios.get(i):null;
			i++;
		}
		
		if(volCurr == null) {
			volCurr = new Voluntario(tel);
			listVoluntarios.add(volCurr);
		}
		
		return volCurr;
	}

}
