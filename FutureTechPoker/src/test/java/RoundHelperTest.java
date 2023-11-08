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
        p1.setTurnOrder(0);
        p2.setTurnOrder(1);
        p3.setTurnOrder(2);
        p4.setTurnOrder(3);
        
        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);

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
        p1.setTurnOrder(0);
        p2.setTurnOrder(1);
        p3.setTurnOrder(2);
        p4.setTurnOrder(3);
        
        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);

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
        p1.setTurnOrder(0);
        p2.setTurnOrder(1);
        p3.setTurnOrder(2);
        p4.setTurnOrder(3);
        
        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        // p3 is small blind
        assertEquals(true, round.getPlayers().get(0).isSmallBlind());
    }

    @Test
    public void update_blinds_big() {
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        // p2 is big blind
        assertEquals(true, round.getPlayers().get(1).isBigBlind());
    }

    @Test
    public void update_blinds_notBlind() {
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        // p1 is neither
        assertEquals(false, (round.getPlayers().get(2).isBigBlind() || round.getPlayers().get(2).isSmallBlind()));
    }

    @Test
    public void update_blinds_notBlind1() {
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


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
        
        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);

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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "fold",0);
        boolean actual = round.getPlayers().get(0).isFold();
        assertEquals(true,actual);
    }
    @Test
    public void player_check(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "fold",0);
        round.player_turn(1, "check",0);
        boolean actual = round.getPlayer_status()[1].equals("check");
        assertEquals(true,actual);
    }

    @Test
    public void player_raise(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "raise",10);
        boolean actual = round.getPlayer_status()[0].equals("raise");
        assertEquals(true,actual);
    }

    @Test
    public void player_allin(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "all in",10);
        boolean actual = round.getPlayer_status()[0].equals("all in");
        assertEquals(true,actual);
    }

    @Test
    public void player_allin_currency(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "all in",10);
        assertEquals(0,round.getPlayers().get(0).getCurrency());
    }

    @Test
    public void player_call(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "call",10);
        boolean actual = round.getPlayer_status()[0].equals("call");
        assertEquals(true,actual);
    }

    @Test
    public void player_turn_exception_test(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "call",0);
        round.player_turn(3, "raise",10);

        boolean actual = round.isRoundOver();
        assertEquals(false,actual);
    }

    @Test
    public void is_roundover_true(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "call",0);
        round.player_turn(3, "call",10);

        boolean actual = round.isRoundOver();
        assertEquals(true,actual);
    }

    @Test
    public void is_roundover_everyoneFolded(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "fold",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "fold",0);
        round.player_turn(3, "fold",10);

        boolean actual = round.isRoundOver();
        assertEquals(true,actual);
    }

    @Test
    public void whos_first_true(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "call",0);
        round.player_turn(3, "call",10);


        assertEquals(0,round.determine_first_player());
    }

    @Test
    public void whos_first_false(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "call",0);
        round.player_turn(3, "call",10);

        assertNotEquals(1,round.determine_first_player());
    }

    @Test
    public void last_player(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);


        round.player_turn(0, "fold",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "fold",0);
        round.player_turn(3, "fold",10);


        assertEquals(p2,round.last_player_standing());
    }

    @Test
    public void last_player_null(){
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
        Round round = new Round(starting_bet, seed);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);
        round.add_player(p1);

        round.player_turn(0, "call",0);
        round.player_turn(1, "check",0);
        round.player_turn(2, "fold",0);
        round.player_turn(3, "fold",10);


        assertEquals(null,round.last_player_standing());
    }
}
