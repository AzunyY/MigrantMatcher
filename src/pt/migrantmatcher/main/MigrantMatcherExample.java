package pt.migrantmatcher.main;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.exceptions.AjudaNaoEhValida;
import pt.migrantmatcher.exceptions.AjudaNaoEstahDisponivel;
import pt.migrantmatcher.exceptions.CodErrado;
import pt.migrantmatcher.exceptions.InfoFamilarEmFalta;
import pt.migrantmatcher.exceptions.NaoExisteAjuda;
import pt.migrantmatcher.exceptions.RegistoNaoEhValido;
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

			/*ACHO NECESSARIO USAR DTO AQUI!!!*/
			regAjHandler.confirmaOferta("ABBBA");

		} catch (RegistoNaoEhValido e) {
			System.err.print("O numero de telemovel introduzido tem demasiado digitos!");
		} catch (AjudaNaoEhValida e) {
			System.err.print("A ajuda nao eh valida. Introduza uma descricao valida!");
		} catch (CodErrado e) {
			System.err.print("O codigo introduzido nao eh valido!");
		}

		try {
			regAjHandler.iniciaRegistoAjuda(937977373);
			regAjHandler.ofereceApartamento(3);
			regAjHandler.indicaRegiao(new Regiao(reg.get(0)));
			regAjHandler.confirmaOferta("ABBBA");
		} catch (RegistoNaoEhValido e) {
			System.err.print("O numero de telemovel introduzido tem demasiado digitos!");
		} catch (CodErrado e) {
			System.err.print("O codigo introduzido nao eh valido!");
		}	

		try {
			regAjHandler.iniciaRegistoAjuda(937977372);
			regAjHandler.ofereceApartamento(3);
			regAjHandler.indicaRegiao(new Regiao("Porto"));
			regAjHandler.confirmaOferta("OOE3");
		} catch (RegistoNaoEhValido e) {
			System.err.print("O numero de telemovel introduzido tem demasiado digitos!");
		} catch (CodErrado e) {
			System.err.print("O codigo introduzido nao eh valido!");
		} 

		//UC2
		ProcuraAjudaHandler procAjHandler = migMatch.procurarAjuda();

		try {
			procAjHandler.iniciaRegistoPessoal("Joao", 939243944);

			List <Regiao> regList = procAjHandler.pedeListaRegioes();
			List <Ajuda> aj = procAjHandler.indicaRegiao(regList.get(0));
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(RegistoNaoEhValido e) {
			System.err.println("O registo nao eh valido - Insira um nome e numero valido!");
		} catch (NaoExisteAjuda e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0)));
			System.err.println("Nao existe ajudas nessa regiao, vai ser notificado quando houver!");
		} catch (AjudaNaoEstahDisponivel e) {
			System.err.println("A ajuda escolhida nao estah disponivel!");
		} catch (InfoFamilarEmFalta e) {
			//do nothing
		}

		try {

			procAjHandler.iniciaRegistoFamilia(3);
			procAjHandler.infoCabeça("Joao", 939439495);
			procAjHandler.indicaInfoFamiliar("Maria");
			procAjHandler.indicaInfoFamiliar("Luis");
			procAjHandler.indicaInfoFamiliar("Vanessa");

			List <Regiao> regList = procAjHandler.pedeListaRegioes();
			List <Ajuda> aj = procAjHandler.indicaRegiao(regList.get(0));
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(RegistoNaoEhValido e) {
			System.err.println("O registo nao eh valido - Insira um nome e numero valido!");
		} catch(InfoFamilarEmFalta e) { 
			System.err.println("Existe informaçao em falta sobre os seus familiares!");
		} catch (NaoExisteAjuda e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0)));
			System.err.println("Nao existe ajudas nessa regiao!");
		} catch (AjudaNaoEstahDisponivel e) {
			System.err.println("A ajuda escolhida nao estah disponivel!");
		}

	}
}