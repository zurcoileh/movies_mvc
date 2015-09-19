package br.com.app.server;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;

public class controllerAdd implements Register{
	
	private MovieDatabase db;
	private RegisterView view;	
	
	public controllerAdd(MovieDatabase db, RegisterView view) {	
		this.db = db;
		this.view = view;
	}

	public void makeRoute(){
		
		//adiciona filme ao banco
		get(new Route("/movies/save/:id/:title/:duration/:year/:gender/:studio/:rating") {
			@Override
			public Object handle(Request request, Response response) {	     	   

				//ex movies/add/121/insurgent/160/2014/adventure/warnner 
				db.addMovie(new Movie(db.getLastIndex(), Double.valueOf(request.params(":duration")),
						request.params(":title"), Integer.valueOf(request.params(":year")),
						new Espec(Gender.getValueByString(request.params(":gender")), request.params(":studio")), new Rating("0")));
				
				return view.jsonResult(response);
			}
		});	
	         
	}

}
