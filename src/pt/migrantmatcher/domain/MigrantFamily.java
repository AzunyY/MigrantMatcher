package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class MigrantFamily extends Migrant {
	
	private int nPersons;
	private List <String> familyMembersList;
	
	protected MigrantFamily(int nPersons) {
		super();
		this.nPersons = nPersons;
		this.familyMembersList = new ArrayList<>(); 
	}
	
	public void addInfoHead(String name, int tel) {
		setName(name);
		setTel(tel);
	}
	
	public void addFamilyMembersName(String name) {
		this.familyMembersList.add(name);
	}

	public int getnPessoas() {
		return nPersons;
	}
	
	public boolean numberOfFamilyMembersIsValid() {
		return this.familyMembersList.size() + 1 == this.nPersons;
	}	
}


