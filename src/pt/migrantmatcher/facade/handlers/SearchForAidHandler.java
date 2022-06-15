package pt.migrantmatcher.facade.handlers;

import java.util.List;
import java.util.stream.Collectors;

import pt.migrantmatcher.domain.Aid;
import pt.migrantmatcher.domain.AidsCatalog;
import pt.migrantmatcher.domain.MigrantsCatalog;
import pt.migrantmatcher.domain.RegionCatalog;
import pt.migrantmatcher.domain.MigrantFamily;
import pt.migrantmatcher.domain.IndividualMigrant;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.domain.Migrant;
import pt.migrantmatcher.domain.Region;
import pt.migrantmatcher.exceptions.InfoFamilyMemberException;
import pt.migrantmatcher.exceptions.AidIsNonExistenceException;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.facade.DTO.AidDTO;
import pt.migrantmatcher.plugins.SendSMSHelper;
import pt.migrantmatcher.strategies.OrderAids;
import pt.migrantmatcher.strategies.OrderByStrategyType;

public class SearchForAidHandler extends SendSMSHelper {

	private MigrantsCatalog catMig;
	private Migrant migCurr;
	private RegionCatalog catReg;
	private AidsCatalog catAids;
	private Aid curAid;
	private static SearchForAidHandler INSTANCE = null; // Lazy loading colocar a null

	protected SearchForAidHandler(MigrantsCatalog catMig, RegionCatalog catReg, AidsCatalog catAid ) {

		this.catMig = catMig;
		this.catReg = catReg;
		this.catAids = catAid;

	}
	
	public static SearchForAidHandler getInstance(MigrantsCatalog catMig, RegionCatalog catReg, AidsCatalog catAj) {
		if (INSTANCE == null) {
			INSTANCE = new SearchForAidHandler(catMig, catReg, catAj);
		}

		return SearchForAidHandler.INSTANCE;
	}

	public void startPersonalRegister(String name, int tel) throws RegisterIsNotValidException {

		int size = String.valueOf(tel).length();

		if(size != 9 || name.isBlank())
			throw new RegisterIsNotValidException();

		this.migCurr = this.catMig.createIndividualMigrant(name, tel); //1

	}

	public void startFamiltRegister(int nPessoas) {

		this.migCurr = this.catMig.createMigrantFamily(nPessoas); //1

	}

	public void addHeadInfo(String name, int tel) throws RegisterIsNotValidException {

		if(name.isBlank())
			throw new RegisterIsNotValidException();

		this.catMig.addInfoHead(migCurr, name, tel); //1

	}

	public void insertFamilyMemberRegister (String name) {

		this.catMig.addInfoNames(migCurr, name);//1

	}

	public List <String> requestListOfRegions () throws InfoFamilyMemberException{

		if(migCurr instanceof MigrantFamily && !((MigrantFamily) migCurr).numberOfFamilyMembersIsValid() )
			throw new InfoFamilyMemberException();

		return this.catReg.getRegions(); //1
	}

	public List <AidDTO> insertChoosenRegion(String reg) throws AidIsNonExistenceException {

		List <Aid> aidsList = this.catAids.filterByReg(reg); //1

		if(aidsList.isEmpty())
			throw new AidIsNonExistenceException();
	
		MigrantConfiguration ordemAjudas = MigrantConfiguration.getInstance();
		OrderAids order = ordemAjudas.getClass("orderHelpType", new OrderByStrategyType());
		return order.order(aidsList)
					.stream()
					.map(a -> new AidDTO(a.getInfo(), a.getType()))
					.collect(Collectors.toList());
	}

	public void choosenAid(AidDTO ajudaDTO) {

		this.curAid = this.catAids.getAid(ajudaDTO); //1

	}

	public void registerConfirm() {
		
			this.catMig.addAid(this.migCurr, this.curAid);
			sendSMS("The migrant, " + ((IndividualMigrant) migCurr).getName() + " wants your registered aid: " 
					+ this.curAid.toString(), this.curAid.getVol());
			curAid.putAidToNotAvailable();
			
	}

	public void requestsToBeNotified(Region region) {
		
		this.catAids.addObserver(this.migCurr, region.getName());
		
	}

}
