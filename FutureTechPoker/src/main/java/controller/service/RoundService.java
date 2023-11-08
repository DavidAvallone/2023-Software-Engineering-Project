package controller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Poker.*;

public class RoundService {

    public Round round;

    public void game_create_test(){
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);

        p1.setTurnOrder(0);
        p2.setTurnOrder(1);
        p3.setTurnOrder(2);
        p4.setTurnOrder(3);
        int starting_bet = 50;
        long seed = 42;
        this.round = new Round(starting_bet, seed);
        this.round.add_player(p1);
        this.round.add_player(p2);
        this.round.add_player(p3);
        this.round.add_player(p4);
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
