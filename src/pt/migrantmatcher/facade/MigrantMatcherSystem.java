package pt.migrantmatcher.facade;

import java.util.List;

import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockMigrantsCatalog;
import tests.mocks.MockRegCatalog;
import tests.mocks.MockVolunteersCatalog;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

public class MigrantMatcherSystem {

	private MockRegCatalog catReg;
	private MockMigrantsCatalog catMig;
	private MockAidsCatalog catAids;
	private MockVolunteersCatalog catVol;
	private String filename;

	public MigrantMatcherSystem(String filename, List <String> reg) throws ErrorCreatingRegionsException, NoFileNameException {
		
		this.catMig = new MockMigrantsCatalog();
		this.catAids = new MockAidsCatalog();
		this.catVol = new MockVolunteersCatalog();
		this.catReg = new MockRegCatalog(filename, reg);
		this.filename = filename;
	}

	// UC1
	public RegisterAidHandler registerNewAid() {
		return new RegisterAidHandler(this.filename, catAids, catVol, catReg);
	}

	// UC2
	public SearchForAidHandler searchForAid() {
		return new SearchForAidHandler(this.filename, this.catAids, this.catMig, this.catReg) ;
	}	
}
