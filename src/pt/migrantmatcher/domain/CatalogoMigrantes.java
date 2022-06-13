package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class CatalogoMigrantes {
	
	private List <Migrantes> listMigrantes;

	public CatalogoMigrantes() {
		listMigrantes = new ArrayList <>();
	}
	
	public Individual criaMigranteIndividual(String nome, int tel) {
		return new Individual(nome, tel); //1.1
	}
	
	public Familia criaFamiliaMigrante(int nPessoas) {
		return new Familia(nPessoas); //1,1
	}
	
	public void addInfoCabeca(Migrantes curr, String nome, int tel) {
		((Familia) curr).addInfoCabeca(nome, tel); //1.1
	}
	
	public void addInfoNomes(Migrantes curr, String nome) {
		((Familia) curr).addNomeFamilar(nome); //1.1
	}

	public void addAjuda(Migrantes migCurr, Ajuda ajCurr) {
		migCurr.addAjuda(ajCurr);
	}

	public List <Migrantes> getListMigrantes() {
		return listMigrantes;
	}
}
