package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jwrc.board.Board;
import jwrc.board.TransportSpaces;
import jwrc.game.Game;
import jwrc.player.Player;

public class TransportSpacesTest {
	
	static Game game;
	static TransportSpaces ts1, ts2;
	static Player p1,p2;

	@BeforeClass
	public static void setUpBeforeClass(){
	}

	@AfterClass
	public static void tearDownAfterClass(){
	}

	@Before
	public void setUp() {
		game = Game.getInstance();
		game.board = new Board();
		ts1 = (TransportSpaces)Board.spaces.get(5);
		ts2 = (TransportSpaces)Board.spaces.get(15);
		p1 = new Player("Ronan");
		p2 = new Player("James");
		Game.playerList = new ArrayList<>(Arrays.asList(p1,p2));
	}

	@After
	public void tearDown(){
		ts1 = null;
		ts2 = null;
		game = null;
		p1 = null;
		p2 = null;
		Game.playerList = null;
	}

	@Test
	public final void takeActionTest_buy_transport_and_test_rent() {
		System.out.println("(TEST INSTRUCTION)--Enter 'y' to buy first Transport");
		ts1.takeAction(p1, Game.playerList);
		assertEquals("account balance reduce by cost of Transport($200)",1300,p1.getAccountBalance());
		assertEquals("site owner updated","Ronan",ts1.getOwner());
		
		ts1.takeAction(p2, Game.playerList);
		assertEquals("$25 rent recieved by p2 landing on transport space",1325,p1.getAccountBalance());
		assertEquals("$25 rent charged for landing on transport",1475,p2.getAccountBalance());
		
		System.out.println("(TEST INSTRUCTION)--Enter 'y' to buy second Transport");
		ts2.takeAction(p1, Game.playerList);
		ts2.takeAction(p2, Game.playerList);
		assertEquals("transport rent incresed when 2 transports are owned",1425,p2.getAccountBalance());
		ts1.takeAction(p2, Game.playerList);
		assertEquals("transport rent incresed when 2 transports are owned",1375,p2.getAccountBalance());
	}

}
