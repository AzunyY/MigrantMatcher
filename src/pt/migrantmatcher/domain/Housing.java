package pt.migrantmatcher.domain;

/**
 * Esta classe e subclasse de Aid
 **/
public class Housing extends Aid {
	private final TYPE type = TYPE.HOUSING;

	private int nPersons;
	private Region reg;
	
	/**
	 * Inicializa uma oferta de alojamento
	 * @param nPersons - numero de pessoas que podem ter acesso ao alojamento
	 * @requires nPersons > 0
	 * @ensures criacao de uma instancia de alojamento
	 **/
	protected Housing(int nPersons) {
		super();
		this.nPersons = nPersons;
	}
	
	/**
	 * Adiciona uma regiao a alojamento
	 * @param reg - regiao onde o alojamento se localiza
	 * @requires  reg!= null
	 **/
	public void setRegion(Region reg) {
		this.reg = reg;
	}
	
	/**
	 * Devolve a regiao de um alojamento
	 **/
	public Region getRegion() {
		return this.reg;
	}

	/**
	 * Devolve o numero de pessoas que podem estar no alojamento
	 **/
	public int getnPersons() {
		return nPersons;
	}


	/**
	 * Ver {@Aid}
	 **/
	@Override
	public String toString() {
		return "Tipo - " + this.type + "| Alojamento na regiao: - " + this.reg.getName();
	}
	
	/**
	 * Ver {@Aid}
	 **/
	@Override
	public String getInfo() {
		return this.reg.getName();
	}
	
	/**
	 * Ver {@Aid}
	 **/
	@Override
	public String getType() {
		return this.type.name();
	}
}