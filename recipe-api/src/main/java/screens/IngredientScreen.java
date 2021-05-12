package screens;

import daos.ExternalDAO;
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
        String ingredient = "placeholder";
        System.out.println("Input ingredient names one at a time!");
        System.out.println("Enter \'0\' when you are finished and are ready to begin searching for recipes.");
        System.out.println("+---------------------------------------------------------------------------------+");

        //Keeps check on while loop until the system needs to exit
        boolean check = true;
        try {
            while(check){
                System.out.print("Please input an ingredient or type \'0\' to Finish: ");
                //trims out extra spaces of inputs
                ingredient = consoleReader.readLine().trim();

                //adds to array only if the user did not enter 0
                if(userService.isIngredientValid(ingredient) && !ingredient.trim().equals("0")){
                    ingredientArray.add(ingredient);
                }
                else if (ingredient.trim().equals("0")){
                    check = false;
                }
                else{
                    System.out.println("Invalid input! Please try again.");
                }
            }

            //returns user to Dashboard only if they enter 0 and have not put in anything into the array
            if(ingredient.equals("0") && ingredientArray.size() == 0){
                //returns user to dashboard if they don't enter anything and enter 0
                System.out.println("Returning to Dashboard...");
                router.navigate("/dashboard");

            }


                //all the ingredients are added, persist each ingredient into the database
                //You will then draw from the database to render RecipeScreen
                if(ingredientArray.size() != 0) {
                    //Prompting that the flow is continuing
                    System.out.println("Saving ingredient data...");

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

           //Shut down the application
            Driver.app().setAppRunning(false);

            } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
