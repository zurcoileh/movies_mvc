package br.com.app.server;
import com.db4o.ObjectSet;


public interface Subject {

	public void notifyObservers(ObjectSet<Movie> result);
	public void registerObserver(Observer o);
	
}
