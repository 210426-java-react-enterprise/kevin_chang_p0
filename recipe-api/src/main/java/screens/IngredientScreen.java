package screens;

import daos.UserDAO;
import drivers.Driver;
import models.Recipe;
import services.UserService;
import util.ArrayList;
import util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class IngredientScreen extends Screen {

    private UserService userService;
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDao;

    public IngredientScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router, UserDAO userDao){
        super("IngredientScreen", "/search");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;
        this.userDao = userDao;

    }

    @Override
    public void render(){
        ArrayList<String> ingredientArray = new ArrayList<>();
        String ingredient = "";
        System.out.println("Input ingredient names one at a time!");
        System.out.println("Enter \'Done\' to begin search.");
        System.out.println("+--------------------------------------+");

        try {
            System.out.print("Please input an ingredient: ");
            //trims out extra spaces of inputs and replaces spaces between ingredients with a '+'
            ingredient = consoleReader.readLine().trim().replace(' ', '+');

            if(ingredient.equalsIgnoreCase("DONE")){
                //returns user to dashboard if they don't enter anything and put done
                System.out.println("Returning to Dashboard...");
                router.navigate("/dashboard");

            } else {
                while (!ingredient.equalsIgnoreCase("DONE")) {
                    ingredientArray.add(ingredient);
                    System.out.print("Please input another ingredient or type \'Done\': ");
                    //TODO Implement a UserService that checks if this input is okay first before saving it
                    ingredient = consoleReader.readLine().trim().replace(' ', '+');


                }
                //all the ingredients are added, persist each ingredient into the database
                //You will then draw from the database to render RecipeScreen
                if(ingredientArray != null) {

                    int[] ingredientIdArray = userDao.saveIngredients(ingredientArray);
                    ArrayList<Recipe> recipeArray = userService.validateSearch(ingredientArray);
                    if(recipeArray != null){
                        int[] recipeIdArray = userDao.saveRecipes(recipeArray);
                        //TODO make sure when implementing these methods to also persist to recipe_ingredient_table
                        //Use the int[] arrays returned at this point to construct and persist to relational table
                    }
                }
                //implement in UserDAO a method for persisting recipeArray as well as ingredientsArray
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
