package screens;

import drivers.Driver;
import util.ScreenRouter;

import java.io.BufferedReader;

//contains roouters to IngredientScreen, ExistingFavoritesScreen
public class DashboardScreen extends Screen{

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router){
        super("DashboardScreen","/dashboard");
        this.consoleReader = consoleReader;
        this.router = router;

    }
    @Override
    public void render(){
        System.out.println("-------------Dashboard-------------");
        System.out.println("1) Search for Recipes");
        System.out.println("2) View Favorites");
        System.out.println("3) Exit application");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to recipe search screen... \n\n\n");
                    router.navigate("/search");
                    break;
                case "2":
                    System.out.println(ANSI_RED +"This feature has not been implemented yet." + ANSI_RESET);
                    //TODO eventually this println should say to navigate to favorites screen...
                    System.out.println("Navigating back to dashboard...\n\n\n");
                    //TODO Implement a recipe favoriting system in future versions
                    router.navigate("/dashboard");
                    break;
                case "3":
                    System.out.println("Exiting application!");
                    Driver.app().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid selection!");
                    Driver.app().getRouter().navigate("/dashboard");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
