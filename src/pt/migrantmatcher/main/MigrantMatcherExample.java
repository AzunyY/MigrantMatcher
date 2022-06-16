package pt.migrantmatcher.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.migrantmatcher.exceptions.AidIsNotValidException;
import pt.migrantmatcher.exceptions.ErrorCreatingCurAidException;
import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.ErrorInsertingInCatalogException;
import pt.migrantmatcher.exceptions.ErrorSettingCodException;
import pt.migrantmatcher.exceptions.IncorrectCodException;
import pt.migrantmatcher.exceptions.InfoFamilyMemberException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.exceptions.RegionInsertedIsNotValid;
import pt.migrantmatcher.exceptions.AidIsNonExistenceException;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.exceptions.ThereIsNoRegionCatalogoException;
import pt.migrantmatcher.exceptions.ThereIsNoValueInPropertiesException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.DTO.AidDTO;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

public class MigrantMatcherExample {

	public static void main(String[] args) throws ErrorSettingCodException, NoFileNameException, ErrorCreatingCurAidException{

		List <String> reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");
		
		RegisterAidHandler registerAidHandler = null;
		SearchForAidHandler searchAidHandler = null;
		MigrantMatcherSystem migMatch = null;
		
		try {
			migMatch = new MigrantMatcherSystem("defaults3.properties", reg);
			registerAidHandler = migMatch.registerNewAid();
			searchAidHandler = migMatch.searchForAid();
		} catch (ErrorCreatingRegionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoFileNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);

		List <String> familyMembersList = new ArrayList<>();
		familyMembersList.add("Maria");
		familyMembersList.add("Vanessa");
		familyMembersList.add("Paulo");


		// UC1
		try {
			registerAidHandler.aidRegisterStart(937977373);
			registerAidHandler.offerHousing(3);
			registerAidHandler.insertHousingRegion(reg.get(0));
			
			System.out.println("Insert the code that was sent: ");
			registerAidHandler.offerConfirm(sc.nextLine().toString());

		}  catch (AidIsNotValidException e) {
			System.err.println("The aid offered is not valid!");
		} catch (IncorrectCodException e) {
			System.err.println("The code that was inserted is not valid!");
		} catch (RegisterIsNotValidException e) {
			System.err.println("There was an error while registering!");
		} catch (ErrorInsertingInCatalogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertiesLoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ThereIsNoRegionCatalogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegionInsertedIsNotValid e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//UC2
		try {

			searchAidHandler.startFamiltRegister(3);
			searchAidHandler.addHeadInfo("Joao", 939439495);

			for(int i = 0; i < familyMembersList.size(); i++)
				searchAidHandler.insertFamilyMemberRegister(familyMembersList.get(i));

			List<String> regList = searchAidHandler.requestListOfRegions();
			System.out.println(regList.toString());
			List <AidDTO> aid = searchAidHandler.insertChoosenRegion(regList.get(0), null);

			System.out.print("\n > List of Aids < \n");
			aid.forEach( a -> {
				System.out.println(a.getInfo());
			});

			System.out.println(aid.toString());
			searchAidHandler.choosenAid(aid.get(0));
			searchAidHandler.registerConfirm();

		} catch(RegisterIsNotValidException e) {
			System.err.println("The register is not valid - Insert valid name!");
		} catch(InfoFamilyMemberException e) { 
			System.err.println("The number of family members which information you have inserted is not valid!");
		} catch (AidIsNonExistenceException e) {
			searchAidHandler.requestsToBeNotified(reg.get(0));
			System.err.println("There's no available aid at: " + reg.get(0).toString() + ", you will be notified when it does!");
		} catch (ThereIsNoValueInPropertiesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoFileNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertiesLoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sc.close();
	}
}