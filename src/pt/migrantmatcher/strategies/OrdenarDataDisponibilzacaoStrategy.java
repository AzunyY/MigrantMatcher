package pt.migrantmatcher.strategies;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;

public class OrdenarDataDisponibilzacaoStrategy implements OrdenaAjudas {

	@Override
	public List<Ajuda> ordena(List<Ajuda> original) {
		return original;
	}

}
