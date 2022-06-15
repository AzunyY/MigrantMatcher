package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class MigrantsCatalog {
	
	private List <Migrant> migrantsList;

	public MigrantsCatalog() {
		migrantsList = new ArrayList <>();
	}
	
	public IndividualMigrant createIndividualMigrant(String name, int tel) {
		return new IndividualMigrant(name, tel); //1.1
	}
	
	public MigrantFamily createMigrantFamily(int nPersons) {
		return new MigrantFamily(nPersons); //1,1
	}
	
	public void addInfoHead(Migrant currMig, String name, int tel) {
		((MigrantFamily) currMig).addInfoHead(name, tel); //1.1
	}
	
	public void addInfoNames(Migrant currMig, String name) {
		((MigrantFamily) currMig).addFamilyMembersName(name); //1.1
	}

	public void addAid(Migrant migCurr, Aid currAid) {
		migCurr.addAid(currAid);
	}

	public List <Migrant> getListMigrant() {
		return migrantsList;
	}
}
