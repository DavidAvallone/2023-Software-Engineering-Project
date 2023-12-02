package controller.service;

import model.dao.UserDAO;
import model.entity.User;
import util.PasswordUtil;

import java.util.List;

public class UserService {

    public static UserDAO dao = new UserDAO();

    public static void setDAO(UserDAO dao){
        UserService.dao = dao;
    }

    /***
     * Registers new user in the DB by calling the DAO.
     * Although you could put this method inside the Servlet,
     *  by separating into another class, it is easier to test and mock
     * @param newUser User entity to save (without ID)
     * @return Saved User Entity (with ID) or null if Login already exists
     */
    public static User registerUser(User newUser){
        try {
            String hashed = newUser.getPassword() == null ? "" : PasswordUtil.hash(newUser.getPassword()); // Determine if new user is a guest or real user
            newUser.setPassword(hashed);
            newUser = dao.create(newUser);
        }catch(javax.persistence.PersistenceException ex){
            //Repeated login
            newUser = null;
            System.out.println(ex);
        }
        finally {
            return newUser;
        }
    }

    /***
     * Checks if Login & Password matches the DB
     * @param login The login (email)
     * @param unhashedPassword Unhashed Password as String
     * @return The User record if successful or null if Login/Password incorrect.
     */
    public static User loginUser(String login, String unhashedPassword){
        User found = dao.findUserByLogin(login);
        if(found!=null && PasswordUtil.compare(unhashedPassword,found.getPassword())){ // Found user by login
            return found;
        }
        return null; // Login or Password incorrect
    }

    public static User findUserByName(String name){
        return dao.findUserByName(name);
    }

    public static User findUserById(int id){
        return dao.read(id);
    }

    public static void deleteUser(int id){
        dao.delete(id);
    }

    public static void updatePassword(User updatedUser) {
        dao.update(updatedUser);

    }

    public static void updateUsername(User updateUser){
        dao.update(updateUser);
    }
}
