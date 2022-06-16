package pt.migrantmatcher.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pt.migrantmatcher.domain.Aid;

public class OrderByDateStrategy implements OrderAids {
	@Override
	public List<Aid> order(Map<Integer, Aid> aidsList) {

		List <Aid> orderedList = new ArrayList<>();

		for(int i = 0; i < aidsList.size(); i++)
			orderedList.add(aidsList.get(i));

		return orderedList;
	}
}
