package pt.migrantmatcher.facade.DTO;

/**
 * Padrao DTO - Nao se quer enviar a Ajuda para o cliente
 */
public class AidDTO {

	private int ref;
	private String info;
	private String type;
	private boolean availability;
	private int tel;

	/**
	 * Construtor de AjudaDTO
	 * @param ref - referencia com que foi criada
	 * @param info - Desc ou regiao 
	 * @param availability - se esta livre ou nao
	 * @param tel - numero de tel do voluntario
	 * @param type - tipo de ajuda
	 */
	public AidDTO(int ref, String info, boolean availability, int tel, String type) {
		this.ref = ref;
		this.info = info;
		this.availability = availability;
		this.tel = tel;
		this.type = type;
	}

	/**
	 * Devolve informacao de Ajuda
	 * @return informacao de ajuda
	 */
	public String getAidInfo() {

		return "Aid - " + this.type + " | Info - " + this.info;

	}

	/**
	 * Devolve o tipo de ajuda
	 * @return tipo de ajuda 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Devolve a desc ou regiao
	 * @return devolve a desc ou regiao
	 */
	public String getInfo() {
		return this.info;
	}

	/**
	 * Devolve se esta livre ou nao 
	 * @return true se estiver livre, falso caso contrario
	 */
	public boolean getAvailability() {
		return this.availability;
	}

	public int getTel() {
		return this.tel;
	}

	public int getRef() {
		return ref;
	}

	public void set(boolean availability) {
		this.availability = availability;		
	}
}

