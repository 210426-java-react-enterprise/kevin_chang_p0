package drivers;

import util.AppState;

public class Driver {

    private static AppState app = new AppState();

    public static void main(String[] args) {
        app.startup();

    }

    public static AppState app() {
        return app;
    }


}




