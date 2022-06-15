package utils.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Esta classe permite definir objetos do tipo Observable que sao capazes de 
 * propagar eventos a todos os objetos que implementam Observer e que estejam
 * a "observar" este objeto, ou seja, estejam na lista observers
 *
 * @param <E> objeto que extende a interface Event
 */
public class Observable<T extends Event> {

	private Map<Observer<T>, String> observers = new HashMap<>();

	protected void notifyAllObservers(T event, String filename, String reg) {
		
		for (Entry<Observer <T>, String> entry : observers.entrySet()) {
		    if(entry.getValue().equals(reg))
		    	entry.getKey().receiveEvent(filename,event);
		}
	}

	protected void notifySingleObservers(T event, String filename, String reg) {
		observers.forEach((key,value) -> {
			if(value.equals(reg))
				key.receiveEvent(filename, event);
		} );
	}

	public void addObserver(Observer<T> o, String reg) {
		observers.put(o, reg);
	}
}
