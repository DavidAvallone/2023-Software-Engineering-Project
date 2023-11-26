package controller.service;

import model.dao.UserDAO;
import model.entity.User;
import util.PasswordUtil;
import controller.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*; //Mock Framework
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    @Test
    public void testRegisterUser() {
        //Setup Data & Expected Return
        User registered = new User(101, "name", "test@test.com", PasswordUtil.hash("123456"), User.NORMAL_PERMISSION);
        User newUser = new User(null, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);

        //We need to use Mocks to test the controller layer
        UserDAO mockDAO = mock(UserDAO.class);
        when(mockDAO.create(any(User.class))).thenReturn(registered);
        UserService.setDAO(mockDAO);

        //Method Under Test
        User returned = UserService.registerUser(newUser);

        //Assertions
        assertAll("Register User Service Assertions",
                () -> assertEquals(returned.getID(), registered.getID(), "Registered ID should be 101"),
                () -> assertEquals(returned.getLogin(), registered.getLogin(), "Registered Login should be test@test.com")
        );
    }

    @Test
    public void testRegisteredUserCatchingException() {
        User newUser = new User();
        newUser.setPassword("123");

        UserDAO mockDAO = mock(UserDAO.class);
        when(mockDAO.create(any(User.class))).thenThrow(new javax.persistence.PersistenceException("Test Exception"));
        UserService.setDAO(mockDAO);

        User returned = UserService.registerUser(newUser);

        assertNull(returned, "Returned user should be null if Login exists (exception).");
    }

    @Test
    public void testLoginSuccessful() {
        //Setup Data & Expected returns
        String email = "test@test.com";
        String unhashed = "654321";
        String hashed = PasswordUtil.hash(unhashed);
        User answer = new User(101, "name", email, hashed, User.NORMAL_PERMISSION);

        UserDAO mockDAO = mock(UserDAO.class);

        when(mockDAO.findUserByLogin(anyString())).thenReturn(answer);
        UserService.setDAO(mockDAO);

        //Method Under Test
        User logged = UserService.loginUser(email, unhashed);

        assertAll("Login User Service assertion",
                () -> assertNotNull(logged, "Logged user cannot be null"),
                () -> assertEquals(logged.getID(), answer.getID())
        );
    }

    @Test
    public void testLoginFailurePasswordDotNotMatch() {
        //Setup Data & Expected returns
        String email = "test@test.com";
        String incorrectPass = "654321";
        String hashed = PasswordUtil.hash("123456");
        User answer = new User(101, "name", email, hashed, User.NORMAL_PERMISSION);

        UserDAO mockDAO = mock(UserDAO.class);

        when(mockDAO.findUserByLogin(anyString())).thenReturn(answer);
        UserService.setDAO(mockDAO);

        //Method Under Test
        User logged = UserService.loginUser(email, incorrectPass);

        //Assertions (Finally)
        assertNull(logged, "Logged must be null if password is incorrect");
    }

    @Test
    public void smokeTestDeleteUser() {
        //We need to use Mocks to test the controller layer
        UserDAO mockDAO = mock(UserDAO.class);

        UserService.setDAO(mockDAO);

        assertDoesNotThrow(() -> UserService.deleteUser(1));
    }
    @Test
    public void testFindUserByName(){
        User u = new User(10, "name", "login", "password", User.NORMAL_PERMISSION);
        UserDAO mockDAO = mock(UserDAO.class);
        when(mockDAO.findUserByName(anyString())).thenReturn(u);
        UserService.setDAO(mockDAO);
        User found = UserService.findUserByName("name");

        assertAll(
                () -> assertEquals(found.getID(), u.getID()),
                () -> assertEquals(found.getUsername(), u.getUsername())
        );
    }
}