package pt.migrantmatcher.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pt.migrantmatcher.domain.Aid;

/**
 * Ordena as ajudas por ordem de chegada
 * @author Ana Luis FC53563
 */

public class OrderByDateStrategy implements OrderAids {
	@Override
	public List<Aid> order(Map<Integer, Aid> aidsList) {

		List <Aid> orderedList = new ArrayList<>();

		for(int i = 0; i < aidsList.size(); i++)
			orderedList.add(aidsList.get(i));

		return orderedList;
	}
}
