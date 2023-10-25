package model.dao;

import model.entity.BaseEntity;
import model.entity.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.annotation.Annotation;
import java.util.List;

public abstract class GenericDAO<E extends BaseEntity> {
    public enum DbType {STANDARD, TEST};
    protected static EntityManagerFactory standardEMF = null; //Factory for Normal DB
    protected static EntityManagerFactory testEMF = null; //Factory for Test DB

    protected DbType DbTypeOutput = DbType.STANDARD; //Sets standard DAO output to Normal DB
    protected Class<E> EntityClass; //Necessary to use some functions

    /***
     * Default Constructor, it requires a BaseEntity subclass as Entity.class
     * Since GenericDAO is an abstract class you cannot instantiate by itself (this is by design and not a bug)
     * the goal is to enforce developers to create a subclas of GenericDAO
     * @param entityclass
     */
    public GenericDAO(Class<E> entityclass){
        this.EntityClass = entityclass;
    }

    /***
     * Sets up the DAO outputs to a different DB
     * Mostly used to set the output to the Test DB
     * @param output
     */
    public void setDbTypeOutput(DbType output){
        this.DbTypeOutput = output;
    }

    /***
     * Creates the EntityManagerFactory if it does not exist,
     * and uses it to return a reference to EntityManager.
     * EntityManager is the JPA class to communicate with the DB.
     *
     * @return the appropriate EntityManager (for Production or Test DB)
     */
    protected EntityManager getEntityManager(){
        EntityManagerFactory fac = null;
        switch (DbTypeOutput){
            case STANDARD:
                if(standardEMF == null) standardEMF = Persistence.createEntityManagerFactory("standardPersistenceUnit");
                fac = standardEMF;
                break;
            case TEST:
                if(testEMF == null) testEMF = Persistence.createEntityManagerFactory("testPersistenceUnit");
                fac = testEMF;
                break;
        }
        return fac.createEntityManager();
    }

    /***
     * Creates (Saves/Inserts) a new entity into the DB.
     * @param entity A BaseEntity subclass object to be saved in the DB.
     */
    public E create(E entity){
        EntityManager em = this.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            em.close();
        }catch(Exception ex){
            //Something went wrong (like duplicate unique filed)
            em.getTransaction().rollback(); //abort transaction
            em.close();
            throw ex; //rethrow for the upper layer to know what happened
        }
        return entity;
    }

    /***
     * Read operation uses getID() function inside the entity
     * to call for read(int id) function.
     * @param entity A BaseEntity subclass object
     * @return the entity full info that was in the DB or null if it does not exist
     */
    public E read(E entity){
        return read(entity.getID());
    }

    /***
     * Read operation, searches DB for the specified ID.
     * @param id The id in the DB
     * @return A BaseEntity subclass with the appropriate record in the DB, or null if it does not exist
     */
    public E read(int id){
        EntityManager em = this.getEntityManager();
        E entity = em.find(EntityClass, id);
        em.close();
        return entity;
    }

    /***
     * Updates the entity in the DB
     * @param entity The entity to be updated
     * @return The updated version of the entity
     */
    public E update(E entity){
        EntityManager em = this.getEntityManager();
        E updated = null;
        try {
            em.getTransaction().begin();
            updated = em.merge(entity);
            em.getTransaction().commit();
            em.close();
        }catch(Exception ex){
            em.getTransaction().rollback();
            em.close();
            throw ex;
        }
        return updated;
    }

    /***
     * Deletes the record in DB specified by the ID.
     * @param id The ID in the DB
     */
    public void delete(int id){
        E entity = read(id);
        delete(entity);
    }

    /***
     * Deletes the entity in the DB
     * @param entity
     */
    public void delete(E entity){
        if(entity==null || entity.getID()==null){
            return;
        }

        EntityManager em = this.getEntityManager();
        try {
            em.getTransaction().begin();
            E mergedContext = em.merge(entity);
            em.remove(mergedContext);
            em.getTransaction().commit();
            em.close();
        }catch(Exception ex){
            em.getTransaction().rollback();
            em.close();
            throw ex;
        }
    }

    /***
     * Deletes all records in the table.
     * This should *not* be used in Production DB only for TestDB
     */
    public void deleteAll(){
        if(DbTypeOutput!=DbType.TEST){
            System.out.println("Access Denied, cannot use deleteAll outside testing.");
            return;
        }

        String query = "DELETE FROM "+getTableName();

        EntityManager em = this.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery(query).executeUpdate();
            em.getTransaction().commit();
            em.close();
        }catch(Exception ex){
            em.getTransaction().rollback();
            em.close();
            throw ex;
        }
    }

    /***
     * List operation returns a list of all records in the table mapped by the DAO.
     *
     * @param Order String specifying the database field(s) for the Order by clause.
     * @return A java.util.List of entities
     */
    public List<E> list(String Order){
        String query = "SELECT entity FROM "+getTableName()+" entity";
        if(Order!=null) query+=" ORDER BY "+Order;

        EntityManager em = this.getEntityManager();
        List<E> results = em.createQuery(query,EntityClass).getResultList();
        return results;
    }

    /***
     * Used to get the Table Name in the DB mapped by this DAO.
     * @return A String with the appropriate table name mapped by this DAO.
     */
    public String getTableName(){
        javax.persistence.Table tableAnnotation = EntityClass.getAnnotation(javax.persistence.Table.class);
        if(tableAnnotation != null) return tableAnnotation.name();
        else return EntityClass.getName();
    }
}
