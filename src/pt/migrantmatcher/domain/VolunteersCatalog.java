package pt.migrantmatcher.domain;

import java.util.HashMap;
import java.util.Optional;

public class VolunteersCatalog {

	private HashMap <Integer, Voluntary> volunteersList;
	private boolean volAlreadyExistsInCatalogo;

	public VolunteersCatalog() {
		volunteersList = new HashMap <>();
		volAlreadyExistsInCatalogo = false;
	}

	public Voluntary getVol(int tel) {
		
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

	public void addVolToCatalog(Voluntary currVol) {
		
		if(!volAlreadyExistsInCatalogo)  //1.2
			volunteersList.put(currVol.getTel(), currVol); //1.3
	}
	
	public Optional<Voluntary> volWasAdded(int tel) {
		Optional<Voluntary> vol = Optional.ofNullable(volunteersList.get(tel));
		return vol;
	}
}
