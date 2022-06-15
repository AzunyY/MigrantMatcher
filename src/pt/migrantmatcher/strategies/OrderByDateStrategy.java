package pt.migrantmatcher.strategies;

import java.util.List;

import pt.migrantmatcher.domain.Aid;

public class OrderByDateStrategy implements OrderAids {
	
	public List<Aid> order(List <Aid> ajudasList) {
		return ajudasList;
	}

}
