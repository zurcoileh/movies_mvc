package br.com.app.client;

public class MovieModel {
	
	private MovieTableModel mtm;
	
	private  Connection connection;		
    
	public MovieModel(Connection conn){
		this.connection = conn;
	}
	
	public MovieTableModel getTableModel(){
		return this.mtm;
	}
	public void sendRequest(String request) {
		getConnection().sendGet(request);
		this.mtm = new MovieTableModel(getConnection().findAllItems());		
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public boolean isEmpty(){		
		if (getTableModel().getRowCount() > 0)
			return false;
		return true;
	}

}
