package pt.migrantmatcher.domain;

/**
* Esta classe e subclasse de Migrant
**/
public class IndividualMigrant extends Migrant{
	
	protected IndividualMigrant(String name, int tel) {
		super();
		setTel(tel);
		setName(name);
	}

}
