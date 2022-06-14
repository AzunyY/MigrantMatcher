package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoMigrantes;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.Familia;
import pt.migrantmatcher.domain.Individual;
import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.domain.Migrantes;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.exceptions.InfoFamilarException;
import pt.migrantmatcher.exceptions.NaoExisteAjudaException;
import pt.migrantmatcher.exceptions.RegistoNaoEhValidoException;
import pt.migrantmatcher.plugins.PidgeonSMSSenderAdapter;
import pt.migrantmatcher.strategies.OrdenaPorTipoStrategy;

public class ProcuraAjudaHandler {

	private CatalogoMigrantes catMigrantes;
	private Migrantes migCurr;
	private CatalogoRegioes catReg;
	private CatalogoAjudas catAj;
	private Ajuda ajCurr;
	
	public ProcuraAjudaHandler(CatalogoMigrantes catmig, CatalogoRegioes catReg, CatalogoAjudas catAj ) {

		this.catMigrantes = catmig;
		this.catReg = catReg;
		this.catAj = catAj;

	}

	public void iniciaRegistoPessoal(String nome, int tel) throws RegistoNaoEhValidoException {

		int size = String.valueOf(tel).length();

		if(size != 9 || nome.isBlank())
			throw new RegistoNaoEhValidoException();

		this.migCurr = this.catMigrantes.criaMigranteIndividual(nome, tel); //1

	}

	public void iniciaRegistoFamilia(int nPessoas) {

		this.migCurr = this.catMigrantes.criaFamiliaMigrante(nPessoas); //1

	}

	public void infoCabeça(String nome, int tel) throws RegistoNaoEhValidoException {


		int size = String.valueOf(tel).length();

		if(size != 9 || nome.isBlank())
			throw new RegistoNaoEhValidoException();

		this.catMigrantes.addInfoCabeca(migCurr, nome, tel); //1

	}

	public void indicaInfoFamiliar(String nome) {

		this.catMigrantes.addInfoNomes(migCurr, nome);//1

	}

	public List <String> pedeListaRegioes() throws InfoFamilarException{

		if(migCurr instanceof Familia && !((Familia) migCurr).isValid() )
			throw new InfoFamilarException();

		return this.catReg.getRegioes(); //1
	}

	public List <Ajuda> indicaRegiao(String reg) throws NaoExisteAjudaException {

		List <Ajuda> ajList = this.catAj.filterByReg(reg); //1

		if(ajList.isEmpty())
			throw new NaoExisteAjudaException();

		MigrantConfiguration ordemAjudas = MigrantConfiguration.getInstance();
		return ordemAjudas.getClass("orderHelpType", new OrdenaPorTipoStrategy(ajList))
						  .ordena();

	}

	public void escolheAjuda(Ajuda aj) {

		this.ajCurr = this.catAj.getAjuda(aj); //1

	}

	public void confirmaRegisto() {
		
			this.catMigrantes.addAjuda(migCurr, ajCurr);
			enviaSMS("O migrante, " + ((Individual) migCurr).getNome() + " quer a ajuda: " + this.toString());
			ajCurr.setNotLivre();
	}

	private void enviaSMS(String message) {
		MigrantConfiguration smsSender = MigrantConfiguration.getInstance();
		smsSender.getClass("senderType", new PidgeonSMSSenderAdapter())
				 .enviaSMS(this.ajCurr.getVol(), message);
	}

	public void pedeNotif(Regiao regiao) {
		this.catAj.addObserver(this.migCurr, regiao.getName());
	}

}
