package pt.migrantmatcher.domain;

import java.util.List;

public class Familia extends Migrantes {
	
	private String nome;
	private int nPessoas;
	private List <String> listFamiliares;
	
	protected Familia(int nPessoas) {
		super(tel);
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

	public int getnPessoas() {
		return nPessoas;
	}

	public int getTel() {
		return tel;
	}
}


