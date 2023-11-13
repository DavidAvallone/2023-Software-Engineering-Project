package model.dao;

import model.entity.Player;

import model.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerDAOTest {
    public static PlayerDAO dao = null;
    public static UserDAO udao = null;

    public static User createNewUser(){
        User u = new User();
        u.setLogin("test@test.com");
        u.setUsername("test");
        u.setPassword("111");
        u.setPermission(User.NORMAL_PERMISSION);
        return u;
    }
    public static Player createNewPlayerEntity(User u){
        Player player = new Player(u);
        //player.setName("test");
        player.setStatus("testing");
        player.setCurrentBet(69.0);
        player.setTurnOrder(0);
        //player.setCurrency(6900);
        player.setHand("test hand");
        return player;
    }

    @BeforeAll public static void createDao(){
        dao = new PlayerDAO();
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
        udao = new UserDAO();
        udao.setDbTypeOutput(GenericDAO.DbType.TEST);
    }

    @BeforeEach public void deleteAll(){
        dao.deleteAll();
        udao.deleteAll();
    }

//    @AfterAll public static void deleteAllAfter(){
//        dao.deleteAll();
//        udao.deleteAll();
//    }

    @Test public void testCreatePlayer(){
        User newuser = createNewUser();
        Player newplayer = createNewPlayerEntity(newuser);
        udao.create(newuser);
        dao.create(newplayer);
        Player found = dao.read(newplayer.getID());
        assertAll("Grouped Assertions of Create Player",
                () -> assertNotNull(newplayer.getID(), "ID should not be null after creation"),
                () -> assertNotNull(found, "Found player after reading should not be null"),
                () -> assertEquals(newplayer.getPlayerName(), found.getPlayerName(), "Name for the newplayer must be equal to the found name"),
                () -> assertEquals(newplayer.getCurrentBet(),found.getCurrentBet())
        );
    }
}