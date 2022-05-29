package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.CatalogoMigrantes;
import pt.migrantmatcher.domain.Regiao;

public class ProcuraAjudaHandler {
	
	private CatalogoMigrantes catMigrantes;
	
	public ProcuraAjudaHandler() {
		catMigrantes = new CatalogoMigrantes();
	}
	
	public void iniciaRegistoPessoas(String nome, int tel) {
		
	}
	
	public void iniciaRegistoFamilia(int nPessoas) {
		
	}
	
	public void infoCabeça(String nome, int tel) {
		
	}
	
	public void indicaInfoFamiliar(String nome) {
		
	}
	
	public List <Regiao> pedeListaRegioes(){
		
	}
	
	public List <Ajuda> indicaRegiao() {
		
	}
	
	public void escolheAjuda() {
		
	}
	
	public void confirmaRegisto() {
		
	}
	
	/*Possivel que seja com observavel*/
	public void pedeNotifAjuda(Regiao reg) {
		
	}
}
