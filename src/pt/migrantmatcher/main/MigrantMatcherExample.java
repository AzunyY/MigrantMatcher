package pt.migrantmatcher.main;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.exceptions.AjudaNaoEhValidaException;
import pt.migrantmatcher.exceptions.AjudaNaoEstahDisponivelException;
import pt.migrantmatcher.exceptions.CodErradoException;
import pt.migrantmatcher.exceptions.InfoFamilarEmFaltaException;
import pt.migrantmatcher.exceptions.NaoExisteAjudaException;
import pt.migrantmatcher.exceptions.RegistoNaoEhValidoException;
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

		} catch (RegistoNaoEhValidoException e) {
			System.err.print("O numero de telemovel introduzido tem demasiado digitos!");
		} catch (AjudaNaoEhValidaException e) {
			System.err.print("A ajuda nao eh valida. Introduza uma descricao valida!");
		} catch (CodErradoException e) {
			System.err.print("O codigo introduzido nao eh valido!");
		}

		try {
			regAjHandler.iniciaRegistoAjuda(937977373);
			regAjHandler.ofereceApartamento(3);
			regAjHandler.indicaRegiao(new Regiao(reg.get(0)));
			regAjHandler.confirmaOferta("ABBBA");
		} catch (RegistoNaoEhValidoException e) {
			System.err.print("O numero de telemovel introduzido tem demasiado digitos!");
		} catch (CodErradoException e) {
			System.err.print("O codigo introduzido nao eh valido!");
		}	

		try {
			regAjHandler.iniciaRegistoAjuda(937977372);
			regAjHandler.ofereceApartamento(3);
			regAjHandler.indicaRegiao(new Regiao("Porto"));
			regAjHandler.confirmaOferta("OOE3");
		} catch (RegistoNaoEhValidoException e) {
			System.err.print("O numero de telemovel introduzido tem demasiado digitos!");
		} catch (CodErradoException e) {
			System.err.print("O codigo introduzido nao eh valido!");
		} 

		//UC2
		ProcuraAjudaHandler procAjHandler = migMatch.procurarAjuda();

		try {
			procAjHandler.iniciaRegistoPessoal("Joao", 939243944);

			List<String> regList = procAjHandler.pedeListaRegioes();
			List <Ajuda> aj = procAjHandler.indicaRegiao(regList.get(0));
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(RegistoNaoEhValidoException e) {
			System.err.println("O registo nao eh valido - Insira um nome e numero valido!");
		} catch (NaoExisteAjudaException e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0)));
			System.err.println("Nao existe ajudas nessa regiao, vai ser notificado quando houver!");
		} catch (AjudaNaoEstahDisponivelException e) {
			System.err.println("A ajuda escolhida nao estah disponivel!");
		} catch (InfoFamilarEmFaltaException e) {
			//do nothing
		}

		try {

			procAjHandler.iniciaRegistoFamilia(3);
			procAjHandler.infoCabeça("Joao", 939439495);
			procAjHandler.indicaInfoFamiliar("Maria");
			procAjHandler.indicaInfoFamiliar("Luis");
			procAjHandler.indicaInfoFamiliar("Vanessa");

			List<String> regList = procAjHandler.pedeListaRegioes();
			List <Ajuda> aj = procAjHandler.indicaRegiao(regList.get(0));
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(RegistoNaoEhValidoException e) {
			System.err.println("O registo nao eh valido - Insira um nome e numero valido!");
		} catch(InfoFamilarEmFaltaException e) { 
			System.err.println("Existe informaçao em falta sobre os seus familiares!");
		} catch (NaoExisteAjudaException e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0)));
			System.err.println("Nao existe ajudas nessa regiao!");
		} catch (AjudaNaoEstahDisponivelException e) {
			System.err.println("A ajuda escolhida nao estah disponivel!");
		}

	}
}