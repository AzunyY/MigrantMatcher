package pt.migrantmatcher.domain;

public class Individual extends Migrantes{
	
	private String nome;
	
	protected Individual(String nome, int tel) {
		super();
		super.setTel(tel);
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
