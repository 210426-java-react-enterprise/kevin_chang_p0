package screens;

import daos.UserDAO;
import models.AppUser;
import util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void render() {

        try {
            String username;
            String password;

            System.out.println("Log into your account!");
            System.out.println("+---------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                AppUser authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);
                if (authenticatedUser != null) {
                    System.out.println("Login successful!");
                    router.navigate("/dashboard");

                } else {
                    System.out.println("Login failed!");

                    /*
                        The below code is not necessary, because if the login fails, we will fall
                        out of this method
                     */
                    router.navigate("/welcome");
                }
            } else {
                System.out.println("It looks like you didn't provide any credentials!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}