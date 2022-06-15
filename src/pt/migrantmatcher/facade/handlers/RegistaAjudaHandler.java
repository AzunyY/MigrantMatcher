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
import pt.migrantmatcher.exceptions.AjudaNaoEhValidaException;
import pt.migrantmatcher.exceptions.CodErradoException;
import pt.migrantmatcher.exceptions.RegistoNaoEhValidoException;
import pt.migrantmatcher.plugins.PidgeonSMSSenderAdapter;
import pt.migrantmatcher.plugins.SenderType;

public class RegistaAjudaHandler {

	private CatalogoVoluntarios catVol;
	private CatalogoRegioes catReg;
	private CatalogoAjudas catAj;
	private Voluntario volCurr;
	private Ajuda ajCurr;

	private static RegistaAjudaHandler INSTANCE = null; // Lazy loading colocar a null

	protected RegistaAjudaHandler(CatalogoAjudas catAj, CatalogoVoluntarios catVol, CatalogoRegioes catReg) {
		
		this.catVol = catVol;
		this.catReg = catReg;
		this.catAj = catAj;
	
	}
	
	public static RegistaAjudaHandler getInstance(CatalogoAjudas catAj, CatalogoVoluntarios catVol, CatalogoRegioes catReg) {
	
		if (INSTANCE == null) {
			INSTANCE = new RegistaAjudaHandler(catAj, catVol, catReg);
		}

		return RegistaAjudaHandler.INSTANCE;
	
	}

	public void iniciaRegistoAjuda(int tel) throws RegistoNaoEhValidoException {

		int size = String.valueOf(tel).length();

		if(size != 9)
			throw new RegistoNaoEhValidoException();
		
		volCurr = this.catVol.getVol(tel); // 1
	
	}

	public List <String> ofereceApartamento(int nPessoas){
	
		this.ajCurr = this.catAj.criarAloj(nPessoas); //1
		return this.catReg.getRegioes(); //2
	
	}

	public void indicaRegiao(Regiao regiao) {
	
		this.catAj.insereReg(ajCurr, regiao); //1
		enviaCodigo();
	
	}

	public void ofereceItem(String desc) throws AjudaNaoEhValidaException{

		if(desc.isBlank())
			throw new AjudaNaoEhValidaException();

		this.ajCurr = this.catAj.novoItem(desc); //1
		enviaCodigo();
	
	}

	private void enviaCodigo() {
	
		String cod = generateCod();

		MigrantConfiguration codSender = MigrantConfiguration.getInstance();
		this.volCurr.setCod(cod);		
		SenderType sender = codSender.getClass("senderType", new PidgeonSMSSenderAdapter());
		sender.enviaSMS(this.volCurr.getTel(), "O seu codigo de confirmacao: " + cod);

	}

	private String generateCod() {
		
		return new Random().ints(6,48,123)
				.filter( x -> ((x < 58 || x > 64) && (x < 91 || x > 96) ))
				.map( x -> (char) x)
				.collect(StringBuilder::new, 
						StringBuilder::appendCodePoint,
						StringBuilder::append)
				.toString();
	
	}

	public void confirmaOferta(String cod) throws CodErradoException {
		
		if(this.volCurr.checkValidCod(cod)) {
			this.catVol.addAj(this.volCurr, this.ajCurr);
			this.catAj.addAj(this.ajCurr, this.volCurr, this.catReg.getRegioes()); //2
		} else
			throw new CodErradoException();
	
	}
}
