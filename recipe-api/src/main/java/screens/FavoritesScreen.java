package screens;

import util.ScreenRouter;

import java.io.BufferedReader;

public class FavoritesScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public FavoritesScreen(BufferedReader consoleReader, ScreenRouter router){
        super("FavoritesScreen", "/favorite");
        this.consoleReader = consoleReader;
        this.router = router;
    }
    @Override
    public void render(){

    }
}
