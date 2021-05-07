package recipes;

import util.ArrayList;

public class Ingredient {
	private static ArrayList<String> ing;
	
	public void setIngredients(ArrayList<String> userInput) {
		ing = userInput;
	}
	
	public ArrayList<String> getIngredients() {	
		return ing;
	}
	
	public static String concatIng() {
		if (ing.size() > 0) {
			String result = ing.get(0);
			for(int i = 1; i < ing.size(); i++) {
				result = result + "+" + ing.get(i);
			}
			
			return result;
		}
		else {
			return "";
		}
	}
	
	
}
