package pt.migrantmatcher.domain;

import java.util.List;

public class Familia extends Migrantes {
	
	private String nome;
	private int nPessoas;
	private List <String> listFamiliares;
	
	protected Familia(int nPessoas) {
		super();
		this.nPessoas = nPessoas;
	}
	
	public void addInfoCabeca(String nome, int tel) {
		this.nome = nome;
		super.setTel(tel);
	}
	
	public void addNomeFamilar(String nome) {
		this.listFamiliares.add(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public int getnPessoas() {
		return nPessoas;
	}
	
	public boolean isValid() {
		return this.listFamiliares.size() + 1 == this.nPessoas;
	}
}


