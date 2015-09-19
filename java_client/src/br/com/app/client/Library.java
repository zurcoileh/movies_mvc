package br.com.app.client;

import java.util.LinkedList;
import java.util.List;

public class Library {
	
	List<Movie>  movies = new LinkedList<Movie>();
	
	private  Connection connection;		

	public List<Movie> getMovies() {
		return movies;
	}

	public void sendRequest(String request) {
		getConnection().sendGet(request);
		this.movies = getConnection().findAllItems();
	}
	
	public void printResult(){		
		for (Movie mv: movies)			
			System.out.println(mv);		
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}	

}
