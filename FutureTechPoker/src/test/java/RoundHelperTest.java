import Poker.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RoundHelperTest {

    @Test
    public void player_sort_test_same() {
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);
        p1.setTurnOrder(2);
        p2.setTurnOrder(1);
        p3.setTurnOrder(0);
        p4.setTurnOrder(3);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Round round = new Round(players, 50);
        ArrayList<Player> expected = new ArrayList<>();
        expected.add(p3);
        expected.add(p2);
        expected.add(p1);
        expected.add(p4);
        assertEquals(expected, round.getPlayers());
    }

    @Test
    public void player_sort_test_notsame() {
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);
        p1.setTurnOrder(2);
        p2.setTurnOrder(1);
        p3.setTurnOrder(0);
        p4.setTurnOrder(3);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Round round = new Round(players, 50);
        ArrayList<Player> expected = new ArrayList<>();
        expected.add(p3);
        expected.add(p2);
        expected.add(p4);
        expected.add(p1);
        assertNotEquals(expected, round.getPlayers());
    }

    @Test
    public void update_blinds_small() {
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);
        p1.setTurnOrder(2);
        p2.setTurnOrder(1);
        p3.setTurnOrder(0);
        p4.setTurnOrder(3);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Round round = new Round(players, 50);

        // p3 is small blind
        assertEquals(true, round.getPlayers().get(0).isSmallBlind());
    }

    @Test
    public void update_blinds_big() {
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);
        p1.setTurnOrder(2);
        p2.setTurnOrder(1);
        p3.setTurnOrder(0);
        p4.setTurnOrder(3);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Round round = new Round(players, 50);

        // p2 is big blind
        assertEquals(true, round.getPlayers().get(1).isBigBlind());
    }

    @Test
    public void update_blinds_notBlind() {
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);
        p1.setTurnOrder(2);
        p2.setTurnOrder(1);
        p3.setTurnOrder(0);
        p4.setTurnOrder(3);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Round round = new Round(players, 50);

        // p1 is neither
        assertEquals(false, (round.getPlayers().get(2).isBigBlind() || round.getPlayers().get(2).isSmallBlind()));
    }

    @Test
    public void update_blinds_notBlind1() {
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);
        p1.setTurnOrder(2);
        p2.setTurnOrder(1);
        p3.setTurnOrder(0);
        p4.setTurnOrder(3);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Round round = new Round(players, 50);

        // p1 is neither
        assertEquals(false, (round.getPlayers().get(3).isBigBlind() || round.getPlayers().get(3).isSmallBlind()));
    }

    @Test
    public void deal_out_test(){
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);
        p1.setTurnOrder(0);
        p2.setTurnOrder(1);
        p3.setTurnOrder(2);
        p4.setTurnOrder(3);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Round round = new Round(players, 50);
        boolean two_cards_per_player = true;
        ArrayList<Player> dealt_cards = round.getPlayers();
        for(Player p : dealt_cards){
            if(p.getHand().getHandSize() != 2){
                two_cards_per_player = false;
            }
        }
        assertEquals(true, two_cards_per_player);
    }
}
