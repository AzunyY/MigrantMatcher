package utils.observer;

import java.util.HashMap;

/**
 * Esta classe permite definir objetos do tipo Observable que sao capazes de 
 * propagar eventos a todos os objetos que implementam Observer e que estejam
 * a "observar" este objeto, ou seja, estejam na lista observers
 *
 * @param <E> objeto que extende a interface Event
 */
public class Observable<T extends Event> {

	private HashMap<Observer<T>, String> observers = new HashMap<>();

	protected void notifyAllObservers(T event) {
		for (observers.entrySet() e : observers.entrySet()) 
	}
	
	protected void notifySingleObservers(T event) {
	
	}


	public void addObserver(Observer<T> o, String reg) {
		observers.put(o, reg);
	}
}
