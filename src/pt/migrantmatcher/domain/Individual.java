package pt.migrantmatcher.domain;

public class Individual extends Migrantes{
	
	private String nome;
	private int tel;
	
	protected Individual(String nome, int tel) {
		super();
		this.nome = nome;
		this.tel = tel;
	}
	
	public String getNome() {
		return nome;
	}

	public int getTel() {
		return tel;
	}

}
