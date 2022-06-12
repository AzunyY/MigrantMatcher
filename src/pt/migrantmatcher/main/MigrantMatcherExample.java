package pt.migrantmatcher.main;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.facade.MigrantMatcherSistema;

public class MigrantMatcherExample {

	public static void main(String[] args){
		
		List <String> reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");
		
		MigrantMatcherSistema migMatch = new MigrantMatcherSistema(reg);

	}
}