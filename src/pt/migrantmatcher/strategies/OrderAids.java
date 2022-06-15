package pt.migrantmatcher.strategies;

import java.util.List;

import pt.migrantmatcher.domain.Aid;

public interface OrderAids {
	public List <Aid> order(List <Aid> ajudasList);
}
