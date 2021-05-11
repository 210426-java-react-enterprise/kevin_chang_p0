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

            System.out.println("Username Requirements: \n" +
                    "Must contain a range of 3 - 20 characters.\n" +
                    "Can contain dots (.), Underscores (_) or hyphens (-) \n" +
                    "No white spaces allowed. \n");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.println("Password must contain: \n" +
                    "A range of 8 - 40 characters.\n" +
                    "Can contain special characters \'!@#$%&*()-+=^.\'");

                    /*
                    "At least one digit.\n" +
                    "At least one uppercase letter.\n" +
                    "At least one lower case letter.\n" +
                    "At least one special character such as: \'!@#$%&*()-+=^.\' \n" +
                    "No white spaces. \n");
                    */


            System.out.print("Password: ");
            password = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            AppUser newUser = new AppUser(firstName, lastName, username, password, email);
            userService.register(newUser);

            //System.out.println("Navigating to welcome screen...");

        } catch (InvalidRequestException | ResourcePersistenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }

}
