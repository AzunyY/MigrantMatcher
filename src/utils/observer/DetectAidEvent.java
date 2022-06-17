package utils.observer;

/**
 * Trata os eventos de Add Ajudas
 * @author Ana Luis FC53563 
 */

public class DetectAidEvent implements Event{
	
	private String value;
	
	public DetectAidEvent (String regiao) {
		this.value = regiao;
	}

	/**
	 * Devolve a informacao associada ao objeto
	 * @return regiao
	 */
	protected String getValue() {
		return value;
	}

	/**
	 * Devolve uma representacao do objeto
	 * @return messagem informativa
	 */
	public String getMessage() {
		return "There is a new Aid available at region: " + value;
	}
 
}
