package recipes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//This class is the Recipe class which will instantiate Recipe object references
//
public class Recipe {
	
	private String name;
	//private ArrayList<String> ingredients;
	private String link;
	
	public Recipe(String name, String link) {
		this.name = name;
		//this.ingredients = ingredients;
		this.link = link;
		
	}
	
	//Recipe constructor
	public Recipe(String name, ArrayList<String> ingredients, Queue<String> instructions, String link) {
		this.name = name;
		//this.ingredients = ingredients;
		this.link = link;
		
	}
	
	//potential toString() method
	
}
