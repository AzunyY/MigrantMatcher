package pt.migrantmatcher.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.migrantmatcher.domain.Region;
import pt.migrantmatcher.exceptions.AidIsNotValidException;
import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.ErrorInsertingInCatalogException;
import pt.migrantmatcher.exceptions.ErrorSettingCod;
import pt.migrantmatcher.exceptions.IncorrectCodException;
import pt.migrantmatcher.exceptions.InfoFamilyMemberException;
import pt.migrantmatcher.exceptions.RegionInsertedIsNotValid;
import pt.migrantmatcher.exceptions.AidIsNonExistenceException;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.exceptions.ThereIsNoRegionCatalogoException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.DTO.AidDTO;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

public class MigrantMatcherExample {

	public static void main(String[] args){

		List <String> reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");
		
		RegisterAidHandler registerAidHandler = null;
		SearchForAidHandler searchAidHandler = null;
		MigrantMatcherSystem migMatch = null;
		
		try {
			migMatch = new MigrantMatcherSystem(reg);
			registerAidHandler = migMatch.registerNewAid();
			searchAidHandler = migMatch.searchForAid();
		} catch (ErrorCreatingRegionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);

		List <String> familyMembersList = new ArrayList<>();
		familyMembersList.add("Maria");
		familyMembersList.add("Vanessa");
		familyMembersList.add("Paulo");


		// UC1
		try {
			registerAidHandler.aidRegisterStart(937977373);
			registerAidHandler.offerItem("Roupa");
			System.out.println("Insert the code that was sent: ");
			registerAidHandler.offerConfirm(sc.nextLine().toString());

		}  catch (AidIsNotValidException e) {
			System.err.println("The aid offered is not valid!");
		} catch (IncorrectCodException e) {
			System.err.println("The code that was inserted is not valid!");
		} catch (RegisterIsNotValidException e) {
			System.err.println("There was an error while registering!");
		} catch (ErrorSettingCod e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorInsertingInCatalogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//UC2
		try {
			searchAidHandler.startPersonalRegister("Joao", 939243944);
			List<String> regList = searchAidHandler.requestListOfRegions();
			System.out.println(regList.toString());
			List <AidDTO> aj = searchAidHandler.insertChoosenRegion(regList.get(0));

			System.out.print("\n > List of Aids < \n");
			aj.forEach( a -> {
				System.out.println(a.getInfo());
			});

			searchAidHandler.choosenAid(aj.get(0));
			searchAidHandler.registerConfirm();

		} catch(RegisterIsNotValidException e) {
			System.err.println("The register is not valid - Insert valid name!");
		} catch (AidIsNonExistenceException e) {
			searchAidHandler.requestsToBeNotified(new Region(reg.get(0)));
			System.err.println("There's no available aid at: " + reg.get(0).toString() + ", you will be notified when it does!");
		} catch (InfoFamilyMemberException e) {
			System.err.println("The number of family members which information you have inserted is not valid!");
		}

		//UC1
		try {
			registerAidHandler.aidRegisterStart(937977373);
			registerAidHandler.offerHousing(3);
			registerAidHandler.insertHousingRegion(reg.get(0));
			registerAidHandler.offerConfirm(sc.nextLine());
		} catch (IncorrectCodException e) {
			System.err.println("The code that was inserted is not valid!");
		} catch (RegisterIsNotValidException e) {
			System.err.println("The number of family members is not valid!");
		} catch (AidIsNotValidException e) {
			System.err.println("There was an error creating the offer!");
		} catch (ThereIsNoRegionCatalogoException e) {
			System.err.println("There is no Regions!");
		} catch (RegionInsertedIsNotValid e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorSettingCod e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ErrorInsertingInCatalogException e) {
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
			List <AidDTO> aid = searchAidHandler.insertChoosenRegion(regList.get(0));

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
			searchAidHandler.requestsToBeNotified(new Region(reg.get(0)));
			System.err.println("There's no available aid at: " + reg.get(0).toString() + ", you will be notified when it does!");
		}

		//UC2
		try {

			searchAidHandler.startFamiltRegister(2);
			searchAidHandler.addHeadInfo("Joao", 939439495);

			for(int i = 0; i < familyMembersList.size(); i++)
				searchAidHandler.insertFamilyMemberRegister(familyMembersList.get(i));

			List<String> regList = searchAidHandler.requestListOfRegions();
			System.out.println(regList.toString());
			List <AidDTO> aj = searchAidHandler.insertChoosenRegion(regList.get(0));

			System.out.print("\n > Lista de Ajudas < \n");

			aj.forEach( a -> {
				System.out.println(a.getInfo());
			});

			searchAidHandler.choosenAid(aj.get(0));
			searchAidHandler.registerConfirm();

		} catch(RegisterIsNotValidException e) {
			System.err.println("The register is not valid - Insert valid name!");
		} catch(InfoFamilyMemberException e) { 
			System.err.println("The number of family members which information you have inserted is not valid!");
		} catch (AidIsNonExistenceException e) {
			searchAidHandler.requestsToBeNotified(new Region(reg.get(0)));
			System.err.println("There's no available aid at: " + reg.get(0).toString() + ", you will be notified when it does!");
		}

		sc.close();
	}
}