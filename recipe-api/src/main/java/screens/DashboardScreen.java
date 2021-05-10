package screens;

import drivers.Driver;
import util.ScreenRouter;

import java.io.BufferedReader;

//contains roouters to IngredientScreen, ExistingFavoritesScreen
public class DashboardScreen extends Screen{

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
                    System.out.println("Navigating to recipe search screen...");
                    router.navigate("/search");
                    break;
                case "2":
                    System.out.println("Navigating to favorites screen...");
                    router.navigate("/favorite");
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
