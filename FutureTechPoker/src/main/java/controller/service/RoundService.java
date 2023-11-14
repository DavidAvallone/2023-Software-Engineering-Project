package controller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Poker.*;
import java.util.Random;

public class RoundService {

    public Round round;

    public void game_create_test(){
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);

        int starting_bet = 50;
        Random rand = new Random();
        long seed = rand.nextLong();
        this.round = new Round(starting_bet, seed);
        this.round.add_player(p1, "playing");
        this.round.add_player(p2,"playing");
        this.round.add_player(p3,"playing");
        this.round.add_player(p4,"playing");
        this.round.start_game();
    }

    public ArrayList<String> extractCardNames(String inputString) {
        ArrayList<String> cardNames = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+ of \\w+");
        Matcher matcher = pattern.matcher(inputString);

        while (matcher.find()) {
            String cardDescription = matcher.group().replaceAll(" ", "_").toLowerCase() + ".png";
            cardNames.add(cardDescription);
        }

        return cardNames;
    }
}
