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
import jwrc.board.Sites;
import jwrc.game.Game;
import jwrc.game.PropertyOverlord;
import jwrc.player.Player;

public class SitesTest {
	
	static Game game;
	static Sites site, site2;
	static Player p1,p2,p3;
	
	
	@BeforeClass
	public static void BeforeClass() {
	}
	
	@Before
	public void setUp(){
		
		game = Game.getInstance();
		game.board = new Board();
		site = (Sites)Board.spaces.get(1);
		site2 = (Sites)Board.spaces.get(3);
		p1 = new Player("Ronan");
		p2 = new Player("James");
		p3 = new Player("Gavin");
		Game.playerList = new ArrayList<>(Arrays.asList(p1,p2,p3));
	}
	@After
	public void tearDown() {
		site = null;
		site2 = null;
		game = null;
		p1 = null;
		p2 = null;
		p3 = null;
		Game.playerList = null;
	}
	
	@AfterClass
	public static void AfterClass(){
		System.out.println("AfterClass");
	}
	
	@Test
	public final void getHouseCostTest() {
		assertEquals("test house cost return method",50,site.getHouseCost());
	}
	
	@Test
	public final void takeActionTest_Unowned_Site() {
		System.out.println("(TEST INSTRUCTION)--Test case when the site is unowned. Enter 'y' to buy site---");
		site.takeAction(p1, Game.playerList);
		assertEquals("account balance reduce by cost of site(med avenue: $60)",1440,p1.getAccountBalance());
		assertEquals("site owner updated","Ronan",site.getOwner());
	}
	
	@Test
	public final void takeActionTest_You_Own_site() {
		
		site.buySite(p1, 10); // buy site for 10
		site.takeAction(p1, Game.playerList);
		assertEquals("No action needed when you own the site, Balance remains unchanged",1490,p1.getAccountBalance());
	}
	
	@Test
	public final void takeActionTest_Site_Owned_By_Another_Player() {
		
		site.buySite(p1, 10); // buy site for 10
		site.takeAction(p2, Game.playerList);
		assertEquals("rent charged when landing on unowned site",1498,p2.getAccountBalance());
	}
	
	@Test
	public final void takeActionTest_Site_Mortgaged_by_another_player() {
		
		site.buySite(p1, 60); // buy site for 60
		PropertyOverlord.mortgageProperty(p1, 1);
		site.takeAction(p2, Game.playerList);
		assertEquals("No rent when site is mortgaged",1500,p2.getAccountBalance());
	}
	
	@Test
	public final void colourGroupCheckTest() {  // rent index increased when a colour set is owned by a player.
		site.buySite(p1, 60);
		assertEquals("lowest rent when only 1 brown site is owned",2,site.getRentCost());
		site2.buySite(p1, 60); // rent increase as colour set is now owned by Player p1.
		assertEquals("rent increased when colour set is owned",4,site.getRentCost());
	}
	
	@Test
	public final void getRentCostTest_with_Add_and_RemoveHouseTests() {
		assertEquals("test rent cost for 0 houses",2,site.getRentCost());
		site.addHouse();
		assertEquals("test rent cost for 1 houses",4,site.getRentCost());
		site.removeHouse();
		assertEquals("test rent cost for 0 houses",2,site.getRentCost());
	}
	
	@Test
	public final void getNoOfHousesTest_with_Add_and_RemoveHouseTests() {
		assertEquals("test rent cost for 0 houses",2,site.getRentCost());
		site.addHouse();
		assertEquals("test adding a house to site",1,site.getNoOfHouses());
		site.addHouse();
		site.addHouse();
		assertEquals("test after houses are added",3,site.getNoOfHouses());
		site.removeHouse();
		assertEquals("remove house and check cost",2,site.getNoOfHouses());
	}

}
