package br.com.app.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.db4o.ObjectSet;

import spark.Response;

public class RegisterView implements Observer{
	
	
	private Register register;
	private ObjectSet<Movie> result;
	
	public void setRegister(Register register){
		this.register = register;
	}
	
	public void routingRegister(){
		register.makeRoute();
	}
	
	public void update(ObjectSet<Movie> result){
		this.result = result;
	}
 
	public JSONArray jsonResult(Response response){

			//allows everyone to access the resource
			response.header("Access-Control-Allow-Origin", "*");

			JSONObject jsonObj = new JSONObject();		
			JSONArray jsonResult = new JSONArray();	
			try {    		
				if (!this.result.isEmpty())    			
					jsonObj.put("result", "sucess");
				else
					jsonObj.put("result", "fail");    		  		
				jsonResult.put(jsonObj);
			} catch (JSONException e) {					
				e.printStackTrace();
			}	     
			return jsonResult;		
	}

}
