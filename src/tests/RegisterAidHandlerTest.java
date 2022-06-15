package tests;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.AidIsNotValidException;
import pt.migrantmatcher.exceptions.IncorrectCodException;
import pt.migrantmatcher.exceptions.RegionInsertedIsNotValid;
import pt.migrantmatcher.exceptions.RegisterIsNotValidException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

class RegisterAidHandlerTest {

	private RegisterAidHandler testHandler;
	private List <String> reg;
	private Scanner sc ;


	@BeforeEach
	protected void setUp() throws Exception {

		reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");

		MigrantMatcherSystem migMatch = new MigrantMatcherSystem(reg);
		testHandler = migMatch.registerNewAid();
		sc = new Scanner(System.in);
	}

	@Test
	void testHousingOfferHandler() {

		Assertions.assertDoesNotThrow(() ->
		testHandler.aidRegisterStart(937977373));

		Assertions.assertDoesNotThrow(() ->
		testHandler.offerHousing(3));

		Assertions.assertDoesNotThrow(() ->
		testHandler.insertHousingRegion(reg.get(0)));

		String code = sc.nextLine();

		Assertions.assertDoesNotThrow(() ->
		testHandler.offerConfirm(code));

		sc.close();
	}


	@Test
	void testOfferItem() {

		Assertions.assertDoesNotThrow(() ->
		testHandler.aidRegisterStart(937977373));

		Assertions.assertDoesNotThrow(() ->
		testHandler.offerItem("Roupa"));

		String s = sc.nextLine();

		Assertions.assertDoesNotThrow(() ->
		testHandler.offerConfirm(s));

	}

	@Test
	void testOfferItem2() {

		Assertions.assertDoesNotThrow(() ->
		testHandler.aidRegisterStart(937977373));

		Assertions.assertDoesNotThrow(() ->
		testHandler.offerItem("Roupa"));

		Assertions.assertThrows(IncorrectCodException.class, () ->
		testHandler.offerConfirm(""));

	}
	
	@Test
	void testOfferItem3() {

		Assertions.assertDoesNotThrow(() ->
		testHandler.aidRegisterStart(937977373));
		
		Assertions.assertThrows(AidIsNotValidException.class, () ->
        testHandler.offerItem(""));
		
		Assertions.assertThrows(IncorrectCodException.class, () ->
		testHandler.offerConfirm(""));

	}
	
	void testHousingOfferHandler2() {

		Assertions.assertDoesNotThrow(() ->
        testHandler.aidRegisterStart(937977373));
	
        Assertions.assertThrows(RegisterIsNotValidException.class, () ->
        testHandler.offerHousing(0));

        Assertions.assertThrows(RegionInsertedIsNotValid.class, () ->
        testHandler.insertHousingRegion(""));

        Assertions.assertThrows(IncorrectCodException.class, () ->
        testHandler.offerConfirm(""));

	}
	
}
