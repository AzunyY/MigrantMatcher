package pt.migrantmatcher.strategies;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.CatalogoAjudas;

public class OrdenaPorTipoStrategy implements OrdenaAjudas{
	
	private CatalogoAjudas ajudas;
	public OrdenaPorTipoStrategy(CatalogoAjudas catAjudas) {
		this.ajudas = catAjudas;
	}
	
	public List<Ajuda> ordena() {
		List <Ajuda> list = ajudas.getListAjudas();
		
		Collections.shuffle(list);

		return list.stream()
					   .sorted(Comparator.comparing(Ajuda::toString))
					   .collect(Collectors.toList());
	}

}