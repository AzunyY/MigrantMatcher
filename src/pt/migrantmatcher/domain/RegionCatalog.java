package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class RegionCatalog {
	
	//A lista das regioes deve estar no configuration
	private List <Region> regsList;
	
	public RegionCatalog(List <String> reg) {
		
		regsList = new ArrayList <>();
		
		for(String s : reg) 
			this.regsList.add(new Region(s));
	}

	public List<String> getRegions() {
			
			List <String> listReg = new ArrayList<>(); //1.1
			
			regsList.forEach(x -> {
				listReg.add(x.getName()); //1.2, 1.3
			});
			
			return listReg;
	}
}
