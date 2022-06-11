package utils.observer;


public class SucessoAddAjudaEvent implements Event {

	private String message;
	
	public SucessoAddAjudaEvent (String message) {
		this.message = message;
	}

	public String getMessage () {	
		return message;
	}
	
}
