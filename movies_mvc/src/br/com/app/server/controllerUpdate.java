package br.com.app.server;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;

public class controllerUpdate implements Register{
	
	private MovieDatabase db;
	private RegisterView view;	
	
	public controllerUpdate(MovieDatabase db, RegisterView view) {	
		this.db = db;
		this.view = view;
	}

	public void makeRoute(){
		
		get(new Route("/movies/update/:id/:title/:duration/:year/:gender/:studio/:rating") {
			@Override
			public Object handle(Request request, Response response) {
				
				db.editMovie(new Movie(Integer.valueOf(request.params(":id")), Double.valueOf(request.params(":duration")),
						request.params(":title"), Integer.valueOf(request.params(":year")),
						new Espec(Gender.getValueByString(request.params(":gender")), request.params(":studio")), new Rating(request.params(":rating"))));

				return view.jsonResult(response);
			}
		});
	
	         
	}

}
