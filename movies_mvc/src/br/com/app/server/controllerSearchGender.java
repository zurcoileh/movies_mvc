package br.com.app.server;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;

public class controllerSearchGender implements Search{
	
	private MovieDatabase db;
	private SearchView view;
	
	public controllerSearchGender(MovieDatabase db, SearchView view){
		this.db = db;
		this.view = view;
	}
	
	public void makeRoute(){		
	
		//busca pelo genero		
		get(new Route("/movies/search/gender/:gender") {
			@Override
			public Object handle(Request request, Response response) {	 
				
				db.searchGender(Gender.valueOf(Gender.class, request.params(":gender")));

				return view.generateJSON(response);	     	 

			}
		});	
	         
	}

}
