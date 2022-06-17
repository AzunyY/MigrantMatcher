package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.AidIsNonExistenceException;
import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.DTO.AidDTO;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockMigrantsCatalog;
import tests.mocks.MockVolunteersCatalog;

/**
 * Testar a interacao entre o voluntario e o migrante
 * @author azuny
 */
class MigrantMatcherSystemTester {


	private List <String> reg = new ArrayList <>();
	private List <String> familyMembersList = new ArrayList<>();
	private MigrantMatcherSystem mgMatch;
	private SearchForAidHandler searchAidHandler;
	private RegisterAidHandler aidHandler;
	List <String> regList = new ArrayList<>();
	List<AidDTO> aidDtoList = new ArrayList<>();
	Scanner sc;

	@BeforeEach
	@Test
	void setUptest() {
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");

		familyMembersList.add("Maria");
		familyMembersList.add("Vanessa");
		familyMembersList.add("Paulo");
	}

	/**
	 * Testa uma sessao simples para oferta de alojamentos
	 */
	@Test
	void testeSystemSession(){

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockMigrantsCatalog mockMigCatalog = new MockMigrantsCatalog();

		try {
			mgMatch = new MigrantMatcherSystem(mockMigCatalog, mockAidCatalog,mockVolCat, reg);

			assertDoesNotThrow(() ->
			searchAidHandler = mgMatch.searchForAid());
			aidHandler = mgMatch.registerNewAid();

			assertDoesNotThrow(() -> aidHandler.aidRegisterStart(932143121));

			assertDoesNotThrow(() -> regList = aidHandler.offerHousing(3));

			assertEquals(false, regList.isEmpty());
			assertDoesNotThrow(() -> aidHandler.insertHousingRegion(regList.get(2))); 

			sc = new Scanner(System.in);
			System.out.println("Insert your confirmation code: ");
			assertDoesNotThrow(() -> aidHandler.offerConfirm(sc.nextLine()));

			assertDoesNotThrow(()->
			searchAidHandler.startFamiltRegister(4));

			assertDoesNotThrow(() -> 
			searchAidHandler.addHeadInfo("Joao", 932394293));


			for(String s : familyMembersList)
				assertDoesNotThrow(() -> {searchAidHandler.insertFamilyMemberRegister(s);
				});

			assertDoesNotThrow(() -> regList = searchAidHandler.requestListOfRegions());
			assertEquals(false, regList.isEmpty());

			assertDoesNotThrow( () ->
			aidDtoList = searchAidHandler.insertChoosenRegion(regList.get(2))
					);

			for(AidDTO aid: aidDtoList) {
				assertDoesNotThrow(() -> searchAidHandler.choosenAid(aid));
				assertDoesNotThrow(() -> searchAidHandler.registerConfirm(aid));
			}
			if (!aidDtoList.isEmpty()) {
				for(AidDTO aid: aidDtoList)
					assertEquals(false, aid.getAvailability());
			}

		} catch (ErrorCreatingRegionsException | PropertiesLoadingException e) {
			//Do nothing
		}

	}

	/**
	 * Testa uma sessao simples com oferta de item
	 */
	@Test
	void testeSystemSessionItem(){
		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockMigrantsCatalog mockMigrantsCatalog = new MockMigrantsCatalog();

		try {
			mgMatch = new MigrantMatcherSystem(mockMigrantsCatalog, mockAidCatalog, mockVolCat,reg);

			searchAidHandler = mgMatch.searchForAid();
			aidHandler = mgMatch.registerNewAid();

			assertDoesNotThrow(() -> aidHandler.aidRegisterStart(932143121));

			assertDoesNotThrow(() -> aidHandler.offerItem("Roupa"));

			sc = new Scanner(System.in);
			System.out.println("Insert your confirmation code: ");
			assertDoesNotThrow(() -> aidHandler.offerConfirm(sc.nextLine()));

			assertDoesNotThrow(()->
			searchAidHandler.startFamiltRegister(4));

			assertDoesNotThrow(() -> 
			searchAidHandler.addHeadInfo("Joao", 932394293));


			for(String s : familyMembersList)
				assertDoesNotThrow(() -> { searchAidHandler.insertFamilyMemberRegister(s);
				});

			assertDoesNotThrow(() -> regList = searchAidHandler.requestListOfRegions());
			assertEquals(false, regList.isEmpty());

			assertDoesNotThrow( () ->
			aidDtoList = searchAidHandler.insertChoosenRegion(regList.get(2))
					);

			for(AidDTO aid: aidDtoList) {
				assertDoesNotThrow(() -> searchAidHandler.choosenAid(aid));
				assertDoesNotThrow(() -> searchAidHandler.registerConfirm(aid));
			}
			if (!aidDtoList.isEmpty()) {
				for(AidDTO aid: aidDtoList)
					assertEquals(false, aid.getAvailability());
			}

		} catch (ErrorCreatingRegionsException | PropertiesLoadingException e) {
			//Do nothing
		}
	}

