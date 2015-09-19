package br.com.app.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import spark.Response;

import com.db4o.ObjectSet;

public class SearchView implements Observer {
	
	private Search search;
	private ObjectSet<Movie> result;
	
	public void setSearch(Search key){
		this.search = key;
	}
	
	public void routingSearch(){
		search.makeRoute();
	}
	
	public void update(ObjectSet<Movie> result){
		this.result = result;
	}

	public JSONArray generateJSON(Response response) throws JSONException{
		JSONArray jsonResult = new JSONArray();
 	  //  JSONObject jsonObj = new JSONObject(); 
		response.header("Access-Control-Allow-Origin", "*");
 
 	 		for(Movie mv: this.result){ 	    
 	 			JSONObject jsonObj = new JSONObject();
 	 			try {
 	 				jsonObj.put("id", mv.getIdMovie());
 	 				jsonObj.put("title", mv.getTitle());
 	 				jsonObj.put("duration", mv.getDuration());
 	 				jsonObj.put("year", mv.getYear());
 	 				jsonObj.put("gender", mv.getEsp().getGender());
 	 				jsonObj.put("studio", mv.getEsp().getStudio());
 	 				jsonObj.put("rating", mv.getRating().getRate());
 	 				jsonResult.put(jsonObj);				
 	 				
 	 						
 	 			} catch (JSONException e) {				
 	 				e.printStackTrace();
 	 			} 	    	
 	 		}    	
 	    
 	 		return jsonResult;
	}

}
