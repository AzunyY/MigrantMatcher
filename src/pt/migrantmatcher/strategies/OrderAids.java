package pt.migrantmatcher.strategies;

import java.util.List;
import java.util.Map;

import pt.migrantmatcher.domain.Aid;

public interface OrderAids {
	public List <Aid> order(Map<Integer, Aid> aidsList);
}
