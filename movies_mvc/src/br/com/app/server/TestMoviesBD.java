package br.com.app.server;

import org.json.JSONException;

public class TestMoviesBD {
	
	static MovieDatabase db = new MovieDatabase();

	public static void main(String[] args) throws JSONException {
		
		initializeStore();
		//View to search the database
		SearchView view  = new SearchView();
		//View to insert, remove, update data
		RegisterView cadview = new RegisterView();			

		//Create controllers for searching
		//Primeiro controlador assume controle da view
		view.setSearch(new controllerGet(db, view));		
		view.routingSearch();
		//Segundo controlador assume controle da view
		view.setSearch(new controllerGetId(db, view));
		view.routingSearch();
		//Terceiro controlador assume controle da view
		view.setSearch( new controllerGetKey(db, view));
		view.routingSearch();
		//Quarto controlador assume controle da view
		view.setSearch(new controllerSearchGender(db,view));
		view.routingSearch();
		//Quinto controlador assume controle da view
		view.setSearch(new controllerSearchStudio(db,view));
		view.routingSearch();
		
		//Create controllers to inerst, delete and update
		//Controlador adicionar assume controle
		cadview.setRegister(new controllerAdd(db,cadview));
		cadview.routingRegister();
		//Controlador update assume controle
		cadview.setRegister(new controllerUpdate(db,cadview));
		cadview.routingRegister();
		//controlador delete assume controle
		cadview.setRegister(new controllerDelete(db,cadview));
		cadview.routingRegister();
		
		db.registerObserver(view);	
		db.registerObserver(cadview);

	}
	
	public static void initializeStore(){

		db.addMovie(new Movie(103,180, "interstellar", 2014, new Espec(Gender.fiction, "warnner"),new Rating("0")));
		db.addMovie(new Movie(105,180, "night at the museum", 2015,new Espec(Gender.adventure, "warnner"),new Rating("0")));
		db.addMovie(new Movie(107,150, "300", 2013,new Espec(Gender.action, "universal"),new Rating("0")));
		db.addMovie(new Movie(109,150, "iroman", 2012,new Espec(Gender.action, "marvel"),new Rating("0")));
		db.addMovie(new Movie(111,180, "birdman", 2015,new Espec(Gender.drama, "warnner"),new Rating("0")));
		db.addMovie(new Movie(113,180, "american sniper", 2015,new Espec(Gender.action, "mgm"),new Rating("0")));
		db.addMovie(new Movie(115,150, "12 years a slave", 2012,new Espec(Gender.drama, "paramount"),new Rating("0")));
		db.addMovie(new Movie(118,150, "the fault in our stars", 2013,new Espec(Gender.romance, "mgm"),new Rating("0")));			
		db.addMovie(new Movie(122,90, "padington", 2014,new Espec(Gender.animation, "pixar"),new Rating("90"))); 

	}

}
