package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;
import pt.migrantmatcher.facade.handlers.SearchForAidHandler;
import tests.mocks.ScannerMock;

class MigrantMatcherSystemaTest {
	
	private List <String> reg = new ArrayList <>();
	private List <String> familyMembersList = new ArrayList<>();
	ScannerMock sc;


	@BeforeEach
	void setUptest() {
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");
		
		familyMembersList.add("Maria");
		familyMembersList.add("Vanessa");
		familyMembersList.add("Paulo");
	}
	
	void testSystemCreation() {
		
		try {
			MigrantMatcherSystem migMatch = new MigrantMatcherSystem("defaults3.properties", reg);
			registerAidHandler = migMatch.registerNewAid();
			searchAidHandler = migMatch.searchForAid();
		} catch (ErrorCreatingRegionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoFileNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
