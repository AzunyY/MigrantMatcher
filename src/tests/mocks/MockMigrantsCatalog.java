package tests.mocks;

import java.util.List;

import pt.migrantmatcher.domain.IndividualMigrant;
import pt.migrantmatcher.domain.Migrant;
import pt.migrantmatcher.domain.MigrantFamily;
import pt.migrantmatcher.domain.MigrantsCatalog;

/**
 * Pure Fabrication - classe artificial catalogo de migrantes
 * @author Ana Luis FC53563
 */
public class MockMigrantsCatalog extends MigrantsCatalog{


	public MockMigrantsCatalog() {
		super();
	}

	public IndividualMigrant createIndividualMigrant(String name, int tel) {
		return super.createIndividualMigrant(name, tel); 
	}

	public MigrantFamily createMigrantFamily(int nPersons) {
		return super.createMigrantFamily(nPersons);
	}

	public void addInfoHead(Migrant currMig, String name, int tel) {
		super.addInfoHead(currMig, name, tel);
	}

	public void addInfoNames(Migrant currMig, String name) {
		super.addInfoNames(currMig, name);
	}

	public List <Migrant> getListMigrant() {
		return super.getListMigrant();
	}

}
