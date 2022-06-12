package pt.migrantmatcher.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.facade.MigrantMatcherSistema;
import pt.migrantmatcher.facade.handlers.ProcuraAjudaHandler;
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
			regAjHandler.ofereceApartamento(3);
			regAjHandler.indicaRegiao(new Regiao(reg.get(0)));
			regAjHandler.confirmaOferta("ABBBA");
		} catch (ajudaNaoEhValida e) {
			System.out.println("O tipo de ajuda inserido nao eh valido !");
		}	

		try {
			regAjHandler.iniciaRegistoAjuda(937977372);
			regAjHandler.ofereceApartamento(3)
			regAjHandler.indicaRegiao(new Regiao("Porto"));
			regAjHandler.confirmaOferta("OOE3");
		} catch (ajudaNaoEhValida e) {
			System.out.println("O tipo de ajuda inserido nao eh valido !");
		}	

		//UC2
		ProcuraAjudaHandler procAjHandler = migMatch.procurarAjuda();

		try {
			procAjHandler.iniciaRegistoPessoal("Joao", 939243944);

			procAjHandler.pedeListaRegioes();
			List <Ajuda> aj = procAjHandler.indicaRegiao(new Regiao(reg.get(0)));
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(registonNaoEhValido e) {
			System.out.println("O registo nao eh valido !");
		} catch (naoExisteAjuda e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0));
			System.out.println("Nao existe ajudas nessa regiao!");
		}

		try {

			procAjHandler.iniciaRegistoFamilia(3);
			procAjHandler.infoCabeça("Joao", 939439495);
			procAjHandler.indicaInfoFamiliar("Maria");
			procAjHandler.indicaInfoFamiliar("Luis");
			procAjHandler.indicaInfoFamiliar("Vanessa");

			procAjHandler.pedeListaRegioes();
			List <Ajuda> aj = procAjHandler.indicaRegiao(new Regiao(reg.get(0)));
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(registonNaoEhValido e) {
			System.out.println("O registo nao eh valido !");
		} catch(infoFamilarEmFalta e) { 
			System.out.printl("Existe informaçao em falta!");
		}catch (naoExisteAjuda e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0));
			System.out.println("Nao existe ajudas nessa regiao!");
		}
	}
}