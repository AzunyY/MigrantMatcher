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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alojamento other = (Alojamento) obj;
		if (reg != other.reg)
			return false;
		if (nPessoas != other.nPessoas)
			return false;
		return true;
	}
}