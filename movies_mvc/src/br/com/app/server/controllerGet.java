package br.com.app.server;

import static spark.Spark.get;


import spark.Request;
import spark.Response;
import spark.Route;


public class controllerGet implements Search{
	
	private MovieDatabase db;
	private SearchView view;
	
	public controllerGet(MovieDatabase db, SearchView view){
		this.db = db;
		this.view = view;		
		
	}
	
	public void makeRoute(){
		
		//listas todos elementos
		get(new Route("/movies/list") {
		@Override
			public Object handle(Request request, Response response) {	
			
				db.listMovies();
				return view.generateJSON(response);	     	    
			}
		});
	         
	}
		
}
