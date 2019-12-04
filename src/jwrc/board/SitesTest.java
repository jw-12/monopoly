package jwrc.board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import jwrc.game.Game;
import jwrc.player.Player;

public class SitesTest {
	
	static Game game;
	static Sites site;
	static Sites site2;
	static ArrayList <Player> playerList;
	Player p1 = new Player("Ronan");
	Player p2 = new Player("James");
	Player p3 = new Player("Gavin");
	
	
	@BeforeClass
	public static void BeforeClass() {
		System.out.println("BeforeClass");
	}
	
	@Before
	public void setUp() throws Exception{
		
		//game = new Game();
		//board = new Board();
		site = (Sites)Board.spaces.get(1);
		site2 = (Sites)Board.spaces.get(3);
		p1 = new Player("Ronan");
		p2 = new Player("James");
		p3 = new Player("Gavin");
		playerList = new ArrayList<Player>();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
	}
	@After
	public void tearDown() {
		site = null;
		site2 = null;
		playerList = null;
		game = null;
		p1 = null;
		p2 = null;
		p3 = null;
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
	public final void takeActionTest() {
		
		site.takeAction(p1, playerList);
		assertEquals(1440,p1.getAccountBalance());
	}
	
	
	@Test
	public final void colourGroupCheckTest() {  // rent index increased when a colour set is owned by a player.
		site.buySite(p1, 60);
		assertEquals("lowest rent when only 1 brown site is owned",2,site.getRentCost());
		site2.buySite(p1, 60); // rent increase as colour set is now owned by Player p1.
		
		System.out.println("site1: "+site.getOwner()+" site2: "+site2.getOwner());
		assertEquals("rent increased when colour set is owned",4,site2.getRentCost());
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
