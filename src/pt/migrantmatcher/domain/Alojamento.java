package pt.migrantmatcher.domain;

public class Alojamento extends Ajuda {
	
	private int nPessoas;
	private Regiao reg;
	
	protected Alojamento(int nPessoas) {
		super();
		this.nPessoas = nPessoas;
	}
	
	public void setRegiao(Regiao reg) {
		this.reg = reg;
	}
}