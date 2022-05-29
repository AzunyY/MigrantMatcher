package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoMigrantes;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.Migrantes;
import pt.migrantmatcher.domain.Regiao;

public class ProcuraAjudaHandler {
	
	private CatalogoMigrantes catMigrantes;
	private Migrantes migCurr;
	private CatalogoRegioes catReg;
	private CatalogoAjudas catAj;
	private Ajuda ajCurr;
	
	public ProcuraAjudaHandler(CatalogoMigrantes catmig, CatalogoRegioes catReg, CatalogoAjudas catAj ) {
		/*ISTO TEM DE MUDAR MAS FAÇO DEPOIS*/
		this.catMigrantes = catmig;
		this.catReg = catReg;
		this.catAj = catAj;
	}
	
	public void iniciaRegistoPessoal(String nome, int tel) {
		this.migCurr = this.catMigrantes.criaMigranteIndividual(nome, tel);
	}
	
	public void iniciaRegistoFamilia(int nPessoas) {
		this.migCurr = this.catMigrantes.criaFamiliaMigrante(nPessoas);
	}
	
	public void infoCabeça(String nome, int tel) {
		this.catMigrantes.addInfoCabeca(migCurr, nome, tel);
	}
	
	public void indicaInfoFamiliar(String nome) {
		this.catMigrantes.addInfoNomes(migCurr, nome);
	}
	
	public List <Regiao> pedeListaRegioes(){
		return this.catReg.getRegioes();
	}
	
	public List <Ajuda> indicaRegiao(Regiao reg) {
		return this.catAj.filterByReg(reg);
	}
	
	public void escolheAjuda(Ajuda aj) {
		ajCurr = this.catAj.getAjuda(aj);
	}
	
	public void confirmaRegisto() {
		if (ajCurr.isLivre()) {
			this.catMigrantes.addAjuda(migCurr, ajCurr);
			ajCurr.setNotLivre(migCurr);
		}
	}
	
	/*Possivel que seja com observavel e synchronized*/
	public void pedeNotifAjuda(Regiao reg) {
		
		this.catReg.notificaMig(migCurr, reg);
		
	}
}
