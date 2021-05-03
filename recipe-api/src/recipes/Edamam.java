package recipes;

import java.io.BufferedReader;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.JSONArray;

public class Edamam {
	
	private static HttpURLConnection connection;
	private static String JSONString = "";
	private static ArrayList<Recipe> recipeArray;
	
	public static ArrayList<Recipe> getRecipe(String q) {
		//GET request URL parameters
		Map<String, String> parameters = new HashMap<>();
		
		//ingredient parameters
		parameters.put("q", q);
		
		//necessary parameters for Edamam
		parameters.put("app_id", "cf6cdd39");
		parameters.put("app_key", "3b7b32c4423d117221766aec8e28e20f");
		
		
		//Setup URL
		String outputStream;
		try {
			//Creates a String from parameters to add to JSON
			outputStream = StringifyParameters.stringifyParameters(parameters);
			
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
		ArrayList<Recipe> arr2 = new ArrayList<Recipe>();
		// arr = [{att1, att2, recipe: {url, name}}...]
		for(int i = 0; i < arr.length() ; i++) {
			JSONObject recipe = arr.getJSONObject(i).getJSONObject("recipe");
			
			//Puts all Recipe data into an ArrayList of Recipe objects
			arr2.add(new Recipe(recipe.get("url").toString(), recipe.get("label").toString()));
		}
//		System.out.print(arr2);
		
		//Sets value of static ArrayList<Recipe> variable for Class access
		recipeArray = arr2;
	} 


	
	public static void main(String[] args) {
		Ingredient ing = new Ingredient();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("chicken");
		arr.add("onion");
		arr.add("potato");
		
		
		ing.setIngredients(arr);
		
		System.out.println(ing.getIngredients());
		System.out.println(ing.concatIng());
		
		ArrayList<Recipe> afk = getRecipe(ing.concatIng());
		System.out.println(afk);
		

	}
}
