package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.ErrorCreatingCurAidException;
import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.DTO.AidDTO;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockMigrantsCatalog;
import tests.mocks.MockRegCatalog;
import tests.mocks.MockVolunteersCatalog;
import tests.mocks.ScannerMock;

class MigrantMatcherSystemTester {


	private List <String> reg = new ArrayList <>();
	private List <String> familyMembersList = new ArrayList<>();
	private MigrantMatcherSystem mgMatch;
	private SearchForAidHandler searchAidHandler;
	private RegisterAidHandler aidHandler;
	List <String> regList = new ArrayList<>();
	List<AidDTO> aidDtoList = new ArrayList<>();


	ScannerMock sc;

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

	@Test
	void testSystemCreationWithDef() {

		assertThrows(PropertiesLoadingException.class , () -> mgMatch = new MigrantMatcherSystem("", reg));
	}
	
	@Test 
	void testSystemCreationFailTest() {
		assertThrows(ErrorCreatingRegionsException.class, () -> {
			mgMatch = new MigrantMatcherSystem("", new ArrayList <>());
		});

	}
	
	@Test
	void testeSystemCreationWithDefAndNoValue(){
		assertThrows(PropertiesLoadingException.class, () -> {
			mgMatch = new MigrantMatcherSystem("defaults2.properties", reg);
		});
	}
	
	@Test
	void testeSystemSession(){

		MockRegCatalog mockRegCatalog;
		try {
			mockRegCatalog = new MockRegCatalog("defaults.properties", reg);

			assertThrows(PropertiesLoadingException.class, () -> {
				mgMatch = new MigrantMatcherSystem(mockRegCatalog);
			});
			
			searchAidHandler = mgMatch.searchForAid();
			aidHandler = mgMatch.registerNewAid();
			
			assertDoesNotThrow(()->
			searchAidHandler.startFamiltRegister(4));
			
			assertDoesNotThrow(() -> 
			searchAidHandler.addHeadInfo("Joao", 932394293));
			
			assertDoesNotThrow(() -> {
				for(String s : familyMembersList)
					searchAidHandler.insertFamilyMemberRegister(s);
				}
			);

			assertDoesNotThrow(() -> regList = searchAidHandler.requestListOfRegions());
			assertEquals(false, regList.isEmpty());
			
			assertDoesNotThrow( () ->
				 aidDtoList = searchAidHandler.insertChoosenRegion("defaults.properties", regList.get(2))
			);
			
			assertThrows(ErrorCreatingCurAidException.class, () -> searchAidHandler.requestsToBeNotified(regList.get(2)));
			
		} catch (ErrorCreatingRegionsException | PropertiesLoadingException e) {
			//Do nothing
		}
	}

}
