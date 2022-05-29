package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Migrantes {
	
	List <Ajuda> aj;
	
	protected Migrantes() {
		aj = new ArrayList <>();
	}

	public void addAjuda(Ajuda ajCurr) {
		aj.add(ajCurr);
	}
	
}
