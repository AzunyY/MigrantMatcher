package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.AidIsNonExistenceException;
import pt.migrantmatcher.exceptions.InfoFamilyMemberException;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockMigrantsCatalog;
/**
 * Testa o handler de procura ajuda
 * @author azuny
 *
 */
class SearchForAidHandlerTest {


	private List <String> reg;
	private SearchForAidHandler searchAidHandler;
	private List <String> familyMembersList;
	private MigrantMatcherSystem mgMatch;

	@BeforeEach
	void setUp() {

		reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");


		familyMembersList = new ArrayList<>();
		familyMembersList.add("Maria");
		familyMembersList.add("Vanessa");
		familyMembersList.add("Paulo");

	}

	@Test 
	void simpleTestRegisterIndividual() throws Exception {

		MockMigrantsCatalog mockMigsCat = new MockMigrantsCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();

		mgMatch = new MigrantMatcherSystem(mockMigsCat, mockAidCatalog, reg);
		searchAidHandler = mgMatch.searchForAid();

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.startPersonalRegister("Joao", 93792373));

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.requestListOfRegions());

		List<String> listReg = searchAidHandler.requestListOfRegions();
		Assertions.assertEquals(false, listReg.isEmpty());

		Assertions.assertThrows(AidIsNonExistenceException.class, () ->
		searchAidHandler.insertChoosenRegion(listReg.get(0)));

		Assertions.assertDoesNotThrow(() -> searchAidHandler.requestsToBeNotified(listReg.get(0)));

	}


	@Test 
	void simpleTestRegisterFamiliar() throws Exception {

		MockMigrantsCatalog mockMigsCat = new MockMigrantsCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		
		mgMatch = new MigrantMatcherSystem(mockMigsCat, mockAidCatalog, reg);
		searchAidHandler = mgMatch.searchForAid();

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.startFamiltRegister(4));

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.addHeadInfo("Joao", 93792373));

		Assertions.assertDoesNotThrow(() -> {
			for(String s : familyMembersList)
				searchAidHandler.insertFamilyMemberRegister(s);
		});

		List<String> listReg = searchAidHandler.requestListOfRegions();
		Assertions.assertEquals(false, listReg.isEmpty());

		Assertions.assertThrows(AidIsNonExistenceException.class, () ->
		searchAidHandler.insertChoosenRegion(listReg.get(0)));

		Assertions.assertDoesNotThrow(() -> searchAidHandler.requestsToBeNotified(listReg.get(0)));

	}

	@Test 
	void registerFamiliarWithLessFamilyMembers() throws Exception {

		MockMigrantsCatalog mockMigsCat = new MockMigrantsCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();

		mgMatch = new MigrantMatcherSystem(mockMigsCat, mockAidCatalog, reg);
		searchAidHandler = mgMatch.searchForAid();
		
		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.startFamiltRegister(6));

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.addHeadInfo("Joao", 93792373));

		for(String s : familyMembersList)
			searchAidHandler.insertFamilyMemberRegister(s);

		Assertions.assertThrows(InfoFamilyMemberException.class, () -> {
			searchAidHandler.requestListOfRegions();
		});
	}

	void registerFamiliarWithMoreFamilyMembers() throws Exception {

		MockMigrantsCatalog mockMigsCat = new MockMigrantsCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();

		mgMatch = new MigrantMatcherSystem(mockMigsCat, mockAidCatalog, reg);
		searchAidHandler = mgMatch.searchForAid();
		
		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.startFamiltRegister(2));

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.addHeadInfo("Joao", 93792373));

		for(String s : familyMembersList)
			searchAidHandler.insertFamilyMemberRegister(s);

		Assertions.assertThrows(InfoFamilyMemberException.class, () -> {
			searchAidHandler.requestListOfRegions();
		});
	}

	void registerLackOfInfoTeste() throws Exception {

		MockMigrantsCatalog mockMigsCat = new MockMigrantsCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		
		mgMatch = new MigrantMatcherSystem(mockMigsCat, mockAidCatalog, reg);
		searchAidHandler = mgMatch.searchForAid();
		
		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.startFamiltRegister(2));

		Assertions.assertThrows(RegisterIsNotValidException.class, () ->
		searchAidHandler.addHeadInfo("", 93792373));
	}
}

