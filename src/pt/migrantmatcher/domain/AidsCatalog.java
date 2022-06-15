package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.migrantmatcher.facade.DTO.AidDTO;
import utils.observer.DetectAidEvent;
import utils.observer.Observable;

public class AidsCatalog extends Observable<DetectAidEvent>{

	private List <Aid> listAids;

	public AidsCatalog() {
		listAids = new ArrayList<>();
	}

	public Housing createHousing(int nPessoas) {
		return new Housing(nPessoas); //1.1
	}

	public void insertReg (Aid currAid, Region region) {
		((Housing) currAid).setRegion(region); //1.1
	}

	public Item getNewItem(String desc) {
		return new Item(desc);
	}

	public void addAid(Aid currAid, Voluntary volCurr, List <String> regList) {
		
		currAid.setVol(volCurr); //2.1
		listAids.add(currAid); //2.2

		boolean notifyAll = currAid instanceof Housing ? false : true;

		if(notifyAll) {
			for(String s : regList)
				notifyAllObservers(new DetectAidEvent(s), s);
		} else {
			String reg = ((Housing) currAid).getRegion().getName();
			notifySingleObservers(new DetectAidEvent(reg), reg);
		}
	}

	public List<Aid> filterByReg(String reg) {

		List <Aid> listAjudasReg = new ArrayList<>();//1.1

		listAids.stream()
		.forEach(x -> {
			if(x instanceof Housing) {
				if(((Housing) x).getRegion().getName().equals(reg) && x.isAidAvailable()) //1.2 
					listAjudasReg.add(x); //1.3
			}else if (x.isAidAvailable()) //1.4
				listAjudasReg.add(x); //1.5
		});

		return listAjudasReg;
	}

	public Aid getAid(AidDTO aidDTO) {

		for(Aid a: this.listAids)
			if(a.getType().toString().equals(aidDTO.getType().toString()) 
					&& a.getInfo().equals(aidDTO.getInfo()))
				return a;

		return null;
	}

	public List<Aid> getAidList() {
		return this.listAids;
	}

	public Optional<Boolean> aidWasAdded(Aid currAid) {
		Optional<Boolean> aid = Optional.ofNullable(listAids.contains(currAid));
		return aid;

	}
}
