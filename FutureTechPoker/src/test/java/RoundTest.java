import Poker.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class RoundTest {

    @Test
    public void test_round_creation() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     
        

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();

        //System.out.println(round);
        assertEquals(50, starting_bet);
    }

    @Test
    public void round_one_pot() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();

        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        //System.out.println(round);
        assertEquals(200, round.getCurrent_pot());
    }

    @Test
    public void round_one_folds() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();

        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "fold", 0);
        round.player_turn(p3.getTurnOrder(), "fold", 0);
        round.player_turn(p4.getTurnOrder(), "fold", 0);
        //System.out.println(round);
        assertEquals(100, round.getCurrent_pot());
    }

    @Test
    public void round_one_folds_gameover() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     
        

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "fold", 0);
        round.player_turn(p3.getTurnOrder(), "fold", 0);
        round.player_turn(p4.getTurnOrder(), "fold", 0);
        round.update_round();
        //System.out.println(round);
        assertEquals(true, round.getGameOver());
    }


    @Test
    public void round_one_update() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round();
        assertEquals(2, round.getRound_num());
    }


    @Test
    public void test_is_round_over() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "raise", 10);
        // continue betting
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        assertEquals(true, round.isRoundOver());
    }


    @Test
    public void first_river() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "raise", 10);
        // continue betting
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.update_round();
        //System.out.println(round);
        assertEquals(2, round.getRound_num());
    }

    @Test
    public void second_river() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 10);
        // continue betting
        round.update_round();
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 10);
        round.update_round();

        //System.out.println(round);
        assertEquals(3, round.getRound_num());
    }

    @Test
    public void third_river() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        // continue betting
        round.update_round();
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round();
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round();

//        System.out.println(round);
        assertEquals(4, round.getRound_num());
    }

    @Test
    public void end_of_round3_pot() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round();// pot = 200

        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 240

        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 280

        assertEquals(280, round.getCurrent_pot());
    }

    @Test
    public void multiple_raises() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0); // 50
        round.player_turn(p2.getTurnOrder(), "check", 0); //50
        round.player_turn(p3.getTurnOrder(), "call", 0); // 50
        round.player_turn(p4.getTurnOrder(), "raise", 10); //60

        round.player_turn(p1.getTurnOrder(), "raise", 10); //70
        round.player_turn(p2.getTurnOrder(), "call", 0); //70
        round.player_turn(p3.getTurnOrder(), "call", 0); //70
        round.player_turn(p4.getTurnOrder(), "call", 0); //70

        round.update_round();

//        System.out.println(round);
        assertEquals(2, round.getRound_num());
    }

    @Test
    public void multiple_raises_check_pot() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        round.player_turn(p1.getTurnOrder(), "call", 0); // 50
        round.player_turn(p2.getTurnOrder(), "check", 0); //50
        round.player_turn(p3.getTurnOrder(), "call", 0); // 50
        round.player_turn(p4.getTurnOrder(), "raise", 10); //60

        //System.out.println(round);
        round.player_turn(p1.getTurnOrder(), "raise", 10); //70
        round.player_turn(p2.getTurnOrder(), "call", 0); //70
        round.player_turn(p3.getTurnOrder(), "call", 0); //70
        round.player_turn(p4.getTurnOrder(), "call", 0); //70

        round.update_round();

        //System.out.println(round);
        assertEquals(280, round.getCurrent_pot());
    }

    @Test
    public void correct_currency() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        //round 1
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round();// pot = 200
        // round 2
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 240
        //round 3
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 280

//        System.out.println(round);
        assertEquals(9930, round.getPlayers().get(0).getCurrency());
    }

    @Test
    public void out_of_order_player_turn() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        //round 1
        round.player_turn(p1.getTurnOrder(), "call", 0);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            round.player_turn(p1.getTurnOrder(), "check", 0);
        });

        // Check if the exception message is as expected
        assertEquals("Wrong Player! Current Turn is : 1", exception.getMessage());
    }


    @Test
    public void player_folded() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        //round 1
        round.player_turn(p1.getTurnOrder(), "fold", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "raise", 10);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            round.player_turn(p1.getTurnOrder(), "call", 0);
        });

        // Check if the exception message is as expected
        assertEquals("Player Has Folded! Current Turn is : 1", exception.getMessage());
    }

    @Test
    public void round4_river() {
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        //round 1
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round();// pot = 200
        // round 2
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 240
        //round 3
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 280
        // round 4
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 320

        //System.out.println(round);
        assertEquals(5, round.getRiver().size());
    }


    @Test
    public void classview(){
        Player p1 = new Player(0, "dave", 10000,0);
        Player p2 = new Player(1, "bolden", 10000,1);
        Player p3 = new Player(2, "alex", 10000,2);
        Player p4 = new Player(3, "neil", 10000,3);
     

        int starting_bet = 50;
        long seed = 42;
        Round round = new Round(starting_bet, seed);
        round.add_player(p1, "playing");
        round.add_player(p2, "playing");
        round.add_player(p3, "playing");
        round.add_player(p4, "playing");
        round.start_game();
        System.out.println("BEFORE ROUND ONE");
        System.out.println(round);

        //round 1
        round.player_turn(p1.getTurnOrder(), "call", 0);
        round.player_turn(p2.getTurnOrder(), "check", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round();// pot = 200
        System.out.println("AFTER ROUND ONE");
        System.out.println(round);

        // round 2
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 240
        System.out.println("AFTER ROUND TWO");
        System.out.println(round);
        //round 3
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 280
        System.out.println("AFTER ROUND THREE");
        System.out.println(round);
        // round 4
        round.player_turn(p1.getTurnOrder(), "raise", 10);
        round.player_turn(p2.getTurnOrder(), "call", 0);
        round.player_turn(p3.getTurnOrder(), "call", 0);
        round.player_turn(p4.getTurnOrder(), "call", 0);
        round.update_round(); // pot = 320
        System.out.println("AFTER ROUND FOUR");
        System.out.println(round);
        assertEquals(5, round.getRiver().size());
    }

}