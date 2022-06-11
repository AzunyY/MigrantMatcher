package pt.migrantmatcher.strategies;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.CatalogoAjudas;

public class OrdenarDataDisponibilzacaoStrategy implements OrdenaAjudas {

	private CatalogoAjudas ajudas;
	
	public OrdenarDataDisponibilzacaoStrategy(CatalogoAjudas catAjudas) {
		this.ajudas = catAjudas;
	}
	
	public List<Ajuda> ordena() {
		return ajudas.getListAjudas();
	}

}
