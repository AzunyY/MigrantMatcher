package pt.migrantmatcher.facade.handlers;

import java.util.List;
import java.util.Random;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.Alojamento;
import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.CatalogoVoluntarios;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.domain.Voluntario;
import pt.migrantmatcher.exceptions.AjudaNaoEhValidaException;
import pt.migrantmatcher.exceptions.CodErradoException;
import pt.migrantmatcher.exceptions.RegistoNaoEhValidoException;
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

	public void iniciaRegistoAjuda(int tel) throws RegistoNaoEhValidoException {

		int size = String.valueOf(tel).length();

		if(size != 9)
			throw new RegistoNaoEhValidoException();

		this.volCurr = this.catVol.setVol(tel);
	}

	public List <Regiao> ofereceApartamento(int nPessoas){
		this.ajCurr = this.catAj.criarAloj(nPessoas);
		return this.catReg.getRegioes();
	}

	public void indicaRegiao(Regiao regiao) {
		this.catAj.insereReg(ajCurr, regiao);
		enviaCodigo();
	}

	public void ofereceItem(String desc) throws AjudaNaoEhValidaException{

		if(desc.isBlank())
			throw new AjudaNaoEhValidaException();

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

	public void confirmaOferta(String cod) throws CodErradoException {

		if(this.volCurr.checkValidCod(cod)) {
			catAj.addAj(this.ajCurr);
			volCurr.addAjuda(this.ajCurr, this.catReg);
		} else
			throw new CodErradoException();

		if(ajCurr instanceof Alojamento)
			volCurr.addObserver(((Alojamento) ajCurr).getRegiao());
		else {
			List <Regiao> regList = this.catReg.getRegioes();
			for(int i = 0; i < regList.size(); i++)
				volCurr.addObserver(regList.get(i));
		}

	}
}
