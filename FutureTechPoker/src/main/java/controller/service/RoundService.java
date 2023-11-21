package controller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Poker.*;
import java.util.Random;

public class RoundService {

    public Round round;
    public boolean game_started;

    public RoundService(){
        this.round = null;
        this.game_started = false;
    }

    public void game_create_test(){
        Player p1 = new Player(0, "dave", 5000,0);
        Player p2 = new Player(1, "bolden", 5000,1);
        Player p3 = new Player(2, "alex", 5000,2);
        Player p4 = new Player(3, "neil", 5000,3);
        Player p5 = new Player(4, "kelly", 5000, 4);
        Player p6 = new Player(5, "jules", 5000, 5);

        int starting_bet = 50;
        Random rand = new Random();
        long seed = 42;
        this.round = new Round(starting_bet, seed);
        this.round.add_player(p1, "playing");
        this.round.add_player(p2,"playing");
        this.round.add_player(p3,"playing");
        this.round.add_player(p4,"playing");
        this.round.add_player(p5,"playing");
        this.round.add_player(p6,"playing");
        this.round.start_game();
        this.game_started = true;
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
}
