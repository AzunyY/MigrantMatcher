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
	
	public Regiao getRegiao() {
		return this.reg;
	}

	public int getnPessoas() {
		return nPessoas;
	}

	public boolean equals(Object obj) {
		if(obj instanceof Alojamento) {
			Alojamento other = (Alojamento) obj;
			return other != null || this == other || (other.reg.equals(this.reg) && this.nPessoas == other.nPessoas);
		}
		return false;
	}
}