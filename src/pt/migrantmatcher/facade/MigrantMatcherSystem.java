package pt.migrantmatcher.facade;

import java.util.List;

import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockMigrantsCatalog;
import tests.mocks.MockRegCatalog;
import tests.mocks.MockVolunteersCatalog;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

public class MigrantMatcherSystem {

	private MockRegCatalog catReg;
	private MockMigrantsCatalog catMig = new MockMigrantsCatalog();
	private MockAidsCatalog catAids = new MockAidsCatalog();
	private MockVolunteersCatalog catVol = new MockVolunteersCatalog();
	private String filename;

	public MigrantMatcherSystem(String filename, List <String> catReg) throws PropertiesLoadingException, ErrorCreatingRegionsException {	
		this(filename, new MockRegCatalog (filename, catReg));
		this.filename = filename;
	}

	public MigrantMatcherSystem(String filename, MockRegCatalog catReg) {
		this.catReg = catReg;
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
