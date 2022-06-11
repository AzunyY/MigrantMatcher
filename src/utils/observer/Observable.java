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
public class Observable<E extends Event> {
	
	protected List<Observer<E>> observers = new ArrayList<>();
	
	public void emitEvent(E e) {
		for (Observer<E> o : observers) {
			o.processEvent(e);
		}
	}
	
	public void registerObserver(Observer<E> obs) {
		observers.add(obs);
	}
	
	public void unregisterObserver(Observer<E> obs) {
		observers.remove(obs);
	}

}
