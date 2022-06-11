package pt.migrantmatcher.facade.handlers;

import java.util.List;
import java.util.Random;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.CatalogoVoluntarios;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.domain.Voluntario;
import pt.migrantmatcher.plugins.PidgeonSMSSenderAdapter;

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
		enviaCodigo();
	}
	
	public void ofereceItem(String desc){
		this.catAj.novoItem(desc);
		enviaCodigo();
	}
	
	private void enviaCodigo() {
		
		String cod = generateCod();

		MigrantConfiguration codSender = MigrantConfiguration.getInstance();
		this.volCurr.setCod(cod);		
		codSender.getClass(codSender.getProperty("SENDERTYPE"), new PidgeonSMSSenderAdapter())
				 .enviaSMS(this.volCurr.getTel(), cod);
		
	}

	public String generateCod() {
		return new Random().ints(6,33,127)
						   .map( x -> (char) x)
						   .collect(StringBuilder::new, 
								    StringBuilder::appendCodePoint,
								    StringBuilder::append)
						   .toString();
	}
	
	public void confirmaOferta(String cod) {
		
		if(this.volCurr.checkValidCod(cod)) {
			catAj.addAj(ajCurr);
			volCurr.addAjuda(ajCurr);
		}	
	}

}
