package model.dao;

import model.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    public static UserDAO dao = null;

    /***
     * To facilitate tests, this method create a new User Entity object
     *
     * @return User with ID=null, login="test@test.com", password="111",
     */
    public static User createNewUserEntity(){
        User u = new User();
        u.setLogin("test@test.com");
        u.setPassword("111");
        u.setPermission(User.NORMAL_PERMISSION);
        return u;
    }

    @BeforeAll public static void createDao(){
        dao = new UserDAO();
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
    }

    @BeforeEach public void deleteAll(){
        dao.deleteAll();
    }

    @AfterAll public static void deleteAllAfter(){
        dao.deleteAll();
    }

    @Test public void testCreateUser(){
        User newuser = createNewUserEntity();

        dao.create(newuser);
        User found = dao.read(newuser.getID());
        assertAll("Grouped Assertions of Create User",
                () -> assertNotNull(newuser.getID(), "ID should not be null after creation"),
                () -> assertNotNull(found, "Found user after reading should not be null"),
                () -> assertEquals(newuser.getLogin(), found.getLogin(), "Login for the newuser must be equal to the Login for the found user"),
                () -> assertEquals(newuser.getPermissionAsString(),found.getPermissionAsString())
        );
    }

    @Test public void testDeleteUserById(){
        User newuser = createNewUserEntity();
        dao.create(newuser);
        dao.delete(newuser.getID());
        User found = dao.read(newuser);
        assertNull(found, "User cannot be in the DB after deletion");
    }

    @Test public void smokeTestDeleteWhatDoesNotExists(){
        //Smoke test has no assertion, we are only testing if this does not raise any exceptions
        User notsaved = createNewUserEntity();
        assertDoesNotThrow(() -> dao.delete(notsaved));
    }

    @Test public void testUpdateUser(){
        User newuser = createNewUserEntity();
        String newlogin = "Altered";
        dao.create(newuser);
        newuser.setLogin(newlogin);
        User updated = dao.update(newuser);
        User found = dao.read(newuser);
        assertAll("Grouped Assertions of Updated User",
                () -> assertEquals(updated.getLogin(),newlogin),
                () -> assertEquals(updated.getID(), found.getID()),
                () -> assertEquals(updated.getLogin(),found.getLogin()),
                () -> assertEquals(updated.getPermission(),found.getPermission())
        );
    }

    @Test public void testList(){
        User u1 = createNewUserEntity();
        User u2 = createNewUserEntity();
        User u3 = createNewUserEntity();
        u1.setEmail("ZZ");
        u2.setEmail("LL");
        u3.setEmail("AA");
        dao.create(u1);
        dao.create(u2);
        dao.create(u3);
        java.util.List<User> lstUser = dao.list("login");
        assertAll("Grouped Assertions for List User",
                () -> assertEquals(lstUser.size(), 3),
                () -> assertEquals(lstUser.get(0).getLogin(),"AA"),
                () -> assertEquals(lstUser.get(2).getEmail(),"ZZ")
        );
    }

    @Test
    public void testCreateUniqueConstraintLogin(){
        User user1 = createNewUserEntity();
        dao.create(user1);

        User user2 = createNewUserEntity();
        assertThrows(javax.persistence.PersistenceException.class,
                () -> dao.create(user2),
                "Login is a unique field in the DB, cannot have repeated login saved on create."
        );//Should not allow to create two user with the same Login
    }

    @Test
    public void testUpdateUniqueConstraintLogin(){
        User user1 = createNewUserEntity();
        dao.create(user1);

        User user2 = createNewUserEntity();
        user2.setLogin("different@test.com");
        dao.create(user2);

        user2.setLogin( user1.getLogin() ); //Set Login to one that in DB

        assertThrows(javax.persistence.PersistenceException.class,
                () -> dao.update(user2),
                "Login is a unique field in the DB, cannot have repeated login saved on update."
        );//Should not allow to create two user with the same Login
    }

    @Test public void testLoginSuccess(){
        User newuser = createNewUserEntity();

        dao.create(newuser); //Create a new user
        User logged = dao.findUserByLogin(newuser.getLogin());
        assertAll("Successful Login Assertions",
                ()-> assertNotNull(logged,"Logged user cannot be null"),
                ()-> assertNotNull(logged.getPassword(), "Password from logged user cannot be null"),
                ()-> assertTrue(logged.getPassword().length()>0, "Password cannot be empty")
        );
    }

    @Test public void testLoginFailure(){
        User logged = dao.findUserByLogin("DoesNotExist@nowhere.com");
        assertNull(logged, "Logged User must return null if Login not found");
    }
}