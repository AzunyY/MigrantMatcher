package utils.observer;

/**
 * Esta interface permite a um objeto Observer ser informado de propagacao de
 * eventos Event em objetos Observable
 *
 * @param <E> objeto que extende Event
 */

public interface Observer<T extends Event> {
	public void receiveEvent(T e);
}