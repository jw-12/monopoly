package test;

import jwrc.board.*;
import jwrc.game.Game;
import jwrc.game.Trade;
import jwrc.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TradeTest {

    static Game game;
    static Player p1, p2, p3, p4;
    static Sites s1, s2;
    static Utility u1, u2;
    static TransportSpaces t1, t2;

    @BeforeClass
    public static void BeforeClass() {
        Board board = new Board();
    }

    @Before
    public void setUp() {
        game = Game.getInstance();
        p1 = new Player("Player1");
        p2 = new Player("Player2");
        p3 = new Player("Player3");
        p4 = new Player("Player4");
        Game.playerList.addAll(Arrays.asList(p1,p2,p3,p4));
        s1 = new Sites("Mediterranean Avenue", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50);
        s2 = new Sites("Other Ave", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50);
        u1 = new Utility("Water Works", 75, 28);
        u2 = new Utility("Other Works", 75, 28);
        t1 = new TransportSpaces("Pennsylvania Railroad", 100, 15);
        t2 = new TransportSpaces("Other Railroad", 100, 15);
    }

    @After
    public void tearDown() {
        game = null;
        p1 = null;
        p2 = null;
        p3 = null;
        p4 = null;
        s1 = null;
        s2 = null;
        u1 = null;
        u2 = null;
        t1 = null;
        t2 = null;
    }

    @Test
    public final void test_startAuction_singleProperty() {
        ArrayList<Player> otherPlayers = new ArrayList<>(Game.playerList);
        otherPlayers.remove(p1);
        p1.addProperty(s1);
        ArrayList<Property> expected = new ArrayList<>();
        expected.add(s1);


        System.out.println("------>TEST INSTRUCTIONS:\nExit Player2 From Auction (Input 0)\nBid some amount with Player3 (Input int > 0)\nExit Player4 from Auction (Input 0)");
        Trade.startAuction(otherPlayers, s1);
        assertEquals("Player3 should own this property only: ", expected, p3.getPropertiesOwned());

    }
}
