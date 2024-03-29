package pt.migrantmatcher.strategies;

import java.util.List;
import java.util.Map;

import pt.migrantmatcher.domain.Aid;

/**
 * Padrao Strategy - Define-se qual e a forma de ordenar as ajudas
 * @author Ana Luis FC53563
 */
public interface OrderAids {
	public List <Aid> order(Map<Integer, Aid> aidsList);
}
