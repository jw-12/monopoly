package jwrc.player;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import jwrc.board.Property;
import jwrc.board.Sites;
import jwrc.board.TransportSpaces;

public class PlayerTest {
	
	static String name;
	static Player player;
	
	@BeforeClass
	public static void BeforeClass() {
		name = "Ronan";
		System.out.println("BeforeClass");
	}
	
	@Before
	public void setUp() throws Exception{
		player = new Player(name);
		System.out.println("SetUp");
	}
	
	@Test
	public final void evaluatePositionTest_Passing_GO() {
		
		player.evaluatePosition(20);
		assertEquals("No money added as we have not passed GO",1500,player.getAccountBalance());
		player.evaluatePosition(30);
		assertEquals("$200 added for passing GO",1700,player.getAccountBalance());
		assertEquals("board index loops back to 0 once a full loop is complete",10,player.getBoardIndex());
	}

	@Test
	public final void changeAccountBalancePlus() {
		
		player.changeAccountBalance(50, PaymentType.BANK);
		assertEquals("add 50 to original account balance",1550,player.getAccountBalance());
	}
	
	@Test
	public final void changeAccountBalanceMinus() {
		
		player.changeAccountBalance(-50, PaymentType.BANK);
		assertEquals("minus 50 from original account balance",1450,player.getAccountBalance());
	}
	
	@Test
	public final void sendToJailTestBoardIndex() {
		player.sendToJail();
		assertEquals(10,player.getBoardIndex());
	}
	
	@Test
	public final void jailStatusTest() {
		assertFalse("jail status false before sent to jail",player.getJailStatus());
		player.sendToJail();
		assertTrue("jail status true once sendToJail is called",player.getJailStatus());
		player.changeJailStatus();
		assertFalse("jail status inverted",player.getJailStatus());
		
	}
	
	
	@Test
	public final void addProperty_removePropertyTest_and_getPropertiesOwnedTest() {
		ArrayList<Property> ptest = new ArrayList<Property>();
		Sites site = new Sites("Mediterranean Avenue", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50);
		player.addProperty(site);
		ptest = player.getPropertiesOwned();
		assertEquals(1,ptest.size());
		player.removeProperty(site);
		assertEquals(0,ptest.size());
	}
	
	
	@Test
	public final void changedTransportsOwnedTest_adding_and_removing_Transports() {
		assertEquals("0 transports owned when none have been bought",0,player.getTransportsOwned());
		player.changeTransportsOwned(1);
		assertEquals("increase by 1 when transport is bought",1,player.getTransportsOwned());
		player.changeTransportsOwned(1);
		assertEquals("add another transport",2,player.getTransportsOwned());
		player.changeTransportsOwned(-1);
		assertEquals("remove a transport",1,player.getTransportsOwned());
	}
	
	
	@After
	public void tearDown() {
		player = null;
		System.out.println("Tear Down");
	}
	
	@AfterClass
	public static void AfterClass(){
		name = null;
		System.out.println("AfterClass");
	}

}
