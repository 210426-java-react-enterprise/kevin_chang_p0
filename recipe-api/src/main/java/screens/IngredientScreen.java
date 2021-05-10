package screens;

import daos.ExternalDAO;
import daos.UserDAO;
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
    private ExternalDAO externalDao;

    public IngredientScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router, UserDAO userDao, ExternalDAO externalDao){
        super("IngredientScreen", "/search");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;
        this.userDao = userDao;
        this.externalDao = externalDao;

    }

    @Override
    public void render(){
        ArrayList<String> ingredientArray = new ArrayList<>();
        String ingredient = "";
        System.out.println("Input ingredient names one at a time!");
        System.out.println("Enter \'0\' when you are finished and are ready to begin searching for recipes.");
        System.out.println("+--------------------------------------+");

        try {
            System.out.print("Please input an ingredient: ");
            //trims out extra spaces of inputs and replaces spaces between ingredients with a '+'
            ingredient = consoleReader.readLine().trim().replace(' ', '+');
            while(!userService.isIngredientValid(ingredient)){
                System.out.print("Please input another ingredient or type \'0\' to Finish: ");
                ingredient = consoleReader.readLine().trim().replace(' ', '+');
            }

            while(!ingredient.trim().equals("0") ){
                do {
                    ingredientArray.add(ingredient);

                    //utilizes userService isIngredientValid to check input validity
                    //If valid, it will stop asking for repeated input and move on
                    while (!userService.isIngredientValid(ingredient)) {
                        System.out.print("Please input another ingredient or type \'0\' to Exit: ");
                        ingredient = consoleReader.readLine().trim().replace(' ', '+');
                    }
                }
                while (!ingredient.trim().equals("0"));
            }
            System.out.println("Saving ingredient data...");

            //returns user to Dashboard only if they enter 0 and have not put in anything into the array
            if(ingredient.equals("0") && ingredientArray.size() == 0){
                //returns user to dashboard if they don't enter anything and enter 0
                System.out.println("Returning to Dashboard...");
                router.navigate("/dashboard");

            }


                //all the ingredients are added, persist each ingredient into the database
                //You will then draw from the database to render RecipeScreen
                if(ingredientArray.size() != 0) {

                    int[] ingredientIdArray = userDao.saveIngredients(ingredientArray);

                    System.out.println("Searching for recipes...");
                    ArrayList<Recipe> recipeArray = externalDao.searchRecipe(ingredientArray);
                    if(recipeArray != null){
                        int[] recipeIdArray = userDao.saveRecipes(recipeArray);

                        //Use the int[] arrays returned at this point to construct and persist to relational table: recipe_ingredient_table
                        userDao.persistFKToRecipeIngredientTable(recipeIdArray, ingredientIdArray);

                    }
                }
            System.out.println("All data has been saved. Recipe search complete!");

            } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
