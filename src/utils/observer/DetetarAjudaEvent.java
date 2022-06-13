package utils.observer;

public class DetetarAjudaEvent implements Event{
	
	private String value;
	
	public DetetarAjudaEvent (String regiao) {
		this.value = regiao;
	}

	public String getValue() {
		return value;
	}
 
}
