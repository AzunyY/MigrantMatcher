package pt.migrantmatcher.facade;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoMigrantes;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.CatalogoVoluntarios;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.facade.handlers.ProcuraAjudaHandler;
import pt.migrantmatcher.facade.handlers.RegistaAjudaHandler;

public class MigrantMatcherSistema {
	
	private CatalogoRegioes catReg;
	private CatalogoMigrantes catMig;
	private CatalogoAjudas catAj;
	private CatalogoVoluntarios catVol;
	
	public MigrantMatcherSistema(List <String> reg) {
		this.catMig = new CatalogoMigrantes();
		this.catAj = new CatalogoAjudas();
		this.catVol = new CatalogoVoluntarios();
		
		MigrantConfiguration catReg = MigrantConfiguration.getInstance();
		List<String> listReg = catReg.getClass(catReg.getProperty("REGIOES"), reg);
		
		this.catReg = new CatalogoRegioes(listReg);
		
	}
	
	// UC1
	public RegistaAjudaHandler registarAjuda() {
		return new RegistaAjudaHandler(catAj, catVol, catReg);
	}

	// UC2
	public ProcuraAjudaHandler procurarAjuda() {
		return new ProcuraAjudaHandler(catMig, catReg, catAj);
	}	
}
