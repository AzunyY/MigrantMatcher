package pt.migrantmatcher.domain;

import java.util.List;

public class Regiao {
	
	private String name;
	private List <Migrantes> listMigToNotify;
	
	public Regiao(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void notifica(Migrantes migCurr) {
		listMigToNotify.add(migCurr);
	}
}
