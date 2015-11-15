package br.com.app.client;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Connection{

    private final String USER_AGENT = "Mozilla/5.0";    
    private String url;  
    private JSONArray result;
    
    public  Connection config(String hostname, int port, String tag){ 
    	
    	url = "http://"+hostname+":"+port+"/"+tag+"/";    
    	return this;    	
    }

	// HTTP GET request
    public void sendGet(String request) {

    	String url = this.url+request;

    	URL obj;
    	StringBuffer response=null;
    	try {
    		obj = new URL(url);

    		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    		// optional default is GET
    		con.setRequestMethod("GET");

    		//add request header
    		con.setRequestProperty("User-Agent", USER_AGENT);

    		int responseCode = con.getResponseCode();
    		System.out.println("\nSending 'GET' request to URL : " + url);
    		System.out.println("Response Code : " + responseCode);

    		BufferedReader in = new BufferedReader(
    				new InputStreamReader(con.getInputStream()));
    		String inputLine;
    		response = new StringBuffer();

    		while ((inputLine = in.readLine()) != null) {
    			response.append(inputLine);
    		}
    		in.close();
            
    		result = new JSONArray(response.toString());
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(null,"Erro na conexao com banco!");
    		System.exit(0);
    	}	
    }

    public List<Movie> findAllItems(Connection this) {

        List<Movie> found = new LinkedList<Movie>();

        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject obj = result.getJSONObject(i);
                found.add(new Movie(obj.getInt("id"), obj.getDouble("duration"), 
                		            obj.getString("title"),obj.getInt("year"),
                		            new Espec(Gender.getValueByString(obj.getString("gender")), 
                		            		        obj.getString("studio")),new Rating("0")));                        		             	
            }
        } catch (JSONException e) {
        	System.out.println(e);
        }
        return found;
    }


}