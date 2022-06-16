package pt.migrantmatcher.facade.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pt.migrantmatcher.domain.Aid;
import pt.migrantmatcher.domain.MigrantFamily;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.domain.Migrant;
import pt.migrantmatcher.exceptions.InfoFamilyMemberException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.exceptions.RegionInsertedIsNotValid;
import pt.migrantmatcher.exceptions.AidIsNonExistenceException;
import pt.migrantmatcher.exceptions.AidIsNotValidException;
import pt.migrantmatcher.exceptions.ErrorCreatingCurAidException;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
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

		if(name == null || name.isEmpty())
			throw new RegisterIsNotValidException();

		this.migCurr = this.catMig.createIndividualMigrant(name, tel); //1

	}

	public void startFamiltRegister(int nPersons) throws RegisterIsNotValidException {

		if(nPersons <= 0)
			throw new RegisterIsNotValidException();

		this.migCurr = this.catMig.createMigrantFamily(nPersons); //1

	}

	public void addHeadInfo(String name, int tel) throws RegisterIsNotValidException {

		if(name == null || name.isEmpty())
			throw new RegisterIsNotValidException();

		this.catMig.addInfoHead(migCurr, name, tel); //1

	}

	public void insertFamilyMemberRegister (String name) throws RegisterIsNotValidException {

		if(name == null || name.isEmpty())
			throw new RegisterIsNotValidException();

		this.catMig.addInfoNames(migCurr, name);//1

	}

	public List <String> requestListOfRegions () throws InfoFamilyMemberException{

		if(this.migCurr instanceof MigrantFamily && !((MigrantFamily) this.migCurr).numberOfFamilyMembersIsValid() )
			throw new InfoFamilyMemberException();

		return this.catReg.getRegions(); //1
	}

	public List <AidDTO> insertChoosenRegion(String filename, String reg) throws AidIsNonExistenceException, PropertiesLoadingException {

		Map <Integer, Aid> aidsList = this.catAids.filterByReg(reg); //1

		if(aidsList.isEmpty())
			throw new AidIsNonExistenceException();

		MigrantConfiguration ordemAjudas = MigrantConfiguration.getInstance(filename);
		OrderAids order;
		try {
			order = ordemAjudas.getClass("orderHelpType");
			return orderList(order, aidsList);
		} catch (PropertiesLoadingException e) {
			System.err.println("There is no value in the properties file but it will be used a default value!");
			order = new OrderByStrategyType();
			return orderList(order, aidsList);
		}	

	}

	private List<AidDTO> orderList(OrderAids order, Map<Integer, Aid> aidsList) {
		List <AidDTO> aidDtoList = new ArrayList<>();

		order.order(aidsList).stream().forEach( a -> {
			aidDtoList.add(new AidDTO(a.getRef(), a.getInfo(), a.getAvailability(), a.getVol(), a.getType()));
		});
		
		return aidDtoList;
	}

	public void choosenAid(AidDTO aidDTO) throws ErrorCreatingCurAidException, AidIsNotValidException {

		if(aidDTO == null)
			throw new AidIsNotValidException();

		this.curAid = this.catAids.getAid(aidDTO); //1

		if(this.curAid.equals(null))
			throw new ErrorCreatingCurAidException();
	}

	public void registerConfirm(AidDTO aidDTO) throws NoFileNameException, PropertiesLoadingException {
		sendSMS(this.filename, "The migrant, " + this.migCurr.getName() + " wants your registered aid: " 
				+ this.curAid.toString(), this.curAid.getVol());
		
		this.curAid.putAidToNotAvailable();
		aidDTO.set(this.curAid.getAvailability());
	}

	public void requestsToBeNotified(String reg) throws RegionInsertedIsNotValid {

		if(reg == null)
			throw new RegionInsertedIsNotValid();

		this.catAids.addObserver(this.migCurr, reg);

	}

}
