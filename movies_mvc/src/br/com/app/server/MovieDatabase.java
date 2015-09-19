package br.com.app.server;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Constraint;
import com.db4o.query.Query;


public class MovieDatabase implements Subject {	

	//ObjectContainer movies = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../movies.db4o");	

	ObjectContainer movies;	
	private List<Observer> observers; 

	public MovieDatabase(){	

		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();			
		conf.common().objectClass(Movie.class).cascadeOnUpdate(true);
		conf.common().objectClass(Movie.class).cascadeOnDelete(true);
		movies = Db4oEmbedded.openFile(conf, "../moviesbdestrategia/database/movies.db4o");
		
		observers = new LinkedList<Observer>();
		
	}	
	
	//APLICANDO MVC OBSERVER MODULE
	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);		
	}	
	
	@Override
	public void notifyObservers(ObjectSet<Movie> result) {
		// TODO Auto-generated method stub
		for(Observer o : observers){
			o.update(result);
		}
		
	}	
	
	public ObjectSet<Movie> getMovie(int id){
		Query query=movies.query();
		query.descend("idMovie").constrain(id);
		ObjectSet<Movie> result = query.execute();		
		return result;		
	}


	//adiciona o filme no banco
	public void addMovie(Movie mv){

		ObjectSet<Movie> result = getMovie(mv.getIdMovie());		
		if (result.isEmpty()){
			movies.store(mv);
			movies.commit();
			notifyObservers(getMovie(mv.getIdMovie()));
		}	
		
	}
	
	public ObjectSet<Movie> getResult(Query query){
		return query.execute();
	}
	
		//list movies
	public  void listMovies() {		
		Query query=movies.query();
		query.constrain(Movie.class);
		query.descend("title").orderAscending();
		ObjectSet<Movie> res = query.execute();		
		notifyObservers(res);
		
	}
	
	//busca por gender
	public void searchGender(Gender gender){

		Query query=movies.query();
		query.descend("esp").descend("gender").constrain(gender);
		notifyObservers(getResult(query));	
	}
	
	//busca por studio
	public void searchStudio(String studio){

		Query query=movies.query();
		query.descend("esp").descend("studio").constrain(studio);
		notifyObservers(getResult(query));	
	}

	//busca pelo id do filme	
	public void searchId(int id){	

		Query query=movies.query();
		query.descend("idMovie").constrain(id);
		notifyObservers(getResult(query));
	}

	//metodo deletar do banco
	public void delMovie(int id){
		
		Query query=movies.query();
		query.descend("idMovie").constrain(id);

		ObjectSet<Movie> result = getResult(query);

		if(!result.isEmpty()){
			Movie mv = result.next();
			movies.delete(mv);
			movies.commit();			
		}

		notifyObservers(result);
	}

	//busca por palavra chave
	public void searchKeyword(String keyword) {		   

		Query query=movies.query();
		query.constrain(Movie.class);
		//Atributos da busca
		Constraint constr=query.descend("rating").descend("rate").constrain(keyword);
		Constraint constr1=query.descend("esp").descend("studio").constrain(keyword);
		Constraint constr2=query.descend("esp").descend("gender").constrain(Gender.getValueByString(keyword));
		Constraint constr3=query.descend("year").constrain(integerValue(keyword));
		Constraint constr4=query.descend("duration").constrain(integerValue(keyword));

		query.descend("title").constrain(keyword.replaceAll("%20"," ")).like().or(constr).or(constr1).or(constr2).or(constr3).or(constr4);		

		notifyObservers(getResult(query));		

	}
	//adiciona nota ao filme e atualiza o rating (percentual)
	public boolean addGrade(int id, double grade){

		ObjectSet<Movie> result = getMovie(id);

		if(!result.isEmpty()){
			Movie found = result.next();
			double newgrade;
			found.getRating().setQtVotes(found.getRating().getQtVotes() + 1);
			found.getRating().setGrade((found.getRating().getGrade() + grade ));
			newgrade =  found.getRating().getGrade()/found.getRating().getQtVotes();						
			found.getRating().setRate(Math.round(newgrade) + "");
			movies.store(found);
			movies.commit();
			return true;
		}
		return false;
	}	
	
	//atualiza os campos	
	public void editMovie(Movie mv){

		ObjectSet<Movie> result = getMovie(mv.getIdMovie());

		String title = mv.getTitle().replaceAll("%20"," ");

		if(!result.isEmpty()){
			Movie found = result.next();
			found.setDuration(mv.getDuration());
			found.setTitle(title);
			found.setYear(mv.getYear());
			found.setEsp(mv.getEsp());
			found.setRating(mv.getRating());	
			movies.store(found);
			movies.commit();			
			notifyObservers(getMovie(found.getIdMovie()));
		}
			
	}	
	
	
	//retorna ultimo index da tabela
	public int getLastIndex(){
		int last = 0;
		Query  query = movies.query();
		query.constrain(Movie.class);
		query.descend("idMovie").orderDescending();
		ObjectSet<Movie> result = query.execute();			
		if (!result.isEmpty()){
			Movie mv = result.next();
			last = mv.getIdMovie();
		}
		return last + 1;
	}

	
	public int integerValue(String s){		
		int num;
		try{
			num = Integer.parseInt(s);
			// is an integer!
		} catch (NumberFormatException e) {
			// not an integer!
			num = 0;
		}
		return num;
	}	
	

}
