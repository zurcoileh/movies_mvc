package br.com.app.client;

public class Client {
	
	public static void main(String[] args) {
		
		Library lib = new Library();		
		
		lib.setConnection(new Connection().config("localhost", 4567,"movies"));
	
	    lib.sendRequest("list");
	    lib.printResult();
	    	
	    lib.sendRequest("search/id/109");
		lib.printResult();
	}  

}
