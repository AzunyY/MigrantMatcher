package pt.migrantmatcher.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.facade.MigrantMatcherSistema;
import pt.migrantmatcher.facade.handlers.RegistaAjudaHandler;

public class MigrantMatcherExample {

	public static void main(String[] args){

		List <String> reg = new ArrayList <>();
		reg.add("Lisboa");
		reg.add("Porto");
		reg.add("Faro");
		reg.add("Cascais");

		MigrantMatcherSistema migMatch = new MigrantMatcherSistema(reg);

		// UC1
		RegistaAjudaHandler regAjHandler = migMatch.registarAjuda();

		try {
			regAjHandler.iniciaRegistoAjuda(937977373);
			regAjHandler.ofereceItem("Roupa");
			regAjHandler.confirmaOferta("ABBBA");
		} catch (ajudaNaoEhValida e) {
			System.out.println("O tipo de ajuda inserido nao eh valido !");
		}

		try {
			regAjHandler.iniciaRegistoAjuda(937977373);
			regAjHandler.ofereceItem("Roupa");
			regAjHandler.confirmaOferta("ABBBA");
		} catch (ajudaNaoEhValida e) {
			System.out.println("O tipo de ajuda inserido nao eh valido !");
		}	
	}
}