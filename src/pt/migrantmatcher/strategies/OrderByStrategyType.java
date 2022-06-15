package pt.migrantmatcher.strategies;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pt.migrantmatcher.domain.Aid;

public class OrderByStrategyType implements OrderAids{
	
	public List<Aid> order(List <Aid> ajudasList) {
		
		Collections.shuffle(ajudasList);

		return ajudasList.stream()
					   .sorted(Comparator.comparing(Aid::toString))
					   .collect(Collectors.toList());
	}

}