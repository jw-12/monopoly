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
import jwrc.board.Utility;
import jwrc.game.Game;
import jwrc.player.Player;

public class UtilityTest {
	
	static Game game;
	static Utility utility1;
	static Utility utility2;
	static Player p1,p2,p3;

	@BeforeClass
	public static void setUpBeforeClass(){
	}

	@AfterClass
	public static void tearDownAfterClass(){
		game = null;
		Game.playerList = null;
	}

	@Before
	public void setUp(){
		game = Game.getInstance();
		game.board = new Board();
		utility1 = (Utility)Board.spaces.get(12);
		utility2 = (Utility)Board.spaces.get(28);
		p1 = new Player("Ronan");
		p2 = new Player("James");
		p3 = new Player("Gavin");
		Game.playerList = new ArrayList<>(Arrays.asList(p1,p2,p3));
	}

	@After
	public void tearDown(){
		utility1 = null;
		utility2 = null;
		game = null;
		Game.playerList = null;
		p1 = null;
		p2 = null;
		p3 = null;
	}
	

	@Test
	public final void takeActionTest_Unowned_Utility() {
		System.out.println("(TEST INSTRUCTION)--Enter 'y' to buy utility.Test case when the utility is unowned");
		utility1.takeAction(p1, Game.playerList);
		assertEquals("account balance reduce by cost of utility(Electric Company: $150)",1350,p1.getAccountBalance());
		assertEquals("site owner updated","Ronan",utility1.getOwner());
	}
	
	@Test
	public final void takeActionTest_You_Own_Utility() {
		
		System.out.println("(TEST INSTRUCTION)--Enter 'y' to buy utility. Testing landing on owned utility.");
		utility1.takeAction(p1, Game.playerList);
		utility1.takeAction(p1, Game.playerList);
		assertEquals("No action needed when you own the site, Balance remains unchanged",1350,p1.getAccountBalance());
	}
	
	@Test
	public final void takeActionTest_Utility_Owned_By_Another_Player() {
		
		System.out.println("(TEST INSTRUCTION)--Enter 'y' to buy utility. Testing landing utility owned by another player.");
		utility1.takeAction(p1, Game.playerList);
		p2.rollDice();
		int diceValue = p2.diceVal[0]+p2.diceVal[1];
		System.out.println("dice roll is: "+diceValue);
		utility1.takeAction(p2, Game.playerList);
		int p2NewBalance = 1500 - (diceValue*4);
		assertEquals("rent charged when landing on unowned site",p2NewBalance,p2.getAccountBalance());
		System.out.println("(TEST INSTRUCTION)--Enter 'y' to buy utility. Buy second utility and test monopoly rent increase.");
		utility2.takeAction(p1, Game.playerList);
		utility1.takeAction(p2, Game.playerList);
		p2NewBalance -= (diceValue*10);
		assertEquals("rent charged when landing on unowned site",p2NewBalance,p2.getAccountBalance());
	}
	
}
