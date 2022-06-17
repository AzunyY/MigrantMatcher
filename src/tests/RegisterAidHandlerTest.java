package tests;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.IncorrectCodException;
import pt.migrantmatcher.exceptions.RegionInsertedIsNotValid;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;
import tests.mocks.MockAidsCatalog;
import tests.mocks.MockVolunteersCatalog;

/**
 * Testa o handler de regista ajuda
 * @author
 *
 */
class RegisterAidHandlerTest {

	private List <String> reg  = new ArrayList <>();
	private List <Integer> telList = new ArrayList <>();	
	private RegisterAidHandler aidHandler;
	private Scanner sc;
	MigrantMatcherSystem mgm;

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

		MigrantMatcherSystem mgm = new MigrantMatcherSystem(mockVolCat, mockAidCatalog, reg);
		aidHandler = mgm.registerNewAid();		

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
		
		sc = new Scanner(System.in);
		System.out.println("Insert your confirmation code: ");

		try {
			aidHandler.offerConfirm(sc.nextLine());
		} catch(IncorrectCodException e){
			System.err.println("The confirmation failed! The code was incorrect!");
		}

	}

	@Test
	void numberFamilyMemNotValidTest() throws Exception {

		MockVolunteersCatalog mockVolCat = new MockVolunteersCatalog();
		MockAidsCatalog mockAidCatalog = new MockAidsCatalog();
		MigrantMatcherSystem mgm = new MigrantMatcherSystem(mockVolCat, mockAidCatalog, reg);
		aidHandler = mgm.registerNewAid();

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
		MigrantMatcherSystem mgm = new MigrantMatcherSystem(mockVolCat, mockAidCatalog, reg);

		aidHandler = mgm.registerNewAid();		Assertions.assertDoesNotThrow(() ->
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
		MigrantMatcherSystem mgm = new MigrantMatcherSystem(mockVolCat, mockAidCatalog, reg);

		aidHandler = mgm.registerNewAid();

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

		mgm = new MigrantMatcherSystem(mockVolCat, mockAidCatalog, reg);
		aidHandler = mgm.registerNewAid();
		
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
		
		sc = new Scanner(System.in);
		System.out.println("Insert your confirmation code: ");
		String code = sc.nextLine();

		try {
			aidHandler.offerConfirm(code);
		} catch(IncorrectCodException e){
			System.err.println("The confirmation failed! The code was incorrect!");
		}

		Assertions.assertEquals(true, mockAidCatalog.isNotifyingCorrectly());
	}
}
