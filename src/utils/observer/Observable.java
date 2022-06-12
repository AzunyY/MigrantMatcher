package utils.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe permite definir objetos do tipo Observable que sao capazes de 
 * propagar eventos a todos os objetos que implementam Observer e que estejam
 * a "observar" este objeto, ou seja, estejam na lista observers
 *
 * @param <E> objeto que extende a interface Event
 */
public class Observable<T extends Event> {

	private List<Observer<T>> observers = new ArrayList<>();

	protected void notifyAllObservers(T event) {
		for (Observer<T> o : observers) {
			o.receiveEvent(event);
		}
	}

	public void addObserver(Observer<T> o) {
		observers.add(o);
	}
}
