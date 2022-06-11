package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Migrantes {
	
	private List <Ajuda> aj;
	private int tel;
	
	protected Migrantes(int tel) {
		aj = new ArrayList <>();
		this.tel = tel;
	}

	public void addAjuda(Ajuda ajCurr) {
		aj.add(ajCurr);
	}

	public int getTel() {
		return tel;
	}
}
