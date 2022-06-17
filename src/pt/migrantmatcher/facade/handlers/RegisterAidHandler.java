package pt.migrantmatcher.facade.handlers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import pt.migrantmatcher.domain.Aid;
import pt.migrantmatcher.domain.Region;
import pt.migrantmatcher.domain.RegionCatalog;
import pt.migrantmatcher.domain.Voluntary;
import pt.migrantmatcher.exceptions.AidIsNotValidException;
import pt.migrantmatcher.exceptions.ErrorInsertingInCatalogException;
import pt.migrantmatcher.exceptions.ErrorSettingCodException;
import pt.migrantmatcher.exceptions.IncorrectCodException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.exceptions.RegionInsertedIsNotValid;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.exceptions.ThereIsNoRegionCatalogoException;
import pt.migrantmatcher.plugins.SendSMSHelper;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockVolunteersCatalog;

/**
 * Handler do caso de uso registar ajuda - Controller
 * 
 * @author Ana Luis FC53563
 */
public class RegisterAidHandler extends SendSMSHelper{

	private MockVolunteersCatalog catVol;
	private RegionCatalog catReg;
	private MockAidsCatalog catAid;
	private Voluntary volCurr;
	private Aid currAid;
	
	/**
	 * Cria o handler
	 * @param mockAidCatalog - catalogo de ajudas
	 * @param catVol - catalogo Voluntarios
	 * @param catReg - catalogo Regioes
	 */
	public RegisterAidHandler(MockAidsCatalog mockAidCatalog, MockVolunteersCatalog catVol, RegionCatalog catReg) {
		
		this.catVol = catVol;
		this.catReg = catReg;
		this.catAid = mockAidCatalog;

	}
	
	/**
	 * Inicia o registo de Ajuda
	 * @param tel - nr de telemovel do voluntario
	 * @throws RegisterIsNotValidException caso nao seja um telemovel valido 
	 * ou houve erro a criar
	 */
	public void aidRegisterStart(int tel) throws RegisterIsNotValidException {
		
		volCurr = this.catVol.getVol(tel); // 1
		String length = Integer.toString(tel);
		
		if(volCurr.getTel() != tel && length.length() > 9)
			throw new RegisterIsNotValidException();
	}
	
	/**
	 * Oferece tipo de ajuda Housing
	 * @param nPersons - nr de pessoas que a casa pode ter
	 * @return lista de regioes
	 * @throws AidIsNotValidException  se a ajuda nao for valida
	 * @throws RegisterIsNotValidException se registo nao for valido
	 * @throws ThereIsNoRegionCatalogoException se nao existir catalogos de regioes
	 */
	public List <String> offerHousing(int nPersons) throws AidIsNotValidException, RegisterIsNotValidException, ThereIsNoRegionCatalogoException{

		if(nPersons <= 0)
			throw new RegisterIsNotValidException();

		this.currAid = this.catAid.createHousing(nPersons); //1

		if(this.currAid == null)
			throw new AidIsNotValidException();
		if(this.catReg.getRegions().isEmpty())
			throw new ThereIsNoRegionCatalogoException();

		return this.catReg.getRegions(); //2
	}

	/**
	 * Coloca a regiao em que o alojamento esta
	 * @param filename - nome ficheiro
	 * @throws RegionInsertedIsNotValid
	 * @throws PropertiesLoadingException
	 * @throws ErrorSettingCodException
	 */
	public void insertHousingRegion(String region) throws RegionInsertedIsNotValid, PropertiesLoadingException, ErrorSettingCodException {

		if(!this.catReg.isValid(region))
			throw new RegionInsertedIsNotValid();

		this.catAid.insertReg(this.currAid, new Region (region)); //1
		sendSMS("Your confirmation code: " + generateCod(), volCurr.getTel());
	}

	/**
	 * Oferece um item 
	 * @param desc - descreve o item
	 * @throws AidIsNotValidException
	 * @throws PropertiesLoadingException
	 */
	public void offerItem(String desc) throws AidIsNotValidException, PropertiesLoadingException {

		if(desc.isBlank())
			throw new AidIsNotValidException();

		this.currAid = this.catAid.getNewItem(desc); //1
		
		try {
			sendSMS("Your confirmation code: " + generateCod(), volCurr.getTel());
		} catch (PropertiesLoadingException | ErrorSettingCodException e) {
			System.err.println("There file is missing a value, it will be used a default value!");
			throw new PropertiesLoadingException(); 
		}

	}

	/**
	 * Cria codigo random que vai ser enviado para o utilizador
	 * @return codigo random 
	 * @throws ErrorSettingCodException
	 */
	private String generateCod() throws ErrorSettingCodException {
		String cod = new Random().ints(6,48,123)
				.filter( x -> ((x < 58 || x > 64) && (x < 91 || x > 96) ))
				.map( x -> (char) x)
				.collect(StringBuilder::new, 
						StringBuilder::appendCodePoint,
						StringBuilder::append)
				.toString();
		this.volCurr.setCod(cod);

		if(!volCurr.checkValidCod(cod))
			throw new ErrorSettingCodException();

		return cod;
	}
	
	/**
	 * Confirma se o codigo esta correto e confirma a oferta
	 * @param cod - codigo gerado
	 * @throws IncorrectCodException
	 * @throws ErrorInsertingInCatalogException
	 */
	public void offerConfirm(String cod) throws IncorrectCodException, ErrorInsertingInCatalogException {

		if(this.volCurr.checkValidCod(cod)) {
		
			this.catVol.addVolToCatalog(this.volCurr);
			this.catAid.addAid(this.currAid, this.volCurr, this.catReg.getRegions()); //2
			
			Optional <Boolean> aidIsInCatalog = this.catAid.aidWasAdded(this.currAid);	
			
			if(aidIsInCatalog.isEmpty())
				throw new ErrorInsertingInCatalogException();

		} else
			throw new IncorrectCodException();
	}
}
