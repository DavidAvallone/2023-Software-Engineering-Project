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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);
        //System.out.println(round);
        assertEquals(50, starting_bet);
    }

    @Test
    public void round_one_pot(){
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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);

        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"check",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"call", 0);
        //System.out.println(round);
        assertEquals(200,round.getCurrent_pot());
    }
    @Test
    public void round_one_folds(){
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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);

        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"fold",0);
        round.player_turn(p3.getTurnOrder(), "fold", 0);
        round.player_turn(p4.getTurnOrder(),"fold", 0);
        //System.out.println(round);
        assertEquals(100,round.getCurrent_pot());
    }

    @Test
    public void round_one_folds_gameover(){
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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);

        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"fold",0);
        round.player_turn(p3.getTurnOrder(), "fold", 0);
        round.player_turn(p4.getTurnOrder(),"fold", 0);
        round.update_round();
        //System.out.println(round);
        assertEquals(true, round.getGameOver());
    }


    @Test
    public void round_one_update(){
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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);

        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"check",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"call", 0);
        round.update_round();
        assertEquals(2,round.getRound_num());
    }


    @Test
    public void test_is_round_over(){
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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);
        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"check",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"raise", 10);
        // continue betting
        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"call",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        assertEquals(true, round.isRoundOver());
    }


    @Test
    public void first_river(){
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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);
        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"check",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"raise", 10);
        // continue betting
        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"call",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.update_round();
        //System.out.println(round);
        assertEquals(2,round.getRound_num());
    }

    @Test
    public void second_river(){
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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);
        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"check",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"call", 10);
        // continue betting
        round.update_round();
        round.player_turn(p1.getTurnOrder(),"raise",10);
        round.player_turn(p2.getTurnOrder(),"call",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"call", 10);
        round.update_round();

        //System.out.println(round);
        assertEquals(3,round.getRound_num());
    }

    @Test
    public void third_river(){
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

        int starting_bet = 50;
        Round round = new Round(players,starting_bet);
        round.player_turn(p1.getTurnOrder(),"call",0);
        round.player_turn(p2.getTurnOrder(),"check",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"call", 10);
        // continue betting
        round.update_round();
        round.player_turn(p1.getTurnOrder(),"raise",10);
        round.player_turn(p2.getTurnOrder(),"call",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"call", 10);
        round.update_round();
        round.player_turn(p1.getTurnOrder(),"raise",10);
        round.player_turn(p2.getTurnOrder(),"call",0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(),"call", 10);
        round.update_round();

        //System.out.println(round);
        assertEquals(4,round.getRound_num());
    }

}
