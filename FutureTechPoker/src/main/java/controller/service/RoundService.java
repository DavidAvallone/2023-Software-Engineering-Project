package controller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Poker.*;
import model.dao.UserDAO;
import model.entity.User;

import java.util.Random;

public class RoundService {

    public Round round;
    private UserDAO dao = new UserDAO();
    public boolean game_started;

    public Player player;
    private User u;

    public String game_type;
    private int current_small;

    private int current_big;

    public RoundService(){
        this.round = null;
        this.game_started = false;
        this.current_small = 0;
        this.current_big = 1;
    }

    public RoundService(User u, boolean single){
        this.u = u;
        this.round = null;
        this.game_started = false;
        if (single)
            this.player = new Player(u.getID(), u.getUsername(),u.getBalance(), 0);
        else
            this.player = new Player(u.getID(), u.getUsername(),u.getBalance(), determine_turn());
        this.current_small = 0;
        this.current_big = 1;
    }

    public void game_tutorial(){
        game_type = "tutorial";
        //Player p1 = new Player(0, "dave", 5000,0);
        Player p2 = new Player(1, "bolden", 5000,1);
        Player p3 = new Player(2, "alex", 5000,2);
        Player p4 = new Player(3, "neil", 5000,3);
        Player p5 = new Player(4, "kelly", 5000, 4);
        Player p6 = new Player(5, "jules", 5000, 5);

        int starting_bet = 50;

        long seed = 6858533835276294153L;
        this.round = new Round(starting_bet, seed);
        this.round.add_player(player, "playing");
        this.round.add_player(p2,"playing");
        this.round.add_player(p3,"playing");
        this.round.add_player(p4,"playing");
        this.round.add_player(p5,"playing");
        this.round.add_player(p6,"playing");
        this.round.start_game(current_small, current_big);
        this.game_started = true;
    }

    public void reset_tutorial(){
        int starting_bet = 50;
        long seed = 6858533835276294153L;
        List<Player> players = this.round.getPlayers();
        this.round = new Round(starting_bet,seed);
        for (Player p : players){
            this.round.add_player(p, "playing");
        }
        this.current_small++;
        this.current_big++;
        if( this.current_big > 5)
            this.current_big = 0;
        if( this.current_small > 5)
            this.current_small = 0;
        this.round.start_game(this.current_small, this.current_big);
    }

    public void create_game(){
        game_type = "single";
        //Player p1 = new Player(0, "dave", 5000,0);
        Player p2 = new Player(1, "bolden", 5000,1);
        Player p3 = new Player(2, "alex", 5000,2);
        Player p4 = new Player(3, "neil", 5000,3);
        Player p5 = new Player(4, "kelly", 5000, 4);
        Player p6 = new Player(5, "jules", 5000, 5);

        int starting_bet = 50;
        Random rand = new Random();

        long seed = rand.nextLong();
        this.round = new Round(starting_bet, seed);
        this.round.add_player(player, "playing");
        this.round.add_player(p2,"playing");
        this.round.add_player(p3,"playing");
        this.round.add_player(p4,"playing");
        this.round.add_player(p5,"playing");
        this.round.add_player(p6,"playing");
        this.round.start_game(current_small, current_big);
        this.game_started = true;
    }

    public void create_multiplayer_game(){
        //Player p1 = new Player(0, "dave", 5000,0);
//        Player p2 = new Player(1, "bolden", 5000,1);
//        Player p3 = new Player(2, "alex", 5000,2);
//        Player p4 = new Player(3, "neil", 5000,3);
//        Player p5 = new Player(4, "kelly", 5000, 4);
//        Player p6 = new Player(5, "jules", 5000, 5);

        int starting_bet = 50;
        Random rand = new Random();

        long seed = rand.nextLong();
        this.round = new Round(starting_bet, seed);
        this.round.add_player(player, "playing");
//        this.round.add_player(p2,"playing");
//        this.round.add_player(p3,"playing");
//        this.round.add_player(p4,"playing");
//        this.round.add_player(p5,"playing");
//        this.round.add_player(p6,"playing");

    }

    public static int determine_turn(){
        return 0;
    }

    public void start_multiplayer_game(){
        this.round.start_game(current_small, current_big);
        this.game_started = true;
    }



    public void new_game(){
        Random rand = new Random();
        long seed = rand.nextLong();
        int starting_bet = 50;
        List<Player> players = this.round.getPlayers();
        this.round = new Round(starting_bet,seed);
        for (Player p : players){
            this.round.add_player(p, "playing");
        }
        this.current_small++;
        this.current_big++;
        if( this.current_big > 5)
            this.current_big = 0;
        if( this.current_small > 5)
            this.current_small = 0;
        this.round.start_game(this.current_small, this.current_big);
    }

    public ArrayList<String> extractCardNames(String inputString) {
        ArrayList<String> cardNames = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b(?:\\d+|Jack|Queen|King|Ace) of \\w+\\b");
        Matcher matcher = pattern.matcher(inputString);

        while (matcher.find()) {
            String cardDescription = matcher.group().replaceAll(" ", "_").toLowerCase() + ".png";
            cardNames.add(cardDescription);
        }

        return cardNames;
    }

    public void update_player_db(){
        User found = dao.findUserByLogin(u.getLogin());
        found.setBalance(player.getCurrency());
        dao.update(found);
    }
    public void update_player_outcome(boolean o){
        User found = dao.findUserByLogin(u.getLogin());
        if(o)
            found.setWins(found.getWins()+1);
        else
            found.setLosses(found.getLosses()+1);
        dao.update(found);
    }
}
