package pt.migrantmatcher.domain;

import java.util.List;

import utils.observer.DetetarAjudaEvent;
import utils.observer.Observable;

public class Voluntario extends Observable<DetetarAjudaEvent> {

	private int tel;
	private List <Ajuda> listAjudas;
	private String cod;

	public Voluntario(int tel) {
		this.tel = tel;
	}

	public int getTel() {
		return tel;
	}

	public void addAjuda(Ajuda ajuda, CatalogoRegioes regioes) {
		listAjudas.add(ajuda);

		String reg = ajuda instanceof Alojamento ? ((Alojamento) ajuda).getRegiao().getName() : "ALL";

		if(reg.equals("ALL")) {
			List <String> regioesNomes = regioes.getRegioesNomes();
			for(String s : regioesNomes)
				notifyAllObservers(new DetetarAjudaEvent(s));
		} else
			notifyAllObservers(new DetetarAjudaEvent(reg));
	}

	public boolean checkValidCod(String cod) {
		return this.cod.equals(cod);
	}

	public void setCod(String cod) {
		this.cod = cod;
	}
}