	/**
	 * Testa um sistema em que os migrantes se registam antes de haver ajudas disponiveis
	 */
	@Test 
	void systemTestMigrantFirst() {

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockMigrantsCatalog mockMigCatalog = new MockMigrantsCatalog();


		try {
			mgMatch = new MigrantMatcherSystem(mockMigCatalog, mockAidCatalog, mockVolCat, reg);

			searchAidHandler = mgMatch.searchForAid();
			aidHandler = mgMatch.registerNewAid();

			assertDoesNotThrow(()->
			searchAidHandler.startFamiltRegister(4));

			assertDoesNotThrow(() -> 
			searchAidHandler.addHeadInfo("Joao", 932394293));


			for(String s : familyMembersList)
				assertDoesNotThrow(() -> { searchAidHandler.insertFamilyMemberRegister(s);
				});

			assertDoesNotThrow(() -> regList = searchAidHandler.requestListOfRegions());
			assertEquals(false, regList.isEmpty());

			assertThrows(AidIsNonExistenceException.class, () ->
			aidDtoList = searchAidHandler.insertChoosenRegion(regList.get(2))
					);

			assertDoesNotThrow(() -> searchAidHandler.requestsToBeNotified(regList.get(2)));

			assertDoesNotThrow(() -> aidHandler.aidRegisterStart(932143121));

			assertDoesNotThrow(() -> regList = aidHandler.offerHousing(3));

			assertEquals(false, regList.isEmpty());
			assertDoesNotThrow(() -> aidHandler.insertHousingRegion(regList.get(2))); 

			sc = new Scanner(System.in);
			System.out.println("Insert your confirmation code: ");
			assertDoesNotThrow(() -> aidHandler.offerConfirm(sc.nextLine()));

		} catch (ErrorCreatingRegionsException | PropertiesLoadingException e) {
			//Do nothing
		}
	}


	/**
	 * Mesmo que o de cima mas para item
	 */
	@Test 
	void systemTestMigrantFirstWithItem() {
		MockMigrantsCatalog mockMigsCat = new MockMigrantsCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockVolunteersCatalog mockVolCatalog = new MockVolunteersCatalog();

		try {

			mgMatch = new MigrantMatcherSystem(mockMigsCat, mockAidCatalog, mockVolCatalog, reg);

			searchAidHandler = mgMatch.searchForAid();
			aidHandler = mgMatch.registerNewAid();

			assertDoesNotThrow(() -> 
			searchAidHandler.startPersonalRegister("Joao", 932394293));

			assertDoesNotThrow(() -> regList = searchAidHandler.requestListOfRegions());
			assertEquals(false, regList.isEmpty());

			assertThrows(AidIsNonExistenceException.class, () ->
			aidDtoList = searchAidHandler.insertChoosenRegion(regList.get(2))
					);

			assertDoesNotThrow(() -> searchAidHandler.requestsToBeNotified(regList.get(2)));

			assertDoesNotThrow(()-> aidHandler.aidRegisterStart(93233231));
			assertDoesNotThrow(() -> aidHandler.offerItem("Roupa"));

			sc = new Scanner(System.in);
			System.out.println("Insert your confirmation code: ");
			assertDoesNotThrow(() -> aidHandler.offerConfirm(sc.nextLine()));

		} catch (ErrorCreatingRegionsException | PropertiesLoadingException e) {
			//Do nothing
		}
	}

}
