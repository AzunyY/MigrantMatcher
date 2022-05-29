package pt.migrantmatcher.strategies;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pt.migrantmatcher.domain.Ajuda;

public class OrdenaPorTipoStrategy implements OrdenaAjudas{

	@Override
	public List<Ajuda> ordena(List<Ajuda> original) {
		Collections.shuffle(original);

		return original.stream()
					   .sorted(Comparator.comparing(Ajuda::toString))
					   .collect(Collectors.toList());
	}

}
