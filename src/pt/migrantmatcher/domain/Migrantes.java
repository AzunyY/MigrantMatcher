package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Migrantes {
	
	private List <Ajuda> aj;
	private int tel;
	
	protected Migrantes() {
		aj = new ArrayList <>();
	}

	public void addAjuda(Ajuda ajCurr) {
		aj.add(ajCurr);
	}
	
	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getTel() {
		return tel;
	}
}
