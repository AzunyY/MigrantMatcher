package pt.migrantmatcher.domain;

/**
 * Instancia de Voluntario
 * @author Ana Luis FC53563
 **/
public class Voluntary  {

	private int tel;
	private String cod;

	/**
	 * Cria um voluntario
	 * @param tel - numero de telemovel voluntario
	 * @ensures voluntario e criado
	 **/
	public Voluntary(int tel) {
		this.tel = tel;
	}

	/**
	 * Devolve o numero de telemovel do voluntario
	 * @return numero de telemovel 
	 */
	public int getTel() {
		return tel;
	}

	/**
	 * Verifica se o codigo enviado para o voluntario e valido
	 * @param cod - codigo enviado
	 * @return true se for valido, falso caso contrario
	 */
	public boolean checkValidCod(String cod) {
		return this.cod.equals(cod);
	}

	/**
	 * Guarda o codigo enviado
	 * @param cod - codigo voluntario
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}
}
