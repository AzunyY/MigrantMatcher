package utils.observer;

public class DetetarNotifEvent implements Event {

	private String value;
	
	public DetetarNotifEvent (String reg) {
		this.value = reg;
	}

	public String getMessage() {
		return "Foi adicionada uma nova ajuda a regiao: " + value;
	}
	
}
