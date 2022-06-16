package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe e subclasse de Migrant
 **/
public class MigrantFamily extends Migrant {

	private int nPersons;
	private List <String> familyMembersList;
	
	/**
	 * Cria uma famila de migrantes 
	 * @param nPersons - numero de familiares
	 * @ensures criar um migrante corrente
	 **/
	protected MigrantFamily(int nPersons) {
		super();
		this.nPersons = nPersons;
		this.familyMembersList = new ArrayList<>(); 
	}

	/**
	 * Adiciona a informaçao do cabeça da familia ao migrante corrente
	 * @param name - nome do cabeca
	 * @param tel - telemovel do cabeca
	 * @requires name!=null
	 **/
	public void addInfoHead(String name, int tel) {
		setName(name);
		setTel(tel);
	}

	/**
	 * Adiciona a informaçao da familia ao migrante corrente
	 * @param name - nome do familiar
	 * @requires name!=null
	 **/
	public void addFamilyMembersName(String name) {
		this.familyMembersList.add(name);
	}
	
	/**
	 * Verifica se o numero de familiares adicionados e valido	 
	 * @returns true se nPersons = lista + cabeça, false caso contrario
	 **/
	public boolean numberOfFamilyMembersIsValid() {
		return this.familyMembersList.size() + 1 == this.nPersons;
	}	
}


