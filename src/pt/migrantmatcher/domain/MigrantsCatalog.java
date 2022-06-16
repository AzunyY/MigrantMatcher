package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class MigrantsCatalog {
	
	private List <Migrant> migrantsList;

	protected MigrantsCatalog() {
		migrantsList = new ArrayList <>();
	}
	
	protected IndividualMigrant createIndividualMigrant(String name, int tel) {
		return new IndividualMigrant(name, tel); //1.1
	}
	
	protected MigrantFamily createMigrantFamily(int nPersons) {
		return new MigrantFamily(nPersons); //1,1
	}
	
	protected void addInfoHead(Migrant currMig, String name, int tel) {
		((MigrantFamily) currMig).addInfoHead(name, tel); //1.1
	}
	
	protected void addInfoNames(Migrant currMig, String name) {
		((MigrantFamily) currMig).addFamilyMembersName(name); //1.1
	}
	
	protected List <Migrant> getListMigrant() {
		return migrantsList;
	}
}
