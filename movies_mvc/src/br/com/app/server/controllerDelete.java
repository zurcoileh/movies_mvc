package br.com.app.server;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;

public class controllerDelete implements Register{
	
	private MovieDatabase db;
	private RegisterView view;	
	
	public controllerDelete(MovieDatabase db, RegisterView view) {	
		
		this.db = db;
		this.view = view;
	}

	public void makeRoute(){
		
		//delete pelo ID		
		get(new Route("/movies/delete/:id") {
			@Override
			public Object handle(Request request, Response response) {
				
				db.delMovie(Integer.valueOf(request.params(":id")));

				return view.jsonResult(response);							     	    
			}
		});	
	
	         
	}

}
