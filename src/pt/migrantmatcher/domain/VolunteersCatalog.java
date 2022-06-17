package pt.migrantmatcher.domain;

import java.util.HashMap;
import java.util.Optional;

/**
 * Lista de Voluntarios
 * 
 * @author Ana Luis FC53563
 */
public class VolunteersCatalog {

	protected HashMap <Integer, Voluntary> 	volunteersList = new HashMap <>();
	protected boolean volAlreadyExistsInCatalogo = false;
	
	/**
	 * Devolve o voluntario 
	 * @param tel - numero de telemovel do voluntario
	 * @return uma instancia do voluntario novo se ainda nao existir no catalogo
	 * caso contrario envia um que ja exista
	 */
	protected Voluntary getVol(int tel) {

		Optional<Voluntary> vol = Optional.ofNullable(volunteersList.get(tel));

		if(vol.isPresent()) {
			volAlreadyExistsInCatalogo = true;
			return vol.get();
		}

		else {
			volAlreadyExistsInCatalogo = false;
			return new Voluntary(tel);
		}
	}
	
	/**
	 * Adiciona um voluntario ao catalogo
	 * @param currVol - o voluntario corrente
	 * @returns se o voluntario nao existir adiciona-se
	 */
	protected void addVolToCatalog(Voluntary currVol) {

		if(!volAlreadyExistsInCatalogo)  //1.2
			volunteersList.put(currVol.getTel(), currVol); //1.3
	}

	/**
	 * Verifica se o voluntario vou adicionado corretamente
	 * @param tel - numero telemovel do voluntario
	 * @return true se tiver sido adicionado corretamne, falso caso contrario
	 */
	protected boolean volWasAdded(int tel) {
		Optional<Voluntary> vol = Optional.empty();
		
		if(!volAlreadyExistsInCatalogo)
			 Optional.ofNullable(volunteersList.get(tel));
		else
			return true;
		
		return vol.isPresent();
	}

}
