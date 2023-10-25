package model.entity;

import javax.persistence.*;

@Entity
public class User extends BaseEntity {
    @Id @Column(name="id_user") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;
    @Column(unique=true) //Login must be unique for each record in DB
    private String Login;
    private String Password;
    private int Permission;

    public transient static final int NORMAL_PERMISSION = 1;
    public transient static final int ADMIN_PERMISSION = 2;

    public User(){
        this.Permission = NORMAL_PERMISSION;
    }

    public User(Integer ID, String login, String password, int permission) {
        this.ID = ID;
        Login = login;
        Password = password;
        Permission = permission;
    }

    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    /***
     * Returns the email which should be same as the Login
     *
     * @return
     */
    public String getEmail(){ return getLogin(); }

    /***
     * Sets the Email which should be same as Login
     */
    public void setEmail(String email){
        setLogin(email);
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getPermission() {
        return Permission;
    }

    public void setPermission(int permission) {
        Permission = permission;
    }

    public String getPermissionAsString(){
        if(Permission==NORMAL_PERMISSION) return "Normal";
        else if(Permission==ADMIN_PERMISSION) return "Admin";
        else return "Unknown";
    }
}
