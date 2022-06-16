package pt.migrantmatcher.facade.handlers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import pt.migrantmatcher.domain.Aid;
import pt.migrantmatcher.domain.Region;
import pt.migrantmatcher.domain.Voluntary;
import pt.migrantmatcher.exceptions.AidIsNotValidException;
import pt.migrantmatcher.exceptions.ErrorInsertingInCatalogException;
import pt.migrantmatcher.exceptions.ErrorSettingCodException;
import pt.migrantmatcher.exceptions.IncorrectCodException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.exceptions.RegionInsertedIsNotValid;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.exceptions.ThereIsNoRegionCatalogoException;
import pt.migrantmatcher.plugins.SendSMSHelper;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockRegCatalog;
import tests.mocks.MockVolunteersCatalog;

public class RegisterAidHandler extends SendSMSHelper{

	private MockVolunteersCatalog catVol;
	private MockRegCatalog catReg;
	private MockAidsCatalog catAid;
	private Voluntary volCurr;
	private Aid currAid;
	private String filename;


	public RegisterAidHandler(String filename, MockAidsCatalog mockAidCatalog, MockVolunteersCatalog catVol, MockRegCatalog catReg) {
		
		this.catVol = catVol;
		this.filename = filename;
		this.catReg = catReg;
		this.catAid = mockAidCatalog;

	}
	
	public void aidRegisterStart(int tel) throws RegisterIsNotValidException {
		
		volCurr = this.catVol.getVol(tel); // 1

		if(volCurr.getTel() != tel)
			throw new RegisterIsNotValidException();

	}

	public List <String> offerHousing(int nPersons) throws AidIsNotValidException, RegisterIsNotValidException, ThereIsNoRegionCatalogoException{

		if(nPersons <= 0)
			throw new RegisterIsNotValidException();

		this.currAid = this.catAid.createHousing(nPersons); //1

		if(this.currAid.toString().isBlank())
			throw new AidIsNotValidException();
		if(this.catReg.getRegions().isEmpty())
			throw new ThereIsNoRegionCatalogoException();

		return this.catReg.getRegions(); //2
	}

	public void insertHousingRegion(String region) throws RegionInsertedIsNotValid, PropertiesLoadingException, NoFileNameException, ErrorSettingCodException {

		if(!this.catReg.isValid(region))
			throw new RegionInsertedIsNotValid();
		

		this.catAid.insertReg(currAid, new Region (region)); //1
		sendSMS(this.filename, "Your confirmation code: " + generateCod(), volCurr.getTel());
		
	}

	public void offerItem(String desc) throws AidIsNotValidException, PropertiesLoadingException {

		if(desc.isBlank())
			throw new AidIsNotValidException();

		this.currAid = this.catAid.getNewItem(desc); //1
		try {
			sendSMS(this.filename, "Your confirmation code: " + generateCod(), volCurr.getTel());
		} catch (NoFileNameException | PropertiesLoadingException | ErrorSettingCodException e) {
			System.err.println("There file is missing a value, it will be used a default value!");
			throw new PropertiesLoadingException(); 
		}

	}

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

	public void offerConfirm(String cod) throws IncorrectCodException, ErrorInsertingInCatalogException {

		if(this.volCurr.checkValidCod(cod)) {
		
			this.catVol.addVolToCatalog(this.volCurr);
			this.catAid.addAid(this.filename, this.currAid, this.volCurr, this.catReg.getRegions()); //2
			
			Optional <Boolean> aidIsInCatalog = this.catAid.aidWasAdded(this.currAid);	
			
			if(aidIsInCatalog.isEmpty())
				throw new ErrorInsertingInCatalogException();

		} else
			throw new IncorrectCodException();
	}
}
