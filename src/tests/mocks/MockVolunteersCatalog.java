package tests.mocks;

import pt.migrantmatcher.domain.Voluntary;
import pt.migrantmatcher.domain.VolunteersCatalog;

/**
 * Pure Fabrication - classe artificial catalogo de voluntarios
 * @author Ana Luis FC53563 
 */
public class MockVolunteersCatalog extends VolunteersCatalog {
		
	public MockVolunteersCatalog() {
		super();
	}
	
	public Voluntary getVol(int tel) {
		return super.getVol(tel);
	}
	
	public void addVolToCatalog(Voluntary currVol) {
		super.addVolToCatalog(currVol);
	}

	public boolean volWasAdded(int tel) {
		return super.volWasAdded(tel);
	}
}
