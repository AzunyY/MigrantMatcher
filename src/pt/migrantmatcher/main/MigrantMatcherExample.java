package pt.migrantmatcher.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.exceptions.AjudaNaoEhValidaException;
import pt.migrantmatcher.exceptions.CodErradoException;
import pt.migrantmatcher.exceptions.InfoFamilarException;
import pt.migrantmatcher.exceptions.NaoExisteAjudaException;
import pt.migrantmatcher.exceptions.RegistoNaoEhValidoException;
import pt.migrantmatcher.facade.MigrantMatcherSistema;
import pt.migrantmatcher.facade.DTO.AjudaDTO;
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
		
		RegistaAjudaHandler regAjHandler = migMatch.registarAjuda();
		Scanner sc = new Scanner(System.in);
		
		ProcuraAjudaHandler procAjHandler = migMatch.procurarAjuda();
		List <String> listFamiliares = new ArrayList<>();
		listFamiliares.add("Maria");
		listFamiliares.add("Vanessa");
		listFamiliares.add("Paulo");
		

		// UC1
		try {
			regAjHandler.iniciaRegistoAjuda(937977373);
			regAjHandler.ofereceItem("Roupa");
			System.out.println("Insira o codigo recebido: ");
			regAjHandler.confirmaOferta(sc.nextLine().toString());

		} catch (RegistoNaoEhValidoException e) {
			System.err.println("O numero de telemovIZ/'l3el introduzido tem demasiado digitos!");
		} catch (AjudaNaoEhValidaException e) {
			System.err.println("A ajuda nao eh valida. Introduza uma descricao valida!");
		} catch (CodErradoException e) {
			System.err.println("O codigo introduzido nao eh valido!");
		}


		//UC2
		try {
			procAjHandler.iniciaRegistoPessoal("Joao", 939243944);
			List<String> regList = procAjHandler.pedeListaRegioes();
			System.out.println(regList.toString());
			List <AjudaDTO> aj = procAjHandler.indicaRegiao(regList.get(0));
			
			System.out.print("\n > Lista de Ajudas < \n");
			aj.forEach( a -> {
				System.out.println(a.getInfo());
			});
			
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(RegistoNaoEhValidoException e) {
			System.err.println("O registo nao eh valido - Insira um nome e numero valido!");
		} catch (NaoExisteAjudaException e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0)));
			System.err.println("Nao existe ajudas na regiao: " + reg.get(0).toString() + ", vai ser notificado quando houver!");
		} catch (InfoFamilarException e) {
			System.err.println("Existe informaçao em falta ou a mais sobre os seus familiares!");
		}
		
		//UC1
		try {
			regAjHandler.iniciaRegistoAjuda(937977373);
			regAjHandler.ofereceApartamento(3);
			regAjHandler.indicaRegiao(new Regiao(reg.get(0)));
			regAjHandler.confirmaOferta(sc.nextLine());
		} catch (RegistoNaoEhValidoException e) {
			System.err.println("O numero de telemovel introduzido tem demasiado digitos!");
		} catch (CodErradoException e) {
			System.err.println("O codigo introduzido nao eh valido!");
		}	

		//UC1
		try {
			regAjHandler.iniciaRegistoAjuda(937977372);
			regAjHandler.ofereceApartamento(3);
			regAjHandler.indicaRegiao(new Regiao("Porto"));
			regAjHandler.confirmaOferta(sc.nextLine());
		} catch (RegistoNaoEhValidoException e) {
			System.err.println("O numero de telemovel introduzido tem demasiado digitos!");
		} catch (CodErradoException e) {
			System.err.println("O codigo introduzido nao eh valido!");
		} 

		//UC2
		try {

			procAjHandler.iniciaRegistoFamilia(3);
			procAjHandler.infoCabeça("Joao", 939439495);

			for(int i = 0; i < listFamiliares.size(); i++)
				procAjHandler.indicaInfoFamiliar(listFamiliares.get(i));

			List<String> regList = procAjHandler.pedeListaRegioes();
			System.out.println(regList.toString());
			List <AjudaDTO> aj = procAjHandler.indicaRegiao(regList.get(0));
			
			System.out.print("\n > Lista de Ajudas < \n");
			aj.forEach( a -> {
				System.out.println(a.getInfo());
			});
			
			System.out.println(aj.toString());
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(RegistoNaoEhValidoException e) {
			System.err.println("O registo nao eh valido - Insira um nome e numero valido!");
		} catch(InfoFamilarException e) { 
			System.err.println("Existe informaçao em falta ou a mais sobre os seus familiares!");
		} catch (NaoExisteAjudaException e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0)));
			System.err.println("Nao existe ajudas na regiao" + reg.get(0).toString() + " , vai ser notificado quando houver!");
		}

		//UC2
		try {

			procAjHandler.iniciaRegistoFamilia(2);
			procAjHandler.infoCabeça("Joao", 939439495);

			for(int i = 0; i < listFamiliares.size(); i++)
				procAjHandler.indicaInfoFamiliar(listFamiliares.get(i));

			List<String> regList = procAjHandler.pedeListaRegioes();
			System.out.println(regList.toString());
			List <AjudaDTO> aj = procAjHandler.indicaRegiao(regList.get(0));
			
			System.out.print("\n > Lista de Ajudas < \n");
			
			aj.forEach( a -> {
				System.out.println(a.getInfo());
			});
			
			procAjHandler.escolheAjuda(aj.get(0));
			procAjHandler.confirmaRegisto();

		} catch(RegistoNaoEhValidoException e) {
			System.err.println("O registo nao eh valido - Insira um nome e numero valido!");
		} catch(InfoFamilarException e) { 
			System.err.println("Existe informaçao em falta ou a mais sobre os seus familiares!");
		} catch (NaoExisteAjudaException e) {
			procAjHandler.pedeNotif(new Regiao(reg.get(0)));
			System.err.println("Nao existe ajudas na regiao" + reg.get(0).toString() + " , vai ser notificado quando houver!");
		}

		sc.close();
	}
}