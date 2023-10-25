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
            String hashed = PasswordUtil.hash(newUser.getPassword());
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
        if(found!=null){ //Found user by login
            //We must certify the passwords match
            if(PasswordUtil.compare(unhashedPassword,found.getPassword())){
                return found;
            }
            //I know I could combine both IFs in the same one,
            // I separated them to make it easier to explain the logic for some students
        }
        return null; //Login or Password incorrect
    }

    /***
     * Returns a list of all Users in the DB
     * Usually for an Admin CRUD needs to see all data
     * @param Order Which field to order the results
     * @return User list
     */
    public static List<User> listUsers(String Order){
        List<User> lstUser = dao.list(Order);
        return lstUser;
    }

    public static void deleteUser(int id){
        dao.delete(id);
    }

    public static User editUser(User u){

        if(u.getPassword()==null || u.getPassword().trim().length()==0){
            //No Password was given, we need to fetch it from DB
            User original = dao.read(u.getID());
            u.setPassword(original.getPassword()); //original pass is already hashed (it is supposed to be!)
        }
        else{
            //Password was typed, we need to hash it
            String hashed = PasswordUtil.hash( u.getPassword() );
            u.setPassword(hashed);
        }

        return dao.update(u);
    }
}
