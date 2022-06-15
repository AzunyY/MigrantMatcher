package pt.migrantmatcher.domain;

import java.util.HashMap;
import java.util.Optional;

public class CatalogoVoluntarios {

	private HashMap <Integer, Voluntario> listVoluntarios;
	private boolean volAlreadyExistsInCatalogo;

	public CatalogoVoluntarios() {
		listVoluntarios = new HashMap <>();
		volAlreadyExistsInCatalogo = false;
	}

	public Voluntario getVol(int tel) {
		
		Optional<Voluntario> vol = Optional.ofNullable(listVoluntarios.get(tel));
		
		if(vol.isPresent()) {
			volAlreadyExistsInCatalogo = true;
			return vol.get();
		}
		
		else 
			return new Voluntario(tel);
	}

	public void addAj(Voluntario volCurr, Ajuda ajCurr) {
		
		if(!volAlreadyExistsInCatalogo)  //1.2
			listVoluntarios.put(volCurr.getTel(), volCurr); //1.3
	}
}
