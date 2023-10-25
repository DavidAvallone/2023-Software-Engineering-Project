package model.dao;

import model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/***
 * UserDAO is a subclass of GenericDAO
 * It is a good practice to extend GenericDAO for each specific entity
 * Then we can add custom methods for this DAO (mostly, custom list operations)
 */
public class UserDAO extends GenericDAO<User> {

    public UserDAO(){
        super(User.class);
    }

    /***
     * Method used for login, finds the user tied to a specific login
     * @param login The login (email) which is unique in the DB
     * @return User entity
     */
    public User findUserByLogin(String login){
        EntityManager em = getEntityManager();

        String query = "SELECT u FROM "+getTableName()+" u WHERE u.Login = :email"; // :email is a parameter, to avoid SQL Injection
        User found = null;

        try {
            found = em.createQuery(query, User.class).setParameter("email", login).getSingleResult();
        } catch(NoResultException ex){
            found = null;
        } finally{
            em.close();
        }
        return found;
    }

}
