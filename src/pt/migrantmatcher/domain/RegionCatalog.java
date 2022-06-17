package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.exceptions.ErrorCreatingRegionsException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
/**
 * Classe com listas de Regioes
 *
 */
public class RegionCatalog {
	
	protected List <Region> regsList;
	
	/***
	 * Vai criar um catalogo de Regioes
	 * @param filename - nome do ficheiro onde podem estar as regioes
	 * @param mockRegCatalog - valor default
	 * @throws ErrorCreatingRegionsException caso ambas as regions e filename estejam vazios
	 * @throws PropertiesLoadingException caso nao haja regioes no ficheiro mas sim no valor default
	 * 
	 */
	public RegionCatalog(List<String> reg) throws ErrorCreatingRegionsException, PropertiesLoadingException {

		MigrantConfiguration catReg = MigrantConfiguration.getInstance("defaults.properties");

		try {
			addToList(catReg.getProperty("regioes"));
		} catch (PropertiesLoadingException e) {
			
			if(reg.isEmpty() || reg.equals(null))
				throw new ErrorCreatingRegionsException();
			
			addToList(reg);
			System.err.println("There is a value missing in the properties file but it will be used a default value!");
			throw new PropertiesLoadingException();
		}		
	}

	public RegionCatalog(String string, List<String> reg) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo auxiliar para adiconar uma lista de regioes ao catalogo
	 * @param listReg lista a adicionar ao catalogo
	 */
	public void addToList(List<String> listReg) {
		regsList = new ArrayList <>();

		for(String s : listReg) 
			this.regsList.add(new Region(s));		
	}

	/**
	 * Devolve uma lista de Regioes
	 * @return lista de regioes
	 */
	public List<String> getRegions() {

		List <String> listReg = new ArrayList<>(); //1.1

		regsList.forEach(x -> {
			listReg.add(x.getName()); //1.2, 1.3
		});

		return listReg;
	}

	/**
	 * Verifica se a regiao e valida
	 * @param region regiao
	 * @return true se a regiao estiver no catalog, false caso contrario
	 */
	public boolean isValid(String region) {
		for(Region r : regsList) {
			if(r.getName().equals(region))
				return true;
		}
		return false;
	}
}
