package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class CatalogoVoluntarios {

	private List <Voluntario> listVoluntarios;
	private boolean volAlreadyExistsInCatalogo;

	public CatalogoVoluntarios() {
		listVoluntarios = new ArrayList <>();
		volAlreadyExistsInCatalogo = false;
	}

	public Voluntario getVol(int tel) {

		Voluntario volCurr = null;
		int i = 0;

		while(i < listVoluntarios.size() && volCurr == null) {
			volCurr = listVoluntarios.get(i).getTel() == tel? 
					listVoluntarios.get(i):null; //1.1
			volAlreadyExistsInCatalogo = true;
			i++;
		}

		if(volCurr == null) 
			volCurr = new Voluntario(tel); //1.2

		return volCurr;
	}

	public void addAj(Voluntario volCurr, Ajuda ajCurr) {
		
		if(!volAlreadyExistsInCatalogo) //1.2
			listVoluntarios.add(volCurr); //1.3
		
		volCurr.addAjuda(ajCurr); //1.4
	}
}
