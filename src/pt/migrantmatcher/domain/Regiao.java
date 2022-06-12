package pt.migrantmatcher.domain;

import java.util.List;

import utils.observer.Observable;
import utils.observer.SucessoAddAjudaEvent;

public class Regiao extends Observable<SucessoAddAjudaEvent> {
	
	private String name;
	private List <Migrantes> listMigToNotify;
	
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
