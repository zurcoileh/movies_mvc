package br.com.app.client;

import javax.swing.JOptionPane;


public class MoviesController implements Controller {
	
	private MovieModel mm;
	private View view;	
	
	public MoviesController(){
		mm = new MovieModel(new Connection().config("localhost", 4567,"movies"));
		view =  new View(this);
		view.createView();
	}

	@Override
	public void sendRequest(String request) {    
	    
		
		if (!request.isEmpty()){
			
			mm.sendRequest("search/key/"+request);
			
		}else{			
			mm.sendRequest("list");			
		}			
	
		
		if (!mm.isEmpty()){			
			
			view.populateTable(mm.getTableModel());			
			
		}else {
			JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
		}
			
			
	}

	
}
