package test;

import daos.ExternalDAO;
import daos.UserDAO;
import models.AppUser;
import services.UserService;

import org.junit.*;
import util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService sut;
    private UserDAO mockUserDao;
    private ExternalDAO mockExternalDao;

    @Before
    public void setUp(){
        mockUserDao = mock(UserDAO.class);
        mockExternalDao = mock(ExternalDAO.class);

        sut = new UserService(mockUserDao,mockExternalDao);
    }

    @After
    public void tearDown(){
        sut = null;
        mockUserDao = null;
        mockExternalDao = null;
    }

    //Tests if the isUserValid() method in UserService working
    @Test
    public void test_IsUserValidWithInvalidCredentials() {
        //test invalid usernames
        //username too short
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                , "Chang", "sw"
                , "password123", "kevin.chang@revature.net")));
        //username starts with a special character (._-)
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","_123swek"
                ,"password123","kevin.chang@revature.net")));
        //username uses an invalid special character
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","123?swekevin"
                ,"password123","kevin.chang@revature.net")));
        //is empty
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang",""
                ,"password123","kevin.chang@revature.net")));


        //test invalid password
        //password too short
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"pass12","kevin.chang@revature.net")));
        //uses an invalid special character
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"password:123","kevin.chang@revature.net")));
        //is empty
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"","kevin.chang@revature.net")));


        //test invalid email
        //contains no @
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"password123","kevin.changrevature.net")));
        //contains no . at revature.net
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"password123","kevin.chang@revaturenet")));
        //contains illegal special character
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"password123","kevin?chang@revature.net")));
        //contains nothing between @ and .
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"password123","kevin.chang@.net")));
        //contains nothing before @
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"password123","@.net")));
        //is empty
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"password123","")));


        //test invalid firstName
        //is empty
        assertFalse(sut.isUserValid(new AppUser(""
                ,"Chang","swekevin"
                ,"password123","kevin.chang@revature.net")));
        //contains illegal numbers
        assertFalse(sut.isUserValid(new AppUser("123Kev"
                ,"Chang","swekevin"
                ,"password123","kevin.chang@revature.net")));


        //test invalid lastName
        //is empty
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"","swekevin"
                ,"password123","kevin.chang@revature.net")));
        //illegal numbers
        assertFalse(sut.isUserValid(new AppUser("Kevin"
                ,"Chan21g","swekevin"
                ,"password123","kevin.chang@revature.net")));
    }


    @Test
    public void test_registerIsUserValidWithValidCredentials(){
        //Assert
        //test valid AppUser
        assertTrue(sut.isUserValid(new AppUser("Kevin"
                ,"Chang","swekevin"
                ,"password123","kevin.chang@revature.net")));
    }

    //Tests if the register() method works when valid and unique inputs are entered
    @Test
    public void test_registerValidUserUsernamePassword(){
        //Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(any(), anyString())).thenReturn(true);

        //Act
        sut.register(new AppUser("Avi", "Goodman","avigoodman","password123", "avi.goodman@revature.net"));

        //Assert
        verify(mockUserDao, times(1)).isUsernameAvailable(any(), anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(any(), anyString());
        verify(mockUserDao, times(1)).saveUser(any(), any());
    }

    @Test
    public void test_IsIngredientValidWithValidIngredients(){
        //Arrange
        ArrayList<String> ingr = new ArrayList(){};
        ingr.add("chicken");
        ingr.add("potatoes");
        ingr.add("beef");

        //Assert
        for (int i = 0; i < ingr.size(); i++) {
            assertTrue(sut.isIngredientValid(ingr.get(i)));
        }

    }

    @Test
    public void test_IsIngredientValidWithInvalidIngredients(){
        //Assert
        assertFalse(sut.isIngredientValid("chicken1"));
        assertFalse(sut.isIngredientValid("?chicken"));
        assertFalse(sut.isIngredientValid("chicken "));
        assertFalse(sut.isIngredientValid("chicken d"));
        assertFalse(sut.isIngredientValid("chicken!"));
        assertFalse(sut.isIngredientValid("chic.ken"));

    }


}
