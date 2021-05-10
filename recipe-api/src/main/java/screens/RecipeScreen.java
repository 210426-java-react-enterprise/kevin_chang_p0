package screens;

import services.UserService;
import util.ScreenRouter;

import java.io.BufferedReader;

public class RecipeScreen extends Screen{

    private BufferedReader consoleReader;
    private UserService userService;
    private ScreenRouter router;

    public RecipeScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router){
        super("RecipeScreen", "/recipe");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;

    }

    @Override
    public void render(){

    }
}
