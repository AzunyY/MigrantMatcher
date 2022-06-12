package utils.observer;

public class SucessoAddAjudaEvent implements Event {

	private String reg;
	
	public SucessoAddAjudaEvent (String reg) {
		this.reg = reg;
	}

	public String getMessage () {	
		
		if(reg.equals("All"))
			return "Uma nova ajuda foi adicionada a todas as regioes!";
		
		return "Uma nova ajuda foi adicionada a regiao " + reg;
	}
	
}
