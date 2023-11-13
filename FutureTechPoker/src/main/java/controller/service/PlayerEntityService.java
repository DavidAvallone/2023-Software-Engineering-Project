package controller.service;
import model.entity.User;
import model.entity.Player;
import model.dao.PlayerDAO;

public class PlayerEntityService {

    public static PlayerDAO dao = new PlayerDAO();

    public static void setDAO(PlayerDAO dao){
        PlayerEntityService.dao = dao;
    }

    public static Player createPlayer(User u){
        try {
            Player player = new Player(u);
            dao.create(player);
            return player;
        }
        catch (javax.persistence.PersistenceException ex){
            System.out.println(ex);
            return null;
        }
    }
}
