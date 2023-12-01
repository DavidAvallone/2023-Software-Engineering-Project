package model.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.*;



/*
CREATE TABLE IF NOT EXISTS user (
id_user INT AUTO_INCREMENT PRIMARY KEY,
Login VARCHAR(255) UNIQUE,
Username VARCHAR(255) UNIQUE,
Password VARCHAR(255),
Permission INT,
Balance DOUBLE,
Wins INT,
Losses INT,
Banned BOOLEAN
);
 */
@Entity
public class User extends BaseEntity {
    @Id @Column(name="id_user") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;
    @Column(unique=true) // Login/Email must be unique for each record in DB
    private String Login;
    private String Username;
    private String Password;
    private int Permission;
    private Double Balance;
    private Integer Wins;
    private Integer Losses;

    private boolean Banned;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "Friends",
//            joinColumns = @JoinColumn(name = "owner"),
//            inverseJoinColumns = @JoinColumn(name = "friend")
//    )
    //private List<User> friends = new ArrayList<>();

    public transient static final int NORMAL_PERMISSION = 1;
    public transient static final int ADMIN_PERMISSION = 2;
    public transient static final int GUEST_PERMISSION = 3; // Correct/best way to make a guest profile?

    public User(){
        this.Permission = NORMAL_PERMISSION;
        Balance = 5000.0;
        Wins = 0;
        Losses = 0;
        Banned = false;
    }

    public User(Integer ID, String name, String login, String password, int permission) {
        this.ID = ID;
        this.Username = name;
        Login = login;
        Password = password;
        Permission = permission;
        Banned = false;
    }

    public static User createGuest(){
        User guest = new User();
        guest.Username = "GuestUsername";
        guest.Permission = GUEST_PERMISSION;
        guest.Login = "Guest";
        guest.Banned = false;
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

    public void setName(String name){
        this.Username = name;
    }

    public String getName(){
        return this.Username;
    }

    /***
     * Returns the email which should be same as the Login
     *
     * @return
     */
    public String getUsername(){ return Username; }

    /***
     * Sets the Email which should be same as Login
     */
    public void setUsername(String username){
        Username = username;
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

    public void setLosses(Integer losses) {
        Losses = losses;
    }

    public void setWins(Integer wins) {
        Wins = wins;
    }

    public int getLosses() {
        return Losses;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public boolean getBanned(){
        return this.Banned;
    }

    public void setBanned(boolean banned){
        this.Banned = banned;
    }

    //Be careful using getFriends(). The fetch type is lazy so this will often be inaccurate or empty
    //May want to use getFriendsList(User owner) in FriendsService instead
//    public List<User> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<User> friends) {
//        this.friends = friends;
//    }
}
