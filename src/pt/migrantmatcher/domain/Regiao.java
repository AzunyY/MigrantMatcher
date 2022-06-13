package pt.migrantmatcher.domain;

import utils.observer.DetetarAjudaEvent;
import utils.observer.DetetarNotifEvent;
import utils.observer.Observable;
import utils.observer.Observer;

public class Regiao extends Observable<DetetarNotifEvent> implements Observer<DetetarAjudaEvent>{
	
	private String name;
	
	public Regiao(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public void receiveEvent(DetetarAjudaEvent e) {
		notifyAllObservers(new DetetarNotifEvent(e.getValue()));
	}

	public void notif(Migrantes migCurr) {
		this.addObserver(migCurr); //1.1.1
	}
	
}
 