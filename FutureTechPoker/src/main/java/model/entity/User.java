package model.entity;
import java.util.Random;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id @Column(name="id_user") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;
    @Column(name = "login",unique=true) // Login/Email must be unique for each record in DB
    private String Login;

    private String Password;

    private String Name;

    private int Permission;

    private Double Balance;

    private Integer Wins;
    
    private Integer Losses;

    public transient static final int NORMAL_PERMISSION = 1;
    public transient static final int ADMIN_PERMISSION = 2;
    public transient static final int GUEST_PERMISSION = 3; // Correct/best way to make a guest profile?

    public User(){
        this.Permission = NORMAL_PERMISSION;
        Balance = 5000.0;
        Wins = 0;
        Losses = 0;
    }

    public User(Integer ID, String name, String login, String password, int permission) {
        this.ID = ID;
        this.Name = name;
        Login = login;
        Password = password;
        Permission = permission;
    }

    public static User createGuest(){
        User guest = new User();
        Random rand = new Random();
        guest.Name = "Guest"+ rand.nextLong();
        guest.Permission = GUEST_PERMISSION;
        guest.Login = "Guest";
        return guest;
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

    public void setName(String name){
        this.Name =name;
    }
    public String getName(){
        return this.Name;
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
        else if(Permission==GUEST_PERMISSION) return "Guest";
        else return "Unknown";
    }

    public int getWins() {
        return Wins;
    }

    public int getLosses() {
        return Losses;
    }

    public Double getBalance() {
        return Balance;
    }
}
