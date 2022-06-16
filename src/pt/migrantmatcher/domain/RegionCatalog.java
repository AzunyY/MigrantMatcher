package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.ThereIsNoValueInPropertiesException;

public class RegionCatalog {

	//A lista das regioes deve estar no configuration
	protected List <Region> regsList;

	protected RegionCatalog(String filename, List <String> regions) throws ErrorCreatingRegionsException {

		MigrantConfiguration catReg = MigrantConfiguration.getInstance(filename);
		List<String> listReg;

		try {
			listReg = catReg.getProperty("regioes");
			addToList(listReg);
		} catch (ThereIsNoValueInPropertiesException e) {
			
			if(regsList.isEmpty())
				throw new ErrorCreatingRegionsException();
			
			addToList(regions);
			System.err.println("There is a value missing in the properties file but it will be used a default value!");
		}		
	}

	protected void addToList(List<String> listReg) {
		regsList = new ArrayList <>();

		for(String s : listReg) 
			this.regsList.add(new Region(s));		
	}

	protected List<String> getRegions() {

		List <String> listReg = new ArrayList<>(); //1.1

		regsList.forEach(x -> {
			listReg.add(x.getName()); //1.2, 1.3
		});

		return listReg;
	}

	protected boolean isValid(String region) {
		for(Region r : regsList) {
			if(r.getName().equals(region))
				return true;
		}
		return false;
	}
}
