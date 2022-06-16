package pt.migrantmatcher.domain;


public class Housing extends Aid {
	private final TYPE type = TYPE.HOUSING;

	private int nPersons;
	private Region reg;
	
	protected Housing(int nPersons) {
		super();
		this.nPersons = nPersons;
	}
	
	public void setRegion(Region reg) {
		this.reg = reg;
	}
	
	public Region getRegion() {
		return this.reg;
	}

	public int getnPersons() {
		return nPersons;
	}

	@Override
	public String toString() {
		return "Tipo - " + this.type + "| Alojamento na regiao: - " + this.reg;
	}

	@Override
	public String getInfo() {
		return this.reg.getName();
	}

	@Override
	public TYPE getType() {
		return this.type;
	}
}