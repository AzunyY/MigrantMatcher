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
 * @author Ana Luis FC53563 
 */
public class Observable<T extends Event> {

	private Map<Observer<T>, String> observers = new HashMap<>();

	protected void notifyAllObservers(T event, String reg) {
		
		System.out.println("Sending notification to the migrants...");
		
		boolean isSent = false;
		
		for (Entry<Observer <T>, String> entry : observers.entrySet()) {
		    if(entry.getValue().equals(reg)) {
		    	isSent = true;
		    	entry.getKey().receiveEvent(event);
		    }
		}
		
		if(!isSent)
		System.out.println("No migrants registerd for the notification!");
	}

	public void addObserver(Observer<T> o, String reg) {
		System.out.println("Migrant added to the observerers list of region: " + reg);
		observers.put(o, reg);
	}
}
