package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jwrc.board.*;
import jwrc.game.Game;
import jwrc.player.PaymentType;
import jwrc.player.Player;
import org.junit.*;

public class PlayerTest {
	
	static String name;
	static Player player;
	static Game game;
	static Board board;

	@BeforeClass
	public static void BeforeClass() {
		name = "Ronan";
		System.out.println("BeforeClass");
		board = new Board();
	}
	
	@Before
	public void setUp() throws Exception{
		player = new Player(name);
		System.out.println("SetUp");
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
		assertTrue(player.getJailStatus());
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
	public final void changedTransportsOwnedTestaddTransport() {
		assertEquals(0,player.getTransportsOwned());
		player.changeTransportsOwned(1);
		assertEquals(1,player.getTransportsOwned());
	}
	
	@After
	public void tearDown() {
		player = null;
		game = null;
		Game.playerList = null;
	}
	
	@AfterClass
	public static void AfterClass(){
		name = null;
		game = null;
		Game.playerList = null;
		System.out.println("AfterClass");
		board = null;
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

	@Test
	public void test_payToPlayer_transferOfProperites() {
		game = Game.getInstance();
		Sites s1 = new Sites("Mediterranean Avenue", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50);
		Sites s2 = new Sites("Other Ave", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50);
		Utility u1 = new Utility("Water Works", 75, 28);
		Utility u2 = new Utility("Other Works", 75, 28);

		Player owed = new Player("OWED");
		Player payer = new Player("PAYER");

		Game.playerList = new ArrayList<>();
		Game.playerList.add(payer);
		Game.playerList.add(owed);

		ArrayList<Property> allProp = new ArrayList<>();
		allProp.add(s1);
		allProp.add(u1);
		allProp.add(s2);
		allProp.add(u2);

		owed.addProperty(s1);
		owed.addProperty(u1);
		payer.addProperty(s2);
		payer.addProperty(u2);

		owed.payToPlayer(payer, 1501);

		assertEquals("All properties transferred to owed-player if payer can't pay:", allProp, owed.getPropertiesOwned());

	}

}
