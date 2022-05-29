import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoMigrantes;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.CatalogoVoluntarios;
import pt.migrantmatcher.facade.handlers.ProcuraAjudaHandler;
import pt.migrantmatcher.facade.handlers.RegistaAjudaHandler;

public class MigrantMatcherSistema {

	private CatalogoRegioes catReg;
	private CatalogoMigrantes catMig;
	private CatalogoAjudas catAj;
	private CatalogoVoluntarios catVol;

	public MigrantMatcherSistema(CatalogoRegioes catReg) {
		//deveria ver se catReg e null ou nao!!
		this.catReg = catReg;
		this.catMig = new CatalogoMigrantes();
		this.catAj = new CatalogoAjudas();
		this.catVol = new CatalogoVoluntarios();
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
