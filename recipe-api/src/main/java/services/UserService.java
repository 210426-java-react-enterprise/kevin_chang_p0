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
        //use regex to check valid username, password, email, first name, and last name
        boolean check = false;

        if (user == null) return check;
        //Username regex borrowed from https://mkyong.com/regular-expressions/how-to-validate-username-with-regular-expression/
        String regexUsername = " ^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        //Password regex borrowed from https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}$";
        //Email regex borrowed from Kyle Plummer
        String regexEmail = "^([0-9a-zA-Z.]+@[0-9a-zA-Z]+[.][a-zA-Z]+){1,40}$";

        String regexName = "^[a-zA-z][a-zA-z,.'-]+$";

        if(Pattern.matches(regexUsername, user.getUsername()) &&
            Pattern.matches(regexPassword, user.getPassword()) &&
            Pattern.matches(regexEmail, user.getEmail()) &&
            Pattern.matches(regexName, user.getFirstName()) &&
            Pattern.matches(regexName, user.getLastName())){
            check = true;
        }

        return check;
    }
    //TODO valid ingredient input
    //called by IngredientScreen to take in ArrayList of ingredients and initiate search process
    public boolean isIngredientValid(String ingredient){
        boolean check = false;
        String regex = "^([a-zA-Z]+){3,40}$|^[0]$";

        if(Pattern.matches(regex, ingredient)){
            check = true;
        }

        return check;
    }



}
