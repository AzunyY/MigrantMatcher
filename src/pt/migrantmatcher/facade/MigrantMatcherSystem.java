package pt.migrantmatcher.facade;

import java.util.List;

import pt.migrantmatcher.domain.AidsCatalog;
import pt.migrantmatcher.domain.MigrantsCatalog;
import pt.migrantmatcher.domain.RegionCatalog;
import pt.migrantmatcher.domain.VolunteersCatalog;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

public class MigrantMatcherSystem {
	
	private RegionCatalog catReg;
	private MigrantsCatalog catMig;
	private AidsCatalog catAids;
	private VolunteersCatalog catVol;
	
	public MigrantMatcherSystem(List <String> reg) {
		
		this.catMig = new MigrantsCatalog();
		this.catAids = new AidsCatalog();
		this.catVol = new VolunteersCatalog();
		
		MigrantConfiguration catReg = MigrantConfiguration.getInstance();
		List<String> listReg = catReg.getProperty("regioes", reg);
		this.catReg = new RegionCatalog(listReg);
		
	}
	
	// UC1
	public RegisterAidHandler registerNewAid() {
		return RegisterAidHandler.getInstance(catAids, catVol, catReg);
	}

	// UC2
	public SearchForAidHandler searchForAid() {
		return SearchForAidHandler.getInstance(catMig, catReg, catAids);
	}	
}
