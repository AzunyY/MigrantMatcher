package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class Familia extends Migrantes {
	
	private int nPessoas;
	private List <String> listFamiliares;
	
	protected Familia(int nPessoas) {
		super();
		this.nPessoas = nPessoas;
		this.listFamiliares = new ArrayList<>(); 
	}
	
	public void addInfoCabeca(String nome, int tel) {
		setNome(nome);
		setTel(tel);
	}
	
	public void addNomeFamilar(String nome) {
		this.listFamiliares.add(nome);
	}

	public int getnPessoas() {
		return nPessoas;
	}
	
	public boolean isValid() {
		return this.listFamiliares.size() + 1 == this.nPessoas;
	}	
}


