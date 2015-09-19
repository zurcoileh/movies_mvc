package br.com.app.server;
import com.db4o.ObjectSet;


public interface Observer {
	
	public void update(ObjectSet<Movie> result);

}
