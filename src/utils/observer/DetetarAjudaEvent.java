package utils.observer;

public class DetetarAjudaEvent implements Event{
	
	private String value;
	
	public DetetarAjudaEvent (String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
 
}
