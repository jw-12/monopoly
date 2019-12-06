package test;
import jwrc.board.Board;
import jwrc.board.Property;
import jwrc.board.Sites;
import jwrc.game.PropertyOverlord;
import jwrc.player.Player;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PropertyOverlordTest {
	
	static Board board;
	static String name;
	static Player player;
	static Sites site1, site2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		name = "Ronan";
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		board = null;
		name = null;
	}

	@Before
	public void setUp() throws Exception {
		player = new Player(name);
		board = new Board();
		site1 = (Sites)Board.spaces.get(1);
		site2 = (Sites)Board.spaces.get(3);
		PropertyOverlord.numOfHouses = 32; // must instantiate at the start of every test method as its static variable.
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		board = null;
		site1 = null;
		site2 = null;
		System.out.println("Tear Down");
	}

	@Test
	public final void testMortgageProperty_PlayerActive_and_AccountBalance() {
		Property prop = (Property)Board.spaces.get(1);
		assertFalse("boolean mortgageActive should be set to false as no mortgage is active",prop.mortgageActive);
		assertEquals("balance before mortgage",1500,player.getAccountBalance());
		PropertyOverlord.mortgageProperty(player, 1);
		assertTrue("boolean mortgageActive should be set to true when property is mortgaged",prop.mortgageActive);
		assertEquals("balance increase after mortgage",1530,player.getAccountBalance());	
	}
	
	@Test
	public final void mixTest_MortgageProperty_and_LiftMortgage() {
		Property prop = (Property)Board.spaces.get(1);	
		PropertyOverlord.mortgageProperty(player, 1);
		assertTrue("boolean mortgageActive should be set to true when property is mortgaged",prop.mortgageActive);
		assertEquals("balance increase after mortgage",1530,player.getAccountBalance());
		PropertyOverlord.liftMortgage(player, 1);
		assertFalse("boolean mortgageActive should be set to false as the mortgage has been lifted",prop.mortgageActive);
		assertEquals("mortage lifted for extra 10% of mortgage value",1497,player.getAccountBalance());
	}

	@Test
	public final void testbuildHouse_when_you_do_not_own_property() {
		assertEquals("0 houses when no houses are built",0,site1.noOfHouses);
		PropertyOverlord.buildHouse(player, 1); // try to build house when you do not own the site.
		assertEquals("number of houses should not increase as I do not own the site yet",0,site1.noOfHouses);
		
	}
	@Test
	public final void testbuildHouse_when_you_do_not_own_colour_set() {
		site1.buySite(player, 60);
		PropertyOverlord.buildHouse(player, 1); // try to build house when you do not own the site.
		assertEquals("number of houses should not increase as I do not own colour set",0,site1.noOfHouses);
	}

	
	@Test
	public final void testbuildHouse_when_you_have_colour_set() {
		site1.buySite(player, 60); // buy both brown sites
		site2.buySite(player, 60);
		assertEquals("0 houses when no houses are built",0,site1.noOfHouses);
		PropertyOverlord.buildHouse(player, 1); // build house on site1
		assertEquals("house count increases when house is built under propper conditions",1,site1.noOfHouses);
		assertEquals("players account balance is reduced by buying 2 sites and a house",1330,player.getAccountBalance());
		assertEquals("the total number of houses is reduced by 1",31,PropertyOverlord.numOfHouses);
	}
	
	@Test
	public final void testsellHouse() {
		site1.buySite(player, 60); // buy both brown sites
		site2.buySite(player, 60);
		PropertyOverlord.buildHouse(player, 1);// build house on site1
		assertEquals("house count increases when house is built under propper conditions",1,site1.noOfHouses);
		assertEquals("players account balance is reduced by buying 2 sites and a house",1330,player.getAccountBalance());
		assertEquals("the total number of houses is reduced by 1",31,PropertyOverlord.numOfHouses);
		PropertyOverlord.sellHouse(player, 1);
		System.out.println("err: "+ PropertyOverlord.numOfHouses);
		assertEquals("house count reduced when house is sold",0,site1.noOfHouses);
		assertEquals("players account balance increase by half the house cost of $50",1355,player.getAccountBalance());
		assertEquals("the total number of houses is increased by 1",32,PropertyOverlord.numOfHouses);
	}
	
	@Test
	public final void test_buyHotel_and_sellHotel() {
		site1.buySite(player, 60); // buy both brown sites
		site2.buySite(player, 60);
		PropertyOverlord.buildHotel(player, 1);
		assertFalse("build hotel will fail if there is not 4 houses on the all sites of that colour group",site1.hasHotel);
		site1.noOfHouses = 4;
		site2.noOfHouses = 4;
		PropertyOverlord.buildHotel(player, 1);
		assertTrue("hotel will build when both sites have 4 houses",site1.hasHotel);
		PropertyOverlord.buildHouse(player, 1);
		assertEquals("cant build a house when you have a hotel",0,site1.getNoOfHouses());
		PropertyOverlord.sellHotel(player, 1);
		assertFalse("hotel sold from site",site1.hasHotel);
	}
	
}
