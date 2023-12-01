//package controller.service;
//import Poker.*;
//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RoundServiceTest {
//
//    @Test
//    public void test_extract_cards(){
//        RoundService rs = new RoundService();
//        rs.game_create_test();
//
//        String actual = "2_of_diamonds.png";
//        String actual1 = "8_of_spades.png";
//        System.out.println(rs.round.getPlayers().get(0).getHand().toString());
//        assertEquals(actual,rs.extractCardNames(rs.round.getPlayers().get(0).getHand().toString()).get(0));
//        assertEquals(actual1,rs.extractCardNames(rs.round.getPlayers().get(0).getHand().toString()).get(1));
//    }
//
//    @Test
//    public void test_extract_river(){
//        RoundService rs = new RoundService();
//        rs.game_create_test();
//
//
//
//        rs.round.player_turn(0, "raise", 10);
//
//        rs.round.player_turn(1, "call", 0);
//
//        rs.round.player_turn(2, "call", 0);
//
//        rs.round.player_turn(3, "call", 0);
//
//        rs.round.player_turn(4, "call", 0);
//
//        rs.round.player_turn(5, "call", 0);
//        rs.round.update_round();
//
//        System.out.println(rs.round.river_string());
//        System.out.println(rs.round.getPlayers().get(1).getHand());
//        List<String> cards = rs.extractCardNames(rs.round.river_string());
//        assertEquals(3, rs.round.getRiver().size());
//        assertEquals(3, cards.size());
//
//    }
//}
