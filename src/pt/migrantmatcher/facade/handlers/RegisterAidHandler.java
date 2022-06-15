package pt.migrantmatcher.facade.handlers;

import java.util.List;
import java.util.Random;

import pt.migrantmatcher.domain.Aid;
import pt.migrantmatcher.domain.AidsCatalog;
import pt.migrantmatcher.domain.RegionCatalog;
import pt.migrantmatcher.domain.VolunteersCatalog;
import pt.migrantmatcher.domain.Region;
import pt.migrantmatcher.domain.Voluntary;
import pt.migrantmatcher.exceptions.AidIsNotValidException;
import pt.migrantmatcher.exceptions.IncorrectCodException;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.plugins.SendSMSHelper;

public class RegisterAidHandler extends SendSMSHelper{

	private VolunteersCatalog catVol;
	private RegionCatalog catReg;
	private AidsCatalog catAid;
	private Voluntary volCurr;
	private Aid currAid;

	private static RegisterAidHandler INSTANCE = null; // Lazy loading colocar a null

	protected RegisterAidHandler(AidsCatalog catAid, VolunteersCatalog catVol, RegionCatalog catReg) {

		this.catVol = catVol;
		this.catReg = catReg;
		this.catAid = catAid;

	}

	public static RegisterAidHandler getInstance(AidsCatalog catAid, VolunteersCatalog catVol, RegionCatalog catReg) {

		if (INSTANCE == null) {
			INSTANCE = new RegisterAidHandler(catAid, catVol, catReg);
		}

		return RegisterAidHandler.INSTANCE;

	}

	public void aidRegisterStart(int tel) {

		volCurr = this.catVol.getVol(tel); // 1

	}

	public List <String> offerHousing(int nPersons){

		this.currAid = this.catAid.createHousing(nPersons); //1
		return this.catReg.getRegions(); //2

	}

	public void insertHousingRegion(Region region) {

		this.catAid.insertReg(currAid, region); //1
		sendSMS("O seu codigo de confirmacao: " + generateCod(), volCurr.getTel());

	}

	public void offerItem(String desc) throws AidIsNotValidException{

		if(desc.isBlank())
			throw new AidIsNotValidException();

		this.currAid = this.catAid.getNewItem(desc); //1
		sendSMS("Your confirmation code: " + generateCod(), volCurr.getTel());

	}

	private String generateCod() {
		String cod = new Random().ints(6,48,123)
				.filter( x -> ((x < 58 || x > 64) && (x < 91 || x > 96) ))
				.map( x -> (char) x)
				.collect(StringBuilder::new, 
						StringBuilder::appendCodePoint,
						StringBuilder::append)
				.toString();
		this.volCurr.setCod(cod);
		return cod;
	}

	public void offerConfirm(String cod) throws IncorrectCodException {

		if(this.volCurr.checkValidCod(cod)) {
			this.catVol.addVolToCatalog(this.volCurr);
			this.catAid.addAid(this.currAid, this.volCurr, this.catReg.getRegions()); //2
		} else
			throw new IncorrectCodException();

	}
}
