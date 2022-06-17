package pt.migrantmatcher.facade;

import java.util.List;

import pt.migrantmatcher.domain.RegionCatalog;
import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockMigrantsCatalog;
import tests.mocks.MockVolunteersCatalog;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

/**
 * Esta é a classe do sistema.
 */
public class MigrantMatcherSystem {

	private RegionCatalog catReg;
	private MockMigrantsCatalog catMig;
	private MockAidsCatalog catAids;
	private MockVolunteersCatalog catVol;

	/**
	 * Construtor do sistema
	 * @param mockAidCatalog 
	 * @param mockVolCat 
	 * @param filename - ficheiro properties
	 * @param reg - Lista de valores defualt
	 * @throws PropertiesLoadingException Se nao houver valores no ficheiro
	 * @throws ErrorCreatingRegionsException
	 */
	public MigrantMatcherSystem(MockVolunteersCatalog mockVolCat, MockAidsCatalog mockAidCatalog, List<String> reg) throws PropertiesLoadingException, ErrorCreatingRegionsException {	
		this.catReg = new RegionCatalog (reg);
		this.catAids = mockAidCatalog;
		this.catVol = mockVolCat;
	}
	
	public MigrantMatcherSystem(MockMigrantsCatalog mockMigCat, MockAidsCatalog mockAidCatalog, List<String> reg) throws PropertiesLoadingException, ErrorCreatingRegionsException {	
		this.catReg = new RegionCatalog (reg);
		this.catAids = mockAidCatalog;
		this.catMig = mockMigCat;
	}
	
	public MigrantMatcherSystem(MockMigrantsCatalog mockMigCat, MockAidsCatalog mockAidCatalog, MockVolunteersCatalog mockVolCat, List<String> reg) throws PropertiesLoadingException, ErrorCreatingRegionsException {	
		this.catReg = new RegionCatalog (reg);
		this.catAids = mockAidCatalog;
		this.catVol = mockVolCat;
		this.catMig = mockMigCat;
	}
	/**
	 * UC1
	 * Devolve um RegisterAidhandler
	 * @return RegisterAidhandler
	 */
	public RegisterAidHandler registerNewAid() {
		return new RegisterAidHandler(catAids, catVol, catReg);
	}

	/**
	 * UC2
	 * Devolve um searchAidhandler
	 * @return searchAidhandler
	 */
	public SearchForAidHandler searchForAid() {
		return new SearchForAidHandler(this.catAids, this.catMig, this.catReg) ;
	}	
}
