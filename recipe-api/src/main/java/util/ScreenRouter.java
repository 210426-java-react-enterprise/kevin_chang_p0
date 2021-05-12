package util;

import exceptions.InvalidRouteException;
import screens.Screen;

public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();
    private Screen currentScreen;

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        currentScreen = screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(() -> new InvalidRouteException("Invalid route!"));
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }
}