package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.facade.MigrantMatcherSystem;
import pt.migrantmatcher.facade.handlers.RegisterAidHandler;

class MigrantSystemCreationTest {
	private List <String> reg;

	@Test
	protected void setUp() throws Exception {

		reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");

		Assertions.assertThrows(NoFileNameException.class, () -> {
			new MigrantMatcherSystem("", reg);
		});
	}

	@Test
	protected void setUpDefaultReg() throws Exception {

		reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");

		Assertions.assertDoesNotThrow(() -> {
			new MigrantMatcherSystem("defaults2.properties", reg);
		});
	}

	@Test
	protected void setUpNoReg() throws Exception {

		Assertions.assertThrows(ErrorCreatingRegionsException.class, () -> {
			new MigrantMatcherSystem("defaults2.properties", new ArrayList <>());
		});
	}

	@Test 
	protected void SenderTypeNotOnProperties() throws Exception {
		reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");
		
		MigrantMatcherSystem mgm = new MigrantMatcherSystem("defaults3.properties", reg);
		RegisterAidHandler registerHandler = mgm.registerNewAid();
		
		registerHandler.aidRegisterStart(937977373);
		registerHandler.offerHousing(3);
		
		Assertions.assertThrows(PropertiesLoadingException.class, () -> {
			registerHandler.insertHousingRegion(reg.get(0));
		});
	
	}
}
