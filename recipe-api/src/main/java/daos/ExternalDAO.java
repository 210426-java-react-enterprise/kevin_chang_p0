package daos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import recipes.Recipe;
import util.ArrayList;

import java.net.URLEncoder;

import org.json.JSONObject;
import org.json.JSONArray;

public class ExternalDAO {
	
	private static HttpURLConnection connection;
	private static String JSONString = "";
	private static ArrayList<Recipe> recipeArray;

	//makes the inputted data into a readable URL to be passed in the GET request
	//q is the concatenated Strings of ingredients being searched for
	public static String getOutputStream(String q, String app_id, String app_key) throws UnsupportedEncodingException{
		StringBuilder str = new StringBuilder();
			str.append(URLEncoder.encode("q", "UTF-8"));
			str.append("=");
			str.append(URLEncoder.encode(q, "UTF-8"));
			str.append("&");
			str.append(URLEncoder.encode("app_id", "UTF-8"));
			str.append("=");
			str.append(URLEncoder.encode(app_id, "UTF-8"));
			str.append("&");
			str.append(URLEncoder.encode("app_key", "UTF-8"));
			str.append("=");
			str.append(URLEncoder.encode(app_key, "UTF-8"));
			str.append("&");

		String strResult = str.toString();
		return strResult;
	}

	public static ArrayList<Recipe> getRecipe(String outputStream) {

		//Setting up the URL connection
		try {
			//Adds outputStream JSON parameters to search URL and opens connection object
			URL url = new URL("https://api.edamam.com/search?" + outputStream);
			connection = (HttpURLConnection)url.openConnection();
			
			
			//GET Request
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			
			//Checking status of connection
//			int status = connection.getResponseCode();
//			System.out.println(status);
			
			//Allows you to read the value of a connection's header
//			String connectionHeaderValue = connection.getHeaderField("Content-Type");
//			System.out.println(connectionHeaderValue);	
			
			//Runs method to read the InputStream response from the API and retrieve JSONString static variable
			read();
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return recipeArray;
	}


	
	private static void read() {
		
		//Response Reader - Reads the InputStream response from the API
		BufferedReader br = null;
		try {
			if (connection.getResponseCode() == 200) {
			    br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			    String strCurrentLine;
			        while ((strCurrentLine = br.readLine()) != null) {
			        	//Concatenates read lines into JSONString static variable
			            JSONString = JSONString + strCurrentLine;    
			        }
			} else {
			    br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			    String strCurrentLine;
			        while ((strCurrentLine = br.readLine()) != null) {
			               System.out.println(strCurrentLine);
			        }
			}
			
			//Runs method to convert JSONString into an Array of Recipe objects
			getJSONToRecipeArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
//		finally {System.out.println(JSONString);}

	}
	
	private static void getJSONToRecipeArray() {
		//instantiate JSONObject given JSONString
		JSONObject obj = new JSONObject(JSONString);
		
		//Parse out JSONArray "hits" from the JSONObject
		JSONArray arr = obj.getJSONArray("hits");
		
		//Parse out API's Recipe object and convert to recipe-api's Recipe object
		ArrayList<Recipe> arr2 = new ArrayList<>();

		// arr = [{att1, att2, recipe: {url, name}}...]
		for(int i = 0; i < arr.length() ; i++) {
			JSONObject recipe = arr.getJSONObject(i).getJSONObject("recipe");

			//Puts all Recipe data into an ArrayList of Recipe object
			arr2.add(new Recipe(recipe.get("label").toString(), recipe.get("url").toString()));
		}
		
		//Sets value of static ArrayList<Recipe> variable for Class access
		recipeArray = arr2;
	} 

}
