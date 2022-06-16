package pt.migrantmatcher.domain;

/**
 * Esta classe vao ser as representacoes das ajudas 
 * E absrtacta pois estou a utilizar o padrao Template method uma vez que 
 * ha pormenores para os tipos de ajudas que sao diferentes. O item tem desc
 * mas o Housing tem o nPersons e reg.
 **/
public abstract class Aid {
		
	private boolean availability;
	private Voluntary vol;
	/**
	 * Construtor de uma Ajuda 
	 * @ensures a ajuda estah disponivel
	 */
	protected Aid() {
		availability = true;
	}
	
	/**
	 * Associa o voluntario a ajuda
	 * @param vol - o voluntario que oferece a ajuda
	 * @requires vol != null
	 * @ensures vol estah associado a ajuda
	 */
	public void setVol(Voluntary vol) {
		this.vol = vol;
	}


	/**
	 * Muda o estado da ajuda para indisponivel 
	 * @ensures a ajuda nao estah disponivel
	 */
	public void putAidToNotAvailable() {				
		availability = false;
	}

	/**
	 * Determina se a ajuda estah disponivel
	 * @return true se a ajuda estah disponivel, false caso contrario
	 */
	public boolean getAvailability() {
		return this.availability;
	}


	/**
	 * Devolve o numero do voluntario a que estah associada
	 * @return um integer que representa o numero do vol
	 */
	public int getVol() {
		return vol.getTel();
	}
	
	/**
	 * Devolve a representacao de cada tipo de ajuda
	 * @return uma String que eh a representacao do tipo de ajuda em questao
	 */
	public abstract String toString();

	/**
	 * Devolve a informacaoo de cada tipo de ajuda, necessaria a classe AjudaDto
	 * @return uma String quue contenha a inforamacao necessaria
	 */
	public abstract String getInfo();
	
	/**
	 * Devolve o tipo de ajudas 
	 * @return uma String com o tipo da ajuda
	 */
	public abstract TYPE getType();
}

