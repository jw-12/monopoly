package jwrc.board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jwrc.player.Player;

public class SitesTest {
	
	
	static Sites site;
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
		site = new Sites("Mediterranean Avenue", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50);
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
		playerList = null;
		System.out.println("Tear Down");
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
