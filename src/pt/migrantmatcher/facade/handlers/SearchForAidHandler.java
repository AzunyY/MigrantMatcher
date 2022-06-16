package pt.migrantmatcher.facade.handlers;

import java.util.List;
import java.util.stream.Collectors;

import pt.migrantmatcher.domain.Aid;
import pt.migrantmatcher.domain.MigrantFamily;
import pt.migrantmatcher.domain.IndividualMigrant;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.domain.Migrant;
import pt.migrantmatcher.exceptions.InfoFamilyMemberException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.exceptions.AidIsNonExistenceException;
import pt.migrantmatcher.exceptions.ErrorCreatingCurAidException;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.exceptions.ThereIsNoValueInPropertiesException;
import pt.migrantmatcher.facade.DTO.AidDTO;
import pt.migrantmatcher.plugins.SendSMSHelper;
import pt.migrantmatcher.strategies.OrderAids;
import pt.migrantmatcher.strategies.OrderByStrategyType;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockMigrantsCatalog;
import tests.mocks.MockRegCatalog;

public class SearchForAidHandler extends SendSMSHelper {

	private MockMigrantsCatalog catMig;
	private Migrant migCurr;
	private MockRegCatalog catReg;
	private MockAidsCatalog catAids;
	private String filename;
	private Aid curAid;

	public SearchForAidHandler(String filename, MockAidsCatalog mockAidCatalog, MockMigrantsCatalog mockMigsCat,
			MockRegCatalog mockRegCatalog) {

		this.catMig = mockMigsCat;
		this.catReg = mockRegCatalog;
		this.catAids = mockAidCatalog;;
		this.filename = filename;

	}

	public void startPersonalRegister(String name, int tel) throws RegisterIsNotValidException {

		if(name.isEmpty())
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

	public List <AidDTO> insertChoosenRegion(String filename, String reg) throws AidIsNonExistenceException, ThereIsNoValueInPropertiesException {

		List <Aid> aidsList = this.catAids.filterByReg(reg); //1

		if(aidsList.isEmpty())
			throw new AidIsNonExistenceException();

		MigrantConfiguration ordemAjudas = MigrantConfiguration.getInstance(filename);
		OrderAids order;
		try {
			order = ordemAjudas.getClass("orderHelpType");
			return order.order(aidsList)
					.stream()
					.map(a -> new AidDTO(a.getInfo(), a.getType(), a.getAvailability()))
					.collect(Collectors.toList());
		} catch (ThereIsNoValueInPropertiesException | PropertiesLoadingException e) {
			System.err.println("There is no value in the properties file but it will be used a default value!");
			order = new OrderByStrategyType();
			return order.order(aidsList)
					.stream()
					.map(a -> new AidDTO(a.getInfo(), a.getType(), a.getAvailability()))
					.collect(Collectors.toList());
		}	

	}

	public void choosenAid(AidDTO ajudaDTO) throws ErrorCreatingCurAidException {

		this.curAid = this.catAids.getAid(ajudaDTO); //1
		
		if(this.curAid.equals(null))
			throw new ErrorCreatingCurAidException();

	}

	public void registerConfirm() throws NoFileNameException, PropertiesLoadingException {

		this.catMig.addAid(this.migCurr, this.curAid);
		sendSMS(this.filename, "The migrant, " + ((IndividualMigrant) migCurr).getName() + " wants your registered aid: " 
				+ this.curAid.toString(), this.curAid.getVol());
		curAid.putAidToNotAvailable();

	}

	public void requestsToBeNotified(String reg) {

		this.catAids.addObserver(this.migCurr, reg);

	}

}
