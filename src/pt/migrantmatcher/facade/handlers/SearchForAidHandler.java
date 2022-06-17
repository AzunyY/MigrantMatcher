package pt.migrantmatcher.facade.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pt.migrantmatcher.domain.Aid;
import pt.migrantmatcher.domain.MigrantFamily;
import pt.migrantmatcher.domain.RegionCatalog;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.domain.Migrant;
import pt.migrantmatcher.exceptions.InfoFamilyMemberException;
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

/**
 * Handler do caso de uso procurar ajuda - Controller
 */
public class SearchForAidHandler extends SendSMSHelper {

	private MockMigrantsCatalog catMig;
	private Migrant migCurr;
	private RegionCatalog catReg;
	private MockAidsCatalog catAids;
	private String filename;
	private Aid curAid;

	/**
	 * Cria um handler
	 * @param mockAidCatalog - catalogo de ajudas
	 * @param mockMigsCat - catalogo de migrantes
	 * @param mockRegCatalog - catalogo de Reg
	 */
	public SearchForAidHandler(MockAidsCatalog mockAidCatalog, MockMigrantsCatalog mockMigsCat,
			RegionCatalog regCatalog) {

		this.catMig = mockMigsCat;
		this.catReg = regCatalog;
		this.catAids = mockAidCatalog;;
	}

	/**
	 * Inicia Registo Pessoal
	 * @param name - nome do migrante
	 * @param tel - numero telemovel do migrante
	 * @throws RegisterIsNotValidException
	 */
	public void startPersonalRegister(String name, int tel) throws RegisterIsNotValidException {

		if(name == null || name.isEmpty())
			throw new RegisterIsNotValidException();

		this.migCurr = this.catMig.createIndividualMigrant(name, tel); //1

	}

	/**
	 * Inicia Registo de famila de migrantes
	 * @param nPersons - numero de pessoas que a familia tem excluindo o cabeca
	 * @throws RegisterIsNotValidException 
	 */
	public void startFamiltRegister(int nPersons) throws RegisterIsNotValidException {

		if(nPersons <= 0)
			throw new RegisterIsNotValidException();

		this.migCurr = this.catMig.createMigrantFamily(nPersons); //1

	}

	/**
	 * Adiciona informacao do cabeca de famila
	 * @param name - nome do cabeca
	 * @param tel - nr de telemovel do cabeca
	 * @throws RegisterIsNotValidException
	 */
	public void addHeadInfo(String name, int tel) throws RegisterIsNotValidException {

		if(name == null || name.isEmpty())
			throw new RegisterIsNotValidException();

		this.catMig.addInfoHead(migCurr, name, tel); //1

	}

	/**
	 * Insere informacao dos familiares
	 * @param name - nome dos familiares
	 * @throws RegisterIsNotValidException
	 */
	public void insertFamilyMemberRegister (String name) throws RegisterIsNotValidException {

		if(name == null || name.isEmpty())
			throw new RegisterIsNotValidException();

		this.catMig.addInfoNames(migCurr, name);//1

	}

	/**
	 * Pede a lista de regioes
	 * @return lista de regioes
	 * @throws InfoFamilyMemberException
	 */
	public List <String> requestListOfRegions () throws InfoFamilyMemberException{

		if(this.migCurr instanceof MigrantFamily && !((MigrantFamily) this.migCurr).numberOfFamilyMembersIsValid() )
			throw new InfoFamilyMemberException();

		return this.catReg.getRegions(); //1
	}

	/**
	 * Insere uma regiao e devolve a sua lista de ajudas
	 * @param filename - nome de ficheiro
	 * @return lista de ajudas da regiao
	 * @throws AidIsNonExistenceException
	 * @throws PropertiesLoadingException
	 */
	public List <AidDTO> insertChoosenRegion(String reg) throws AidIsNonExistenceException, PropertiesLoadingException {

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

	/**
	 * Auxiliar - ordena e devolve a lista de ajudas de uma regiao
	 * @param order - estrategia de ordenar
	 * @param aidsList - lista a ordenar
	 * @return lista ordenada de ajudas
	 */
	private List<AidDTO> orderList(OrderAids order, Map<Integer, Aid> aidsList) {
		List <AidDTO> aidDtoList = new ArrayList<>();

		order.order(aidsList).stream().forEach( a -> {
			aidDtoList.add(new AidDTO(a.getRef(), a.getInfo(), a.getAvailability(), a.getVol(), a.getType()));
		});
		
		return aidDtoList;
	}

	/**
	 * Escolhe uma ajuda disponivel
	 * @param aidDTO - ajuda 
	 * @throws ErrorCreatingCurAidException
	 * @throws AidIsNotValidException
	 */
	public void choosenAid(AidDTO aidDTO) throws ErrorCreatingCurAidException, AidIsNotValidException {

		if(aidDTO == null)
			throw new AidIsNotValidException();

		this.curAid = this.catAids.getAid(aidDTO); //1

		if(this.curAid.equals(null))
			throw new ErrorCreatingCurAidException();
	}

	/**
	 * Confirma o registo de ajuda
	 * @param aidDTO - ajuda
	 * @throws PropertiesLoadingException
	 */
	public void registerConfirm(AidDTO aidDTO) throws PropertiesLoadingException {
		sendSMS("The migrant, " + this.migCurr.getName() + " wants your registered aid: " 
				+ this.curAid.toString(), this.curAid.getVol());
		
		this.curAid.putAidToNotAvailable();
		aidDTO.set(this.curAid.getAvailability());
	}

	/**
	 * Pede para ser notificado caso nao haja ajudas na regiao
	 * @param reg - regiao 
	 * @throws RegionInsertedIsNotValid
	 */
	public void requestsToBeNotified(String reg) throws RegionInsertedIsNotValid {

		if(reg == null)
			throw new RegionInsertedIsNotValid();

		this.catAids.addObserver(this.migCurr, reg);

	}

}
