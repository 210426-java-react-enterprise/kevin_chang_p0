package screens;

import services.UserService;
import util.ScreenRouter;

import java.io.BufferedReader;

public class IngredientScreen extends Screen {

    private UserService userService;
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public IngredientScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router){
        super("IngredientScreen", "/input");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;

    }

    @Override
    //public void render(){

    }
}
