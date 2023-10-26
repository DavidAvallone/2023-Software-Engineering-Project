import Poker.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RoundHelperTest {

    @Test
    public void player_sort_test_same() {
        long seed = 42;

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
        Round round = new Round(players, 50,seed);

        ArrayList<Player> expected = new ArrayList<>();
        expected.add(p3);
        expected.add(p2);
        expected.add(p1);
        expected.add(p4);
        assertEquals(expected, round.getPlayers());
    }

    @Test
    public void player_sort_test_notsame() {
        long seed = 42;

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
        Round round = new Round(players, 50,seed);

        ArrayList<Player> expected = new ArrayList<>();
        expected.add(p3);
        expected.add(p2);
        expected.add(p4);
        expected.add(p1);
        assertNotEquals(expected, round.getPlayers());
    }

    @Test
    public void update_blinds_small() {
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        // p3 is small blind
        assertEquals(true, round.getPlayers().get(0).isSmallBlind());
    }

    @Test
    public void update_blinds_big() {
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        // p2 is big blind
        assertEquals(true, round.getPlayers().get(1).isBigBlind());
    }

    @Test
    public void update_blinds_notBlind() {
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        // p1 is neither
        assertEquals(false, (round.getPlayers().get(2).isBigBlind() || round.getPlayers().get(2).isSmallBlind()));
    }

    @Test
    public void update_blinds_notBlind1() {
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        // p1 is neither
        assertEquals(false, (round.getPlayers().get(3).isBigBlind() || round.getPlayers().get(3).isSmallBlind()));
    }

    @Test
    public void deal_out_test(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);

        boolean two_cards_per_player = true;
        ArrayList<Player> dealt_cards = round.getPlayers();
        for(Player p : dealt_cards){
            if(p.getHand().getHandSize() != 2){
                two_cards_per_player = false;
            }
        }
        assertEquals(true, two_cards_per_player);
    }

    @Test
    public void player_fold(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "fold",0);
        boolean actual = round.getPlayers().get(0).isFold();
        assertEquals(true,actual);
    }
    @Test
    public void player_check(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "fold",0);
        round.player_turn(1, "check",0);
        boolean actual = round.getPlayer_status()[1].equals("check");
        assertEquals(true,actual);
    }

    @Test
    public void player_raise(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "raise",10);
        boolean actual = round.getPlayer_status()[0].equals("raise");
        assertEquals(true,actual);
    }

    @Test
    public void player_allin(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "all in",10);
        boolean actual = round.getPlayer_status()[0].equals("all in");
        assertEquals(true,actual);
    }

    @Test
    public void player_allin_currency(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "all in",10);
        assertEquals(0,round.getPlayers().get(0).getCurrency());
    }

    @Test
    public void player_call(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "call",10);
        boolean actual = round.getPlayer_status()[0].equals("call");
        assertEquals(true,actual);
    }

    @Test
    public void player_turn_exception_test(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        String choice = "hello";

        // Use assertThrows to check for the expected exception
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            round.player_turn(0, choice, 10);
        });

        // Check if the exception message is as expected
        assertEquals("Unexpected value: " + choice, exception.getMessage());
    }

    @Test
    public void is_roundover_false(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "call",0);
        round.player_turn(3, "raise",10);

        boolean actual = round.isRoundOver();
        assertEquals(false,actual);
    }

    @Test
    public void is_roundover_true(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "call",0);
        round.player_turn(3, "call",10);

        boolean actual = round.isRoundOver();
        assertEquals(true,actual);
    }

    @Test
    public void is_roundover_everyoneFolded(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "fold",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "fold",0);
        round.player_turn(3, "fold",10);

        boolean actual = round.isRoundOver();
        assertEquals(true,actual);
    }

    @Test
    public void whos_first_true(){
        long seed = 42;

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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "call",0);
        round.player_turn(3, "call",10);


        assertEquals(0,round.determine_first_player());
    }

    @Test
    public void whos_first_false(){
        long seed = 42;
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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "call",0);
        round.player_turn(3, "call",10);

        assertNotEquals(1,round.determine_first_player());
    }

    @Test
    public void last_player(){
        long seed = 42;
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
        Round round = new Round(players, 50,seed);


        round.player_turn(0, "fold",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "fold",0);
        round.player_turn(3, "fold",10);


        assertEquals(p2,round.last_player_standing());
    }

    @Test
    public void last_player_null(){
        long seed = 42;
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

        Round round = new Round(players, 50,seed);

        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "fold",0);
        round.player_turn(3, "fold",10);


        assertEquals(null,round.last_player_standing());
    }
}
