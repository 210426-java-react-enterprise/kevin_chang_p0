package recipes;

import daos.ExternalDAO;
import util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        Ingredient ing = new Ingredient();
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("chicken");
        arr.add("onion");
        arr.add("potato");

        ing.setIngredients(arr);

        System.out.println(ing.getIngredients());
        System.out.println(ing.concatIng());

        ArrayList<Recipe> afk = ExternalDAO.getRecipe(ing.concatIng());
        System.out.println(afk.size());
        System.out.println(afk.get(1));
    }
}
