package pt.migrantmatcher.main;

import pt.migrantmatcher.facade.MigrantMatcherSistema;

public class MigrantMatcherExample {

	public static void main(String[] args){
		
		
		MigrantMatcherSistema migMatch = new MigrantMatcherSistema("configuration.properties");
		
		migMatch.registarAjuda();
		
	}
}