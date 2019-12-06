package test;

import jwrc.board.Board;
import jwrc.board.Sites;
import jwrc.game.Game;
import jwrc.game.PropertyOverlord;
import jwrc.game.Turn;
import jwrc.player.Player;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommunityChestTest {

    static Game game;
    static Turn turn;
    static Board board;
    static Player p1;
    static Player p2;
    static Player p3;
    static ArrayList<Player> players;
    static ArrayList<Integer> commDeck;
    static ArrayList<Integer> chanceDeck;
    static PropertyOverlord po;

    @BeforeClass
    public static void BeforeClass() {
        board = new Board();
        po = new PropertyOverlord();
    }

    @AfterClass
    public static void AfterClass() {
        board = null;
        po = null;
    }

    @Before
    public void setUp() {
        game = Game.getInstance();
        p1 = new Player("PLAYER1");
        p2 = new Player("PLAYER2");
        p3 = new Player("PLAYER3");
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        commDeck = new ArrayList<>();
        chanceDeck = new ArrayList<>();
    }

    @After
    public void tearDown() {
        game = null;
        p1 = null;
        p2 = null;
        p3 = null;
        players = null;
        turn = null;
        commDeck = null;
        chanceDeck = null;
    }

    @Test
    public final void test_takeAction_case0 () {
        commDeck.add(0);
        chanceDeck.add(0);
        turn = new Turn(commDeck, chanceDeck);
        p1.evaluatePosition(2); //send to position 2
        assertEquals("Ensure position is 2 Before", 2, p1.getBoardIndex());
        int balanceBefore = p1.getAccountBalance();
        Turn.movePlayerForward(p1, players);
        assertEquals("Ensure position is 0 After", 0, p1.getBoardIndex());
        assertEquals("Ensure balance is 200 greater After", (balanceBefore+200), p1.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case1 () {
        commDeck.add(1);
        chanceDeck.add(1);
        turn = new Turn(commDeck, chanceDeck);
        p1.evaluatePosition(2); //send to position 7
        int balanceBefore = p1.getAccountBalance();
        Turn.movePlayerForward(p1, players);
        assertEquals("Ensure balance is 200 greater After", (balanceBefore+200), p1.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case4() {
        commDeck.add(4);
        chanceDeck.add(4);
        turn = new Turn(commDeck, chanceDeck);
        p1.evaluatePosition(2);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure card added", 1, p1.getGetOutOfJailFreeCard());
    }

    @Test
    public final void test_takeAction_case5() {
        commDeck.add(5);
        chanceDeck.add(5);
        turn = new Turn(commDeck, chanceDeck);
        p1.evaluatePosition(2);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure at Jail Index", 10, p1.getBoardIndex());
        assertTrue("Ensure player in jail", p1.getJailStatus());
    }

    @Test
    public final void test_takeAction_case6() {
        commDeck.add(6);
        chanceDeck.add(6);
        turn = new Turn(commDeck, chanceDeck);
        int bal1 = p1.getAccountBalance();
        int bal2 = p2.getAccountBalance();
        int bal3 = p3.getAccountBalance();
        p1.evaluatePosition(2);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure Player1 has 100 more in account", bal1+100,p1.getAccountBalance());
        assertEquals("Ensure Player1 has 100 more in account", bal2-50,p2.getAccountBalance());
        assertEquals("Ensure Player1 has 100 more in account", bal3-50,p3.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case14() {
        commDeck.add(14);
        chanceDeck.add(14);
        turn = new Turn(commDeck, chanceDeck);
        p1.evaluatePosition(2);
        Sites s1, s2;
        s1 = (Sites)Board.spaces.get(1);
        s2 = (Sites)Board.spaces.get(3);
        p1.addProperty(s1);
        p1.addProperty(s2);
        s1.changeOwner(p1.getName());
        s2.changeOwner(p1.getName());

        PropertyOverlord.buildHouse(p1,1);
        PropertyOverlord.buildHouse(p1,3);
        PropertyOverlord.buildHouse(p1,1);
        PropertyOverlord.buildHouse(p1,3);
        PropertyOverlord.buildHouse(p1,1);
        PropertyOverlord.buildHouse(p1,3);

        int balBefore = p1.getAccountBalance();
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure Player1 spent (6*40=$240) on repairs", balBefore-240,p1.getAccountBalance());
    }

}
