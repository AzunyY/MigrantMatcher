package pt.migrantmatcher.domain;

/**
* Esta classe e subclasse de Migrant
* @author Ana Luis FC53563
**/
public class IndividualMigrant extends Migrant{
	
	protected IndividualMigrant(String name, int tel) {
		super();
		setTel(tel);
		setName(name);
	}

}
