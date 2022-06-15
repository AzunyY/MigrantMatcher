package pt.migrantmatcher.facade;

import java.util.List;

import pt.migrantmatcher.domain.AidsCatalog;
import pt.migrantmatcher.domain.MigrantsCatalog;
import pt.migrantmatcher.domain.RegionCatalog;
import pt.migrantmatcher.domain.VolunteersCatalog;
import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptios.ThereIsNoValueInPropertiesException;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

public class MigrantMatcherSystem {

	private RegionCatalog catReg;
	private MigrantsCatalog catMig;
	private AidsCatalog catAids;
	private VolunteersCatalog catVol;
	private String filename;

	public MigrantMatcherSystem(String filename, List <String> reg) throws ErrorCreatingRegionsException, NoFileNameException {

		this.catMig = new MigrantsCatalog();
		this.catAids = new AidsCatalog();
		this.catVol = new VolunteersCatalog();		
		this.filename = filename;

		MigrantConfiguration catReg = MigrantConfiguration.getInstance(filename);
		List<String> listReg;

		if(filename.isBlank())
			throw new NoFileNameException();
		else {
			try {
				listReg = catReg.getProperty("regioes");
				this.catReg = new RegionCatalog(listReg);
			} catch (ThereIsNoValueInPropertiesException e) {
				if(reg.isEmpty())
					throw new ErrorCreatingRegionsException();

				this.catReg = new RegionCatalog(reg);
				System.err.println("There is no value in the properties file but it will be used a default value!");
			}		
		}
	}

	// UC1
	public RegisterAidHandler registerNewAid() {
		return RegisterAidHandler.getInstance(this.filename, catAids, catVol, catReg);
	}

	// UC2
	public SearchForAidHandler searchForAid() {
		return SearchForAidHandler.getInstance(this.filename, catMig, catReg, catAids);
	}	
}
