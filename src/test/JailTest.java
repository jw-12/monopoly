package test;

import jwrc.board.Board;
import jwrc.game.Game;
import jwrc.game.Turn;
import jwrc.player.Player;
import org.junit.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class JailTest {

    static Board board;
    static Game game;
    static Turn turn;
    static ArrayList<Integer> commDeck;
    static ArrayList<Integer> chanceDeck;
    static Player p1,p2,p3,p4;
    static int[] dice;

    @BeforeClass
    public static void BeforeClass() {
        board = new Board();
        commDeck = new ArrayList<>();
        chanceDeck = new ArrayList<>();
        commDeck.add(0);
        chanceDeck.add(0); //so test will end up with player landing on Go
    }

    @AfterClass
    public static void AfterClass() {
        board = null;
    }

    @Before
    public void setUp() {
        game = Game.getInstance();
        turn = new Turn(commDeck, chanceDeck);
        p1 = new Player("Player1");
        p2 = new Player("Player2");
        p3 = new Player("Player3");
        p4 = new Player("Player4");
        Game.playerList.add(p1);
        Game.playerList.add(p2);
        Game.playerList.add(p3);
        Game.playerList.add(p4);
    }

    @After
    public void tearDown() {
        game = null;
        turn = null;
        p1 = null;
        p2 = null;
        p3 = null;
        p4 = null;
    }

    @Test
    public final void test_Jail_doublesToLeaveJail() {
        p1.changeJailStatus();
        dice = new int[]{1,1};
        assertTrue("Ensure player1 in jail to begin with", p1.getJailStatus());
        turn.tryLeaveJail(p1,Game.playerList,dice);
        assertFalse("Ensure player1 out of jail now", p1.getJailStatus());
    }

    @Test
    public final void test_Jail_3DoublesInJail_ForcedToPayBail() {
        p1.changeJailStatus();
        p1.setTurnsInJail(2);
        int balanceBefore = p1.getAccountBalance();
        dice = new int[]{1,2}; //not a double
        assertTrue("Ensure player1 in jail to begin with", p1.getJailStatus());
        turn.tryLeaveJail(p1,Game.playerList,dice);
        assertFalse("Ensure player1 out of jail", p1.getJailStatus());
        assertEquals("Ensure 50 poorer now", balanceBefore-50,p1.getAccountBalance());
    }

    @Test
    public final void test_Jail_UseGOOJFCard() {
        p1.changeJailStatus();
        p1.setGetOutOfJailFreeCard(1);
        assertTrue("Ensure player in jail before", p1.getJailStatus());
        assertEquals("Ensure player has 1 card before", 1, p1.getGetOutOfJailFreeCard());
        turn.tryUseGOOJFCard(p1);
        assertFalse("Ensure player not in jail after", p1.getJailStatus());
        assertEquals("Ensure player has 0 card after", 0, p1.getGetOutOfJailFreeCard());
    }

    @Test
    public final void test_Jail_tryPostBail() {
        p1.changeJailStatus();
        assertTrue("Ensure player in jail before", p1.getJailStatus());
        int balanceBefore = p1.getAccountBalance();
        turn.tryPostBail(p1);
        assertFalse("Ensure player not in jail after", p1.getJailStatus());
        assertEquals("Ensure player has 50 less after", balanceBefore-50, p1.getAccountBalance());
    }
}
