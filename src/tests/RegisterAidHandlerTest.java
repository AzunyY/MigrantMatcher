package tests;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.IncorrectCodException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.RegionInsertedIsNotValid;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockRegCatalog;
import tests.mocks.MockVolunteersCatalog;
import tests.mocks.ScannerMock;

class RegisterAidHandlerTest {

	private List <String> reg  = new ArrayList <>();
	private List <Integer> telList = new ArrayList <>();	
	private RegisterAidHandler aidHandler;

	@BeforeEach
	void setUp() {

		reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");
	}

	@Test 
	void simpleTest() throws Exception {

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockRegCatalog mockRegCatalog = new MockRegCatalog("defaults.properties", reg);
		ScannerMock sc = new ScannerMock(System.in);

		aidHandler = new RegisterAidHandler("defaults.properties", mockAidCatalog, mockVolCat, mockRegCatalog);

		Assertions.assertDoesNotThrow(() ->
		aidHandler.aidRegisterStart(937977373));

		if(!telList.contains(937977373))
			telList.add(937977373);
		else 
			Assertions.assertEquals(true, mockVolCat.volWasAdded(937977373));

		Assertions.assertDoesNotThrow(() ->
		aidHandler.offerHousing(3));

		Assertions.assertDoesNotThrow(() ->
		aidHandler.insertHousingRegion(reg.get(0)));

		String code = sc.ask();

		try {
			aidHandler.offerConfirm(code);
		} catch(IncorrectCodException e){
			System.err.println("The confirmation failed! The code was incorrect!");
		}

	}

	@Test
	void numberFamilyMemNotValidTest() throws Exception {

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockRegCatalog mockRegCatalog = new MockRegCatalog("", reg);
		aidHandler = new RegisterAidHandler("", mockAidCatalog, mockVolCat, mockRegCatalog);

		Assertions.assertDoesNotThrow(() ->
		aidHandler.aidRegisterStart(937977373));

		if(!telList.contains(937977373))
			telList.add(937977373);
		else 
			Assertions.assertEquals(true, mockVolCat.volWasAdded(937977373));

		Assertions.assertThrows(RegisterIsNotValidException.class, () ->
		aidHandler.offerHousing(0));
	}

	@Test
	void regionIsNotValidTest() throws Exception {

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockRegCatalog mockRegCatalog = new MockRegCatalog("defaults2.properties", reg);
		aidHandler = new RegisterAidHandler("defaults2.properties", mockAidCatalog, mockVolCat, mockRegCatalog);

		Assertions.assertDoesNotThrow(() ->
		aidHandler.aidRegisterStart(937977373));

		if(!telList.contains(937977373))
			telList.add(937977373);
		else 
			Assertions.assertEquals(true, mockVolCat.volWasAdded(937977373));

		Assertions.assertDoesNotThrow(() ->
		aidHandler.offerHousing(4));

		Assertions.assertThrows(RegionInsertedIsNotValid.class, () ->
		aidHandler.insertHousingRegion(""));

	}

	@Test
	void regionIsNotInTheListTest() throws Exception {

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockRegCatalog mockRegCatalog = new MockRegCatalog("defaults.properties", reg);
		aidHandler = new RegisterAidHandler("", mockAidCatalog, mockVolCat, mockRegCatalog);

		Assertions.assertDoesNotThrow(() ->
		aidHandler.aidRegisterStart(937977373));

		if(!telList.contains(937977373))
			telList.add(937977373);
		else 
			Assertions.assertEquals(true, mockVolCat.volWasAdded(937977373));

		Assertions.assertDoesNotThrow(() ->
		aidHandler.offerHousing(4));

		Assertions.assertThrows(RegionInsertedIsNotValid.class, () ->
		aidHandler.insertHousingRegion("Bigorne"));

	}

	@Test
	void propertiesWithoutNameTest() throws Exception {

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockRegCatalog mockRegCatalog = new MockRegCatalog("", reg);
		aidHandler = new RegisterAidHandler("", mockAidCatalog, mockVolCat, mockRegCatalog);
		ScannerMock sc = new ScannerMock(System.in);

		Assertions.assertDoesNotThrow(() ->
		aidHandler.aidRegisterStart(937977373));

		if(!telList.contains(937977373))
			telList.add(937977373);
		else 
			Assertions.assertEquals(true, mockVolCat.volWasAdded(937977373));

		Assertions.assertDoesNotThrow(() ->
		aidHandler.offerHousing(4));

		Assertions.assertThrows(NoFileNameException.class, () ->
		aidHandler.insertHousingRegion(reg.get(0)));

		String code = sc.ask();

		try {
			aidHandler.offerConfirm(code);
		} catch(IncorrectCodException e){
			System.err.println("The confirmation failed! The code was incorrect!");
		}

		Assertions.assertEquals(true, mockAidCatalog.isNotifyingCorrectly());

	}

	@Test
	void tryingDifferentFilePropertyTest() throws Exception {

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MockRegCatalog mockRegCatalog = new MockRegCatalog("defaults3.properties", reg);
		aidHandler = new RegisterAidHandler("defaults3.properties", mockAidCatalog, mockVolCat, mockRegCatalog);
		ScannerMock sc = new ScannerMock(System.in);

		Assertions.assertDoesNotThrow(() ->
		aidHandler.aidRegisterStart(937977373));

		if(!telList.contains(937977373))
			telList.add(937977373);
		else 
			Assertions.assertEquals(true, mockVolCat.volWasAdded(937977373));

		Assertions.assertDoesNotThrow(() ->
		aidHandler.offerHousing(4));

		Assertions.assertDoesNotThrow(() ->
		aidHandler.insertHousingRegion(reg.get(0)));

		String code = sc.ask();

		try {
			aidHandler.offerConfirm(code);
		} catch(IncorrectCodException e){
			System.err.println("The confirmation failed! The code was incorrect!");
		}

		Assertions.assertEquals(true, mockAidCatalog.isNotifyingCorrectly());
	}
}
