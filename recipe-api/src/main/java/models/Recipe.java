package models;

import util.ArrayList;
import java.util.Queue;
//This class is the Recipe class which will instantiate Recipe object references
//
public class Recipe {
	
	private String name;
	//private ArrayList<String> ingredients;
	private String url;

	public Recipe(String name, String url) {
		this.name = name;
		this.url = url;

	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "Recipe " + "\n" +
				"	Name of Recipe: " + name + "\n" +
				"	Link to Recipe: " +  url;
	}
}
