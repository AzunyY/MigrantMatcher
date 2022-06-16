package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.DTO.AidDTO;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockMigrantsCatalog;
import tests.mocks.MockRegCatalog;

class SearchForAidHandlerTest {


	private List <String> reg;
	private SearchForAidHandler searchAidHandler;
	private List <String> familyMembersList;

	@BeforeEach
	protected void setUp() {

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
	protected void simpleTest() throws Exception {

		MockMigrantsCatalog mockMigsCat = new MockMigrantsCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();

		MockRegCatalog mockRegCatalog = new MockRegCatalog("defaults.properties", reg);
		searchAidHandler = new SearchForAidHandler("defaults.properties", mockAidCatalog, mockMigsCat, mockRegCatalog);

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.startPersonalRegister("Joao", 93792373));

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.requestListOfRegions());

		List<String> listReg = searchAidHandler.requestListOfRegions();
		Assertions.assertEquals(false, listReg.isEmpty());

		Assertions.assertDoesNotThrow(() ->
		searchAidHandler.insertChoosenRegion("defaults.properties", listReg.get(0)));
		List <AidDTO> aidList = searchAidHandler.insertChoosenRegion("defaults.properties", listReg.get(0));
		Assertions.assertEquals(false, aidList.isEmpty());

		Assertions.assertEquals(aidList, mockAidCatalog.filterByReg(listReg.get(0)));
		Assertions.assertDoesNotThrow(() -> searchAidHandler.choosenAid(aidList.get(0)));

		if(!aidList.get(0).getAvailability())
			Assertions.assertDoesNotThrow(() -> searchAidHandler.requestsToBeNotified(listReg.get(0)));
		else
			Assertions.assertDoesNotThrow(() -> searchAidHandler.registerConfirm());

		Assertions.assertEquals(false, aidList.get(0).getAvailability());		

	}
}

