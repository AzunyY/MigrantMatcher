package tests.mocks;

import java.util.List;

import pt.migrantmatcher.domain.RegionCatalog;
import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;

public class MockRegCatalog extends RegionCatalog {
	
	public MockRegCatalog(String string, List<String> reg) throws ErrorCreatingRegionsException {
		super(string, reg);
	}
	

	public void addToList(List<String> listReg) {
		super.addToList(listReg);	
	}

	public List<String> getRegions(){			
		return super.getRegions();
	}

	public boolean isValid(String region) {
		return super.isValid(region);
	}
}
