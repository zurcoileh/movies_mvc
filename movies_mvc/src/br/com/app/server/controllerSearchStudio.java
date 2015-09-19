package br.com.app.server;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;

public class controllerSearchStudio implements Search{
	
	private MovieDatabase db;
	private SearchView view;
	
	public controllerSearchStudio(MovieDatabase db, SearchView view){
		this.db = db;
		this.view = view;
	}
	
	public void makeRoute(){		
	
		//busca pelo studio		
		get(new Route("/movies/search/studio/:studio") {
			@Override
			public Object handle(Request request, Response response) {
				
				db.searchStudio(request.params(":studio"));

				return view.generateJSON(response);	     	 

			}
		});		
	         
	}

}
