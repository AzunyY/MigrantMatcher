package pt.migrantmatcher.domain;

public class Housing extends Aid {
	
	private int nPersons;
	private Region reg;
	
	protected Housing(int nPersons) {
		super();
		this.nPersons = nPersons;
		setType(Type.HOUSING);
		setInfo(Integer.toString(nPersons));
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
}