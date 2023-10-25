import Poker.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class RoundTest {

    @Test
    public void test_round_creation(){
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(0, "neil", 10000);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);
        System.out.println(round);
        assertEquals(50, starting_bet);
    }
}
