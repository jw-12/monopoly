package test;

import jwrc.board.*;
import jwrc.game.Game;
import jwrc.game.Turn;
import jwrc.player.Player;
import org.junit.*;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class ChanceTest {

    static Game game;
    static Turn turn;
    static Board board;
    static Player p1;
    static Player p2;
    static Player p3;
    static ArrayList<Player> players;
    static ArrayList<Integer> commDeck;
    static ArrayList<Integer> chanceDeck;

    @BeforeClass
    public static void BeforeClass() {
        board = new Board();
    }

    @AfterClass
    public static void AfterClass() {
        board = null;
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
        p1.evaluatePosition(7); //send to position 7
        assertEquals("Ensure position is 7 Before", 7, p1.getBoardIndex());
        int balanceBefore = p1.getAccountBalance();
        Turn.movePlayerForward(p1, players);
        assertEquals("Ensure position is 0 After", 0, p1.getBoardIndex());
        assertEquals("Ensure balance is 200 greater After", (balanceBefore+200), p1.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case1_noPassGo () {
        commDeck.add(1);
        chanceDeck.add(1);
        turn = new Turn(commDeck, chanceDeck);
        Sites s = (Sites) Board.spaces.get(24);
        s.changeOwner(p2.getName());
        p2.addProperty(s); //let p2 own so no User Input Needed
        p1.evaluatePosition(7); //send to position 7
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure position is 24 After", 24, p1.getBoardIndex());
    }

    @Test
    public final void test_takeAction_case1_passGo () {
        commDeck.add(1);
        chanceDeck.add(1);
        turn = new Turn(commDeck, chanceDeck);
        Sites s = (Sites) Board.spaces.get(24);
        s.changeOwner(p2.getName());
        p2.addProperty(s); //let p2 own so no User Input Needed
        int balanceBefore = p1.getAccountBalance();
        p1.evaluatePosition(36); //send to position 36
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure position is 24 After", 24, p1.getBoardIndex());
        //180 since $20 rent will be paid at position 36 to player 2
        assertEquals("Ensure balance is 180 greater after move", balanceBefore+180, p1.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case2_noPassGo () {
        commDeck.add(2);
        chanceDeck.add(2);
        turn = new Turn(commDeck, chanceDeck);
        Sites s = (Sites) Board.spaces.get(11);
        s.changeOwner(p2.getName());
        p2.addProperty(s); //let p2 own so no User Input Needed
        int balanceBefore = p1.getAccountBalance();
        p1.evaluatePosition(7); //send to position 7
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure position is 11 After", 11, p1.getBoardIndex());
    }

    @Test
    public final void test_takeAction_case2_passGo () {
        commDeck.add(2);
        chanceDeck.add(2);
        turn = new Turn(commDeck, chanceDeck);
        Sites s = (Sites) Board.spaces.get(11);
        s.changeOwner(p2.getName());
        p2.addProperty(s); //let p2 own so no User Input Needed
        int balanceBefore = p1.getAccountBalance();
        p1.evaluatePosition(36); //send to position 36
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure position is 24 After", 11, p1.getBoardIndex());
        //190 since $10 rent will be paid at position 36 to player 2
        assertEquals("Ensure balance is 190 greater after move", balanceBefore+190, p1.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case3_noPassGo() {
        commDeck.add(3);
        chanceDeck.add(3);
        turn = new Turn(commDeck, chanceDeck);
        p1.rollDice(); //set diceVal to be used when land on Utility
        Utility u = (Utility) Board.spaces.get(12);
        p2.addProperty(u);
        u.changeOwner(p2.getName());
        p1.evaluatePosition(7);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure position is 12 After", 12, p1.getBoardIndex());
    }

    @Test
    public final void test_takeAction_case3_passGo() {
        commDeck.add(3);
        chanceDeck.add(3);
        turn = new Turn(commDeck, chanceDeck);
        p1.rollDice(); //set diceVal to be used when land on Utility
        Utility u = (Utility) Board.spaces.get(12);
        p2.changeUtilitiesOwned(+1);
        p2.addProperty(u);
        u.changeOwner(p2.getName());
        int balanceBefore = p1.getAccountBalance();
        p1.evaluatePosition(36);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure position is 12 After", 12, p1.getBoardIndex());
        assertEquals("Ensure balance is (200 - Utility Rent) greater after move", (balanceBefore + 200 - (4*(p1.diceVal[0]+p1.diceVal[1]))), p1.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case4_noPassGo() {
        commDeck.add(4);
        chanceDeck.add(4);
        turn = new Turn(commDeck, chanceDeck);
        TransportSpaces t = (TransportSpaces) Board.spaces.get(15);
        p2.addProperty(t);
        t.changeOwner(p2.getName());
        p1.evaluatePosition(7);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure position is 15 After", 15, p1.getBoardIndex());
    }

    @Test
    public final void test_takeAction_case4_passGo() {
        commDeck.add(4);
        chanceDeck.add(4);
        turn = new Turn(commDeck, chanceDeck);
        TransportSpaces t = (TransportSpaces) Board.spaces.get(5);
        p2.changeTransportsOwned(+1);
        p2.addProperty(t);
        t.changeOwner(p2.getName());
        int balanceBefore = p1.getAccountBalance();
        p1.evaluatePosition(36);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure position is 15 After", 5, p1.getBoardIndex());
        assertEquals("Ensure balance is (200 - Transport Rent) greater after move", (balanceBefore + 200 - 25), p1.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case5() {
        commDeck.add(5);
        chanceDeck.add(5);
        turn = new Turn(commDeck, chanceDeck);
        int balanceBefore = p1.getAccountBalance();
        p1.evaluatePosition(7);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure balance $50 greater after", balanceBefore+50, p1.getAccountBalance());
    }

    @Test
    public final void test_takeAction_case6() {
        commDeck.add(6);
        chanceDeck.add(6);
        turn = new Turn(commDeck, chanceDeck);
        p1.evaluatePosition(7);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure card added", 1, p1.getGetOutOfJailFreeCard());
    }

    @Test
    public final void test_takeAction_case7() {
        commDeck.add(7);
        chanceDeck.add(7);
        turn = new Turn(commDeck, chanceDeck);
        p1.evaluatePosition(7);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure moved back 3 spaces", 4, p1.getBoardIndex());
    }

    @Test
    public final void test_takeAction_case8() {
        commDeck.add(8);
        chanceDeck.add(8);
        turn = new Turn(commDeck, chanceDeck);
        p1.evaluatePosition(7);
        Turn.movePlayerForward(p1,players);
        assertEquals("Ensure at Jail Index", 10, p1.getBoardIndex());
        assertTrue("Ensure player in jail", p1.getJailStatus());
    }
}
