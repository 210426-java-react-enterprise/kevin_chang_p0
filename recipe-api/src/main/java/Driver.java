import daos.ExternalDAO;
import recipes.Ingredient;
import recipes.Recipe;
import util.ArrayList;

import java.io.UnsupportedEncodingException;

public class Driver {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Ingredient ing = new Ingredient();
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("chicken");
        arr.add("onion");
        arr.add("potato");

        ing.setIngredients(arr);

        System.out.println(ing.getIngredients());
        System.out.println(ing.concatIng());

        String outputStream = ExternalDAO.getOutputStream(ing.concatIng(), "cf6cdd39", "3b7b32c4423d117221766aec8e28e20f");
        ArrayList<Recipe> afk = ExternalDAO.getRecipe(outputStream);
        System.out.println(afk.size());
        System.out.println(afk.get(1));
    }
}
