package util;

import daos.ExternalDAO;
import daos.UserDAO;
import screens.*;
import services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("Initializing application...");

        appRunning = true;

        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        final UserDAO userDao = new UserDAO();
        final ExternalDAO externalDao = new ExternalDAO();
        final UserService userService = new UserService(userDao, externalDao);
        router = new ScreenRouter();

        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader, router))
                .addScreen(new RegisterScreen(consoleReader, userService, router))
                .addScreen(new DashboardScreen(consoleReader, router))
                .addScreen(new IngredientScreen(consoleReader, userService, router, userDao, externalDao))
                .addScreen(new FavoritesScreen(consoleReader, router))
                .addScreen(new RecipeScreen(consoleReader, userService, router));
        System.out.println("Application initialized!");
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

}
