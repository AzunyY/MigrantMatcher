package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.CatalogoVoluntarios;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.domain.Voluntario;

public class RegistaAjudaHandler {
	
	private CatalogoVoluntarios catVol;
	private CatalogoRegioes catReg;
	private CatalogoAjudas catAj;
	private Voluntario volCurr;
	private Ajuda ajCurr;
	
	public RegistaAjudaHandler(CatalogoAjudas catAj, CatalogoVoluntarios catVol, CatalogoRegioes catReg) {
		this.catVol = catVol;
		this.catReg = catReg;
		this.catAj = catAj;
	}
	
	public void iniciaRegistoAjuda(int tel) {
		this.volCurr = this.catVol.setVol(tel);
	}
	
	public List <Regiao> ofereceApartamento(int nPessoas){
		this.ajCurr = this.catAj.criarAloj(nPessoas);
		return this.catReg.getRegioes();
	}
	
	public void indicaRegiao(Regiao reg) {
		this.catAj.insereReg(ajCurr, reg);
		this.volCurr.enviaCodigo();
	}
	
	public void ofereceItem(String desc){
		this.catAj.novoItem(desc);
		this.volCurr.enviaCodigo();
	}
	
	public void confirmaOferta(String cod) {
		this.volCurr.checkValidCod(cod);
	}

}
