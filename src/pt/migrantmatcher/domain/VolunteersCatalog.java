package pt.migrantmatcher.domain;

import java.util.HashMap;
import java.util.Optional;

public class VolunteersCatalog {

	protected HashMap <Integer, Voluntary> volunteersList;
	protected boolean volAlreadyExistsInCatalogo;

	protected VolunteersCatalog() {
		volunteersList = new HashMap <>();
		volAlreadyExistsInCatalogo = false;
	}

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

	protected void addVolToCatalog(Voluntary currVol) {

		if(!volAlreadyExistsInCatalogo)  //1.2
			volunteersList.put(currVol.getTel(), currVol); //1.3
	}

	protected boolean volWasAdded(int tel) {
		Optional<Voluntary> vol = Optional.empty();
		
		if(!volAlreadyExistsInCatalogo)
			 Optional.ofNullable(volunteersList.get(tel));
		else
			return true;
		
		return vol.isPresent();
	}

}
