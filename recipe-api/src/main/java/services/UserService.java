package services;

import daos.ExternalDAO;
import daos.UserDAO;
import exceptions.InvalidRequestException;
import exceptions.ResourcePersistenceException;
import models.AppUser;
import java.util.regex.*;
import models.Recipe;
import util.ArrayList;

public class UserService {

    private UserDAO userDao;
    private ExternalDAO externalDao;

    public UserService(UserDAO userDao, ExternalDAO externalDao) {

        this.userDao = userDao;
        this.externalDao = externalDao;
    }

    public AppUser register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        if (!userDao.isUsernameAvailable(newUser.getUsername())) {
            throw new ResourcePersistenceException("The provided username is already taken!");
        }

        if (!userDao.isEmailAvailable(newUser.getEmail())) {
            throw new ResourcePersistenceException("The provided email is already taken!");
        }

        return userDao.saveUser(newUser);

    }
    //TODO valid user input
    public boolean isUserValid(AppUser user) {

        boolean check = true;

        if (user == null) return false;
        //Regex expression to check for usernames of length 3-20
        String regexUsername = "^[a-zA-Z0-9]([a-zA-Z0-9._-]){1,18}[a-zA-Z0-9]$";

        //Regex expression to check for passwords of length 8-40 with special characters
        String regexPassword = "^[a-zA-Z0-9.!@?'#$%^&-+=;()+=^._-]{8,40}$";

        //Commented version: Password regex borrowed from https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
        //String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}$";

        //Email regex courtesy of Kyle Plummer
        String regexEmail = "^([0-9a-zA-Z.]+@[0-9a-zA-Z]+[.][a-zA-Z]+){1,40}$";

        String regexName = "^[a-zA-z][a-zA-z,.'-]+$";

        //Builds a String that tells users where their inputs were incorrect
        StringBuilder message = new StringBuilder();
        if(!Pattern.matches(regexUsername, user.getUsername())){
            message.append("Username input was not valid.\n");
            check = false;
        }
        if(!Pattern.matches(regexPassword, user.getPassword())){
            message.append("Password input was not valid.\n");
            check = false;
        }
       if(!Pattern.matches(regexEmail, user.getEmail())){
            message.append("Email input was not valid.\n");
            check = false;
        }
        if(!Pattern.matches(regexName, user.getFirstName())){
            message.append("First name input was not valid.\n");
            check = false;
        }
        if(!Pattern.matches(regexName, user.getLastName())){
            message.append("Last name input was not valid.\n");
            check = false;
        }

        System.err.println(message.toString());

        return check;
    }
    //TODO valid ingredient input
    //called by IngredientScreen to take in ArrayList of ingredients and initiate search process
    public boolean isIngredientValid(String ingredient){
        boolean check = false;
        //String regex = "^([a-zA-Z+]+){3,40}$|^[0]$";
        //accounts for in case the user puts in a space between words
        String regex = "^([a-zA-Z]+){3,40}$|^[0]$";
        if(Pattern.matches(regex, ingredient)){
            check = true;
        }

        if(!check){
            System.out.println("Ingredient input is invalid!");
        }
        return check;
    }



}
