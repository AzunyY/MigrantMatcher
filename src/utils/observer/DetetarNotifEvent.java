package utils.observer;

public class DetetarNotifEvent implements Event {

	private String value;
	
	public DetetarNotifEvent (String value) {
		this.value = value;
	}

	public String getMessage() {
		return "Foi adicionada uma nova ajuda a regiao: " + value;
	}
	
}
