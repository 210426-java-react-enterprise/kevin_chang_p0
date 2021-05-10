package screens;

import exceptions.InvalidRequestException;
import exceptions.ResourcePersistenceException;
import models.AppUser;
import services.UserService;
import util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private UserService userService;
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public RegisterScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router) {
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.userService = userService;
    }

    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        int age;

        // ok but a little verbose
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(inputStreamReader);

        try {
            // risky code that might throw an exception

            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            System.out.print("First name: ");
            firstName = consoleReader.readLine(); // throws Exception here

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            System.out.print("Age: ");
            age = Integer.parseInt(consoleReader.readLine());

            AppUser newUser = new AppUser(firstName, lastName, username, password, email);
            userService.register(newUser);

            System.out.println("Navigating to welcome screen...");
            router.navigate("/welcome");

        } catch (InvalidRequestException | ResourcePersistenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }

}
