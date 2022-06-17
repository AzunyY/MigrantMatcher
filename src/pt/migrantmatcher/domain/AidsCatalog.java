package pt.migrantmatcher.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import pt.migrantmatcher.facade.DTO.AidDTO;
import utils.observer.DetectAidEvent;
import utils.observer.Observable;


/**
 * Classe que vai representar uma Lista com todas as ajudas
 * Vai implementar o padrao Observer porque eh ela que ve quando 
 * se verifica um novo evento de DeteterAjuda pois, eh aqui que 
 * as ajudas sao adicionadas apos o vol confirmar a sua oferta.
 * 
 * @author Ana Luis FC53563
 **/
public class AidsCatalog extends Observable<DetectAidEvent>{

	private Map <Integer, Aid> listAids = new HashMap<>();
	private int count = 0;
	//serve para verificar que tipo de notificacao estah a ser feita consoante o tipo de ajuda
	private boolean notifyingCorrectly = false; 

	/**
	 * Cria uma instancia da Ajuda - Housing
	 * @param nPessoas - indicador do numero de Pessoas que podem estar no alojamento
	 * @return uma instancia da ajuda que representa a ajuda corrente que ainda nao
	 * foi adicionada ao catalogo
	 */
	protected Housing createHousing(int nPersons) {
		return new Housing(nPersons); //1.1
	}

	/**
	 * Add a instancia da ajuda criada em createHousing(n)
	 * @param currAid - A ajuda corrente
	 * @param region - Onde e que o alojamento estah localizado
	 * @ensures a instancia de currAid tem os novos dados adicionados
	 */
	protected void insertReg (Aid currAid, Region region) {
		((Housing) currAid).setRegion(region); //1.1
	}

	/**
	 * Cria uma instancia da Ajuda - ITEM
	 * @param desc - referencia da ajuda
	 * @return uma instancia da ajuda que representa a ajuda corrente que ainda nao
	 * foi adicionada ao catalogo
	 */
	protected Item getNewItem(String desc) {
		return new Item(desc);
	}

	/**
	 * Adiciona a ajuda corrente ao catalogo de ajudas
	 * @param currAid - ajuda corrente
	 * @param volCurr - voluntario que vai estah associado a ajuda
	 * @ensures ajuda estah associada ao voluntario && listAids != null && listAids.size()!=0
	 * && observers sao notificados corretamente
	 */
	protected void addAid(Aid currAid, Voluntary volCurr, List <String> regList) {
		
		currAid.setRef(count);
		currAid.setVol(volCurr); //2.1
		listAids.put(count, currAid); //2.2
		count++;

		boolean notifyAll = currAid instanceof Housing ? false : true;
		
		if(notifyAll) {
			notifyingCorrectly = currAid instanceof Item ? true : false;;
			for(Aid a : listAids.values())
				notifyAllObservers(new DetectAidEvent(a.getInfo()), a.getInfo());
		} else {
			notifyingCorrectly = currAid instanceof Housing ? true : false;;
			String reg = ((Housing) currAid).getRegion().getName();
			notifyAllObservers(new DetectAidEvent(reg), reg);
		}
	}

	/**
	 * Filtra o catalogo de Ajudas por regiao e devolve uma lista representativa
	 * @param reg - regiao a qual uma ajuda estah associada
	 * @returns lista de ajudas filtrada por regiao
	 */
	protected Map<Integer, Aid> filterByReg(String reg) {
		
		Map<Integer, Aid> listAidsreg = new HashMap<>();
		
		listAids.entrySet().stream().forEach(x -> {
						if(x.getValue() instanceof Housing) {
							if(x.getValue().getInfo().equals(reg))
								listAidsreg.put(x.getKey(), x.getValue());
						} else 
							listAidsreg.put(x.getKey(), x.getValue());
		});
		
		return listAidsreg;
	}

	/**
	 * Verifica se a ajuda corrente foi adicionada ao catalogo 
	 * @param currAid - ajuda corrente
	 * @return a ajuda corrente
	 */
	protected Optional<Boolean> aidWasAdded(Aid currAid) {
		Optional<Boolean> aid = Optional.ofNullable(listAids.containsValue(currAid) && listAids.containsKey(currAid.getRef()));
		return aid;
	}
	
	/**
	 * Devolve se os Observadores estao a ser notificados corretamente para
	 * testes
	 **/
	protected boolean isNotifyingCorrectly() {
		return notifyingCorrectly;
	}

	/**
	 * Devolve uma ajuda a partir da sua representacao
	 * @param aidDTO - representacao da ajuda
	 * @ensures aidDTO.equals(aid a devolver)
	 * @returns uma ajuda
	 **/
	public Aid getAid(AidDTO aidDTO) {
		return listAids.get(aidDTO.getRef());
	}
}
