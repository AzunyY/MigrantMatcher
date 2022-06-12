package pt.migrantmatcher.domain;

import utils.observer.Observable;
import utils.observer.SucessoAddAjudaEvent;

public class Regiao extends Observable<SucessoAddAjudaEvent> {
	
	private String name;
	
	public Regiao(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void ajudaAdd() {
		SucessoAddAjudaEvent sa = new SucessoAddAjudaEvent(this.name);
		this.notifyAllObservers(sa);
	}
	
}
