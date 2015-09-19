package br.com.app.server;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;

public class controllerGetId implements Search{
	
	private MovieDatabase db;
	private SearchView view;
	
	public controllerGetId(MovieDatabase db, SearchView view){
		this.db = db;
		this.view = view;
	}
	
	public void makeRoute(){
		
		get(new Route("/movies/search/id/:id") {
			@Override
			public Object handle(Request request, Response response) {
				
				db.searchId(Integer.valueOf(request.params(":id")));

				return view.generateJSON(response);			     	    
			}
		});	
	}

}
