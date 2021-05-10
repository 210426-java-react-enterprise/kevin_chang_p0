package services;

import daos.ExternalDAO;
import daos.UserDAO;
import exceptions.InvalidRequestException;
import exceptions.ResourcePersistenceException;
import models.AppUser;
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

        //TODO Check if the inputs are also VALID inputs
        return userDao.saveUser(newUser);

    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().length() > 20) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 255) return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getEmail().length() > 255) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getLastName().length() > 25) return false;

        return true;
    }

    //called by IngredientScreen to take in ArrayList of ingredients and initiate search process
    public boolean isIngredientValid(String ingredient){

        //TODO Implement checks to make sure that there are proper Ingredient inputs
        ////This Service should not allow numbers other than 0


        return true;
    }

}
