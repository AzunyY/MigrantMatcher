package pt.migrantmatcher.strategies;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pt.migrantmatcher.domain.Ajuda;

public class OrdenaPorTipoStrategy implements OrdenaAjudas{
	
	private List <Ajuda> ajudas;
	
	public OrdenaPorTipoStrategy(List<Ajuda> list) {
		this.ajudas = list;
	}
	
	public List<Ajuda> ordena() {
		
		Collections.shuffle(ajudas);

		return ajudas.stream()
					   .sorted(Comparator.comparing(Ajuda::toString))
					   .collect(Collectors.toList());
	}

}