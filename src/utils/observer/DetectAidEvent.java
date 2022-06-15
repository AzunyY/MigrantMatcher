package utils.observer;

public class DetectAidEvent implements Event{
	
	private String value;
	
	public DetectAidEvent (String regiao) {
		this.value = regiao;
	}

	protected String getValue() {
		return value;
	}

	public String getMessage() {
		return "There is a new Aid available at region: " + value;
	}
 
}
