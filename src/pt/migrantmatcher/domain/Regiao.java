package pt.migrantmatcher.domain;

public class Regiao {
	
	private String name;
	
	public Regiao(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Regiao) {
			Regiao other = (Regiao) obj;
			return other != null || this == other || this.name.equals(other.name);
		}
		return false;
	}
}
 