package Recipes;

import java.util.ArrayList;
public class Main {

	
	public static void main(String[] args) {
		ArrayList<String> ing = new ArrayList<String>();
		ing.add("onion");
		ing.add("chicken");
		ing.add("garlic");
		
		/* This code prints the data inside the ing variable in the main class
		for(int i = 0; i < ing.size(); i++) {
			System.out.println(ing.get(i));
		}
		*/
		
		//System.out.println(ing.size());
		
		//Ingredient.setIngredients(ing);
		
		//get and print the data inside the static variable of Ingredients class
//		for(int i = 0; i < ing.size(); i++) {
//			System.out.println(Ingredient.getIngredients().get(i));
//		}
		
		/*	
		try {
			//GET request URL parameters
			Map<String, String> parameters = new HashMap<>();
			
			//test ingredient for now
			parameters.put("q", "chicken");
			//necessary parameters to stay
			parameters.put("app_id", "cf6cdd39");
			parameters.put("app_key", "3b7b32c4423d117221766aec8e28e20f");
			
			
			//Setup URL
			String outputStream = StringifyParameters.stringifyParameters(parameters);

			URL url = new URL("https://api.edamam.com/search?" + outputStream);
			connection = (HttpURLConnection)url.openConnection();
			
			
			//Request setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			
			//Checking status of connection
			int status = connection.getResponseCode();
			System.out.println(status);
			
			//Response Reader
			BufferedReader br = null;
			String resultJSON = "";
			if (connection.getResponseCode() == 200) {
			    br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			    String strCurrentLine;
			        while ((strCurrentLine = br.readLine()) != null) {
			            resultJSON = resultJSON + strCurrentLine;    
			        }
			} else {
			    br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			    String strCurrentLine;
			        while ((strCurrentLine = br.readLine()) != null) {
			               System.out.println(strCurrentLine);
			        }
			}

			
			
			System.out.println(resultJSON);
			
			JSONObject obj = new JSONObject(resultJSON);
			
			JSONArray arr = obj.getJSONArray("hits");
			
			ArrayList<Recipe> arr2 = new ArrayList<Recipe>();
			// arr = [{att1, att2, recipe: {url, name}}...]
			for(int i = 0; i < arr.length() ; i++) {
				JSONObject recipe = arr.getJSONObject(i).getJSONObject("recipe");
				arr2.add(new Recipe(recipe.get("url").toString(), recipe.get("label").toString()));
			}
			System.out.print(arr2);
			
			//ArrayList<Recipe> arr2 = arr.stream().map(o -> new Recipe(o.recipe.name, o.recipe.url)).collect(Collectors.toList());

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
		
		
	}
	
}

