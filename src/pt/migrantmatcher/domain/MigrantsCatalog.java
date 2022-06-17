package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Vai ter a lista com os varios Migrantes
 * 
 * @author Ana Luis FC53563
 **/
public class MigrantsCatalog {
	
	private List <Migrant> migrantsList = new ArrayList <>();

	/**
	 * Cria instancia de um migrante corrente do tipo individual
	 * @param name - nome do migrante
	 * @param tel - telemovel do migrante
	 * @return migrante corrente
	 **/
	protected IndividualMigrant createIndividualMigrant(String name, int tel) {
		return new IndividualMigrant(name, tel); //1.1
	}
	
	
	/**
	 * Cria instancia de um migrante corrente do tipo familiaMigrantes
	 * @param nPersons - numero de familiares
	 * @return migrante corrente
	 **/
	protected MigrantFamily createMigrantFamily(int nPersons) {
		return new MigrantFamily(nPersons); //1,1
	}
	
	/**
	 * ver {@ MigrantFamily addInfoHead}
	 **/
	protected void addInfoHead(Migrant currMig, String name, int tel) {
		((MigrantFamily) currMig).addInfoHead(name, tel); //1.1
	}
	
	/**
	 * ver {@ MigrantFamily addFamilyMembersName}
	 **/
	protected void addInfoNames(Migrant currMig, String name) {
		((MigrantFamily) currMig).addFamilyMembersName(name); //1.1
	}
	
	/**
	 * Devolve a lista de migrantes
	 * @return lista de migrantes
	 */
	protected List <Migrant> getListMigrant() {
		return migrantsList;
	}
}
