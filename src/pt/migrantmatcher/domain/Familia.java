package pt.migrantmatcher.domain;

import java.util.List;

public class Familia extends Migrantes {
	
	private String nome;
	private int tel;
	private int nPessoas;
	private List <String> listFamiliares;
	
	protected Familia(int nPessoas) {
		super();
		this.nPessoas = nPessoas;
	}
	
	public void addInfoCabeca(String nome, int tel) {
		this.nome = nome;
		this.tel = tel;
	}
	
	public void addNomeFamilar(String nome) {
		listFamiliares.add(nome);
	}
	
	public String getNome() {
		return nome;
	}
}


