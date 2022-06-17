package pt.migrantmatcher.domain;

/**
 * Instancia de Regiao
 *
 * @author Ana Luis FC53563
 */
public class Region {
	
	private String name;

	/**
	 * Inicializa uma Regiao
	 * @param name nome da regiao
	 */
	public Region(String name) {
		this.name = name;
	}
	
	/**
	 * Devolve o nome da regiao
	 * @return nome da regiao
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Verfica se um objeto e uma regiao e se sao iguais
	 * returns true se objeto for instancia de regiao e tiver nome igual, 
	 * false caso contrario
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Region) {
			Region other = (Region) obj;
			return other != null || this == other || this.name.equals(other.name);
		}
		return false;
	}
}
 