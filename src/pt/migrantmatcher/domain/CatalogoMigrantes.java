package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class CatalogoMigrantes {
	
	private List <Migrantes> listMigrantes;

	public CatalogoMigrantes() {
		listMigrantes = new ArrayList <>();
	}
	
	public Individual criaMigranteIndividual(String nome, int tel) {
		return new Individual(nome, tel);
	}
	
	public Familia criaFamiliaMigrante(int nPessoas) {
		return new Familia(nPessoas);
	}
	
	public void addInfoCabeca(Migrantes curr, String nome, int tel) {
		((Familia) curr).addInfoCabeca(nome, tel);
	}
	
	public void addInfoNomes(Migrantes curr, String nome) {
		((Familia) curr).addNomeFamilar(nome);
	}

	public void addAjuda(Migrantes migCurr, Ajuda ajCurr) {
		migCurr.addAjuda(ajCurr);
	}
}
