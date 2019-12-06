package test;

import jwrc.board.Board;
import jwrc.board.Sites;
import jwrc.game.Game;
import jwrc.game.PropertyOverlord;
import jwrc.game.Turn;
import jwrc.player.PaymentType;
import jwrc.player.Player;
import org.junit.*;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class EndGameTest {

    static Board board;
    static Game game;
    static Turn turn;
    static ArrayList<Integer> commDeck;
    static ArrayList<Integer> chanceDeck;
    static Player p1,p2,p3,p4;

    @BeforeClass
    public static void BeforeClass() {
        board = new Board();
        commDeck = new ArrayList<>();
        chanceDeck = new ArrayList<>();
        commDeck.add(0);
        chanceDeck.add(0);
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
        Game.playerList = new ArrayList<>();
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
        Game.playerList = null;
    }

    @Test
    public final void test_playerBrokeToPlayer_kickFromGame() {
        p1.addProperty((Sites)Board.spaces.get(1)); //assign property to player1
        ((Sites)Board.spaces.get(1)).changeOwner(p1.getName());
        p2.changeAccountBalance(-1499, PaymentType.BANK); //set balance to $1
        p3.changeAccountBalance(-1499, PaymentType.BANK); //set balance to $1
        p2.evaluatePosition(1);
        Turn.movePlayerForward(p2, Game.playerList);
        assertEquals("Ensure playerlist now of size 3", 3, Game.playerList.size());

        //p3.evaluatePosition(1);

    }
    
   
    @Test
    public final void test_endGame_evaluation(){
    	Sites site, site2,site3;
    	site = (Sites)Board.spaces.get(1);
		site2 = (Sites)Board.spaces.get(3);
		site3 = (Sites)Board.spaces.get(6);
		site.buySite(p1, 60);				//p1 buys both brown sites 
		site2.buySite(p1, 60);
		site3.buySite(p2, 100);
		PropertyOverlord.mortgageProperty(p2, 6);
		PropertyOverlord.buildHouse(p1, 1); //p1 builds a house on each site.
		PropertyOverlord.buildHouse(p1, 3);
		game.endGame();
		assertEquals("evaluate p1 with houses and sites",1500,p1.getAccountBalance());
		assertEquals("evaluate p2 with a mortgaged property",1500,p2.getAccountBalance());
    }
    
}
