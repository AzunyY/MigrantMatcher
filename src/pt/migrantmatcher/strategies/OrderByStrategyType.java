package pt.migrantmatcher.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pt.migrantmatcher.domain.Aid;

/**
 * Ordenas-se as ajudas por tipo de estrategia
 * @author azuny
 */
public class OrderByStrategyType implements OrderAids{

	@Override
	public List<Aid> order(Map<Integer, Aid> aidsList) {
		List<Aid> aidList = new ArrayList<>(aidsList.values());
		return aidList.stream()
					   .sorted(Comparator.comparing(Aid::getType))
					   .collect(Collectors.toList());
	}
}