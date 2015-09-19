package br.com.app.server;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;

public class controllerGetKey implements Search{
	
	private MovieDatabase db;
	private SearchView view;
	
	public controllerGetKey(MovieDatabase db, SearchView view){
		this.db = db;
		this.view = view;
	}
	
	public void makeRoute(){
		
		//busca por palavra chave
		get(new Route("/movies/search/key/:keyword") {
			@Override
			public Object handle(Request request, Response response) { 
							
				db.searchKeyword((request.params(":keyword")));

				return view.generateJSON(response);	     	    
			}
		});	
		
	}

}
