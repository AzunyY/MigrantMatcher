package pt.migrantmatcher.domain;

public class Region {
	
	private String name;
	
	public Region(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Region) {
			Region other = (Region) obj;
			return other != null || this == other || this.name.equals(other.name);
		}
		return false;
	}
}
 