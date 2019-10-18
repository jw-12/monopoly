package jwrc.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Board {

	BoardSpace[] propertyBoard;
	ArrayList<BoardSpace> testBoard;
	public static ArrayList<Sites> sitesBoard;
	public static Map<String, ArrayList<Sites>> map;
	public static ArrayList<BoardSpace> chanceBoard;
	public static ArrayList<BoardSpace> spaces;

    
    public Board() {
    	this.propertyBoard = new BoardSpace[5];
    	this.testBoard = new ArrayList<BoardSpace>();
    	Board.sitesBoard = new ArrayList<Sites>();
    	Board.map = new HashMap<String, ArrayList<Sites>>();
    	spaces = generateSpaces();
    }

    public ArrayList<BoardSpace> generateSpaces() {
		return (
				new ArrayList<>(Arrays.asList(
					new BlankSpace(0),
					new Sites("Mediterranean Avenue", 60, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50),
					new CommunityChest(2),
					new Sites("Baltic Avenue", 60, 3, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50),
					new Penalties(4, 200, "Income Tax"),
					new TransportSpaces("Reading Railroad", 200, 5),
					new Sites("Oriental Avenue", 100, 6, "Blue", new int[]{2, 4, 10, 30, 90, 160, 250}, 100),
					new Chance(7),
					new Sites("Vermont Avenue", 100, 8, "Blue", new int[]{2, 4, 10, 30, 90, 160, 250}, 100),
					new Sites("Connecticut Avenue", 120, 9, "Blue", new int[]{2, 4, 10, 30, 90, 160, 250}, 100),
					new BlankSpace(10),
					new Sites("St. Charles Place", 140, 11, "Pink", new int[]{2, 4, 10, 30, 90, 160, 250}, 150),
					new Utility("Electric Company", 150, 12),
					new Sites("States Avenue", 140, 13, "Pink", new int[]{2, 4, 10, 30, 90, 160, 250}, 150),
					new Sites("Virginia Avenue", 160, 14, "Pink", new int[]{2, 4, 10, 30, 90, 160, 250}, 150),
					new TransportSpaces("Pennsylvania Railroad", 200, 15),
					new Sites("St James Place", 180, 16, "Orange", new int[]{2, 4, 10, 30, 90, 160, 250}, 200),
					new CommunityChest(17),
					new Sites("Tennessee Avenue", 180, 18, "Orange", new int[]{2, 4, 10, 30, 90, 160, 250}, 200),
					new Sites("New York Avenue", 200, 19, "Orange", new int[]{2, 4, 10, 30, 90, 160, 250}, 200),
					new BlankSpace(20),
					new Sites("Kentucky Avenue", 220, 21, "Red", new int[]{2, 4, 10, 30, 90, 160, 250}, 250),
					new Chance(22),
					new Sites("Indiana Avenue", 220, 23, "Red", new int[]{2, 4, 10, 30, 90, 160, 250}, 250),
					new Sites("Illinois Avenue", 240, 24, "Red", new int[]{2, 4, 10, 30, 90, 160, 250}, 250),
					new TransportSpaces("B. & O. Railroad", 200, 25),
					new Sites("Atlantic Avenue", 260, 26, "Yellow", new int[]{2, 4, 10, 30, 90, 160, 250}, 300),
					new Sites("Ventnor Avenue", 260, 27, "Yellow", new int[]{2, 4, 10, 30, 90, 160, 250}, 300),
					new Utility("Water Works", 150, 28),
					new Sites("Marvin Gardens", 280, 29, "Yellow", new int[]{2, 4, 10, 30, 90, 160, 250}, 300),
					new GoToJail(30),
					new Sites("Pacific Avenue", 300, 31, "Green", new int[]{2, 4, 10, 30, 90, 160, 250}, 300),
					new Sites("North Carolina Avenue", 300, 32, "Green", new int[]{2, 4, 10, 30, 90, 160, 250}, 300),
					new CommunityChest(33),
					new Sites("Pennsylvania Avenue", 320, 34, "Green", new int[]{2, 4, 10, 30, 90, 160, 250}, 300),
					new TransportSpaces("Short Line", 200, 35),
					new Chance(36),
					new Sites("Park Place", 350, 31, "Purple", new int[]{2, 4, 10, 30, 90, 160, 250}, 300),
					new Penalties(38, 75, "Luxury Tax"),
					new Sites("BoardWalk", 350, 31, "Purple", new int[]{2, 4, 10, 30, 90, 160, 250}, 400)
				)
			)
		);
	}

    public void setTestBoard() {


    	BoardSpace b0 = new BlankSpace(0);
    	//BoardSpace b1 = new Sites("Medeteranian Avenue",60,1,"Brown",new int[] { 2,4,10,30,90,160,250},50) ;
    	BoardSpace b2 = new BlankSpace(2);
    	//BoardSpace b3 = new Sites("Baltic Avenue",60,3,"Brown",new int[] { 2,4,10,30,90,160,250},50);
    	BoardSpace b4 = new Penalties(4, 100, "Income Tax");
    	BoardSpace b5 = new TransportSpaces("Reading Railroad", 200, 5);
    	//BoardSpace b6 = new Sites("Oriantal Avenue",100,6,"Blue",new int[] { 2,4,10,30,90,160,250}, 100) ;
    	BoardSpace b7 = new BlankSpace(7);
    	//BoardSpace b8 = new Sites("Vermont Avenue",100,8,"Blue",new int[] { 2,4,10,30,90,160,250}, 100) ;
    	//BoardSpace b9 = new Sites("Connecticut Avenue",120,9,"Blue",new int[] { 2,4,10,30,90,160,250}, 100) ;

    	BoardSpace b10 = new BlankSpace(10);
    	//BoardSpace b11 = new Sites("St Charles Place",140,11,"Pink",new int[] { 2,4,10,30,90,160,250}, 150);
    	BoardSpace b12 = new Utility("Electric Company",150, 12);
    	//BoardSpace b13 = new Sites("States Avenue",140,13,"Pink",new int[] { 2,4,10,30,90,160,250},150);
    	//BoardSpace b14 = new Sites("Virginia Avenue",160,14,"Pink",new int[] { 2,4,10,30,90,160,250}, 150);
    	BoardSpace b15 = new TransportSpaces("Pennsylvania Railroad", 200, 15);
    	//BoardSpace b16 = new Sites("St Jamses Place",180,16,"Orange",new int[] { 2,4,10,30,90,160,250},200);
    	BoardSpace b17 = new BlankSpace(17);
    	//BoardSpace b18 = new Sites("Tennessee Avenue",180,18,"Orange",new int[] { 2,4,10,30,90,160,250},200);
    	//BoardSpace b19 = new Sites("New York Avenue",200,19,"Orange",new int[] { 2,4,10,30,90,160,250},200);

    	BoardSpace b20 = new BlankSpace(20);
    	//BoardSpace b21 = new Sites("Kentucky Avenue",220,21,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	BoardSpace b22 = new BlankSpace(22);
    	//BoardSpace b23 = new Sites("Indiana Avenue",220,23,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	//BoardSpace b24 = new Sites("Illinois Avenue",240,24,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	BoardSpace b25 = new TransportSpaces("B. & O. Railroad", 200, 25);
    	//BoardSpace b26 = new Sites("Atlantic Avenue",260,26,"Yellow",new int[] { 2,4,10,30,90,160,250},300);
    	//BoardSpace b27 = new Sites("Ventnor Avenue",260,27,"Yellow",new int[] { 2,4,10,30,90,160,250},300);
    	BoardSpace b28 = new Utility("Water Works", 150, 28);
    	//BoardSpace b29 = new Sites("Marvin Gardens",280,29,"Yellow",new int[] { 2,4,10,30,90,160,250},300);

    	BoardSpace b30 = new BlankSpace(30);
    	//BoardSpace b31 = new Sites("Pecific Avenue",300,31,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	//BoardSpace b32 = new Sites("North Carolina Avenue",300,32,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	BoardSpace b33 = new BlankSpace(33);
    	//BoardSpace b34 = new Sites("Pennsylvania Avenue",320,34,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	BoardSpace b35 = new TransportSpaces("Short Line", 200, 35);
    	BoardSpace b36 = new BlankSpace(36);
    	//BoardSpace b37 = new Sites("Park Place",350,31,"Purple",new int[] { 2,4,10,30,90,160,250},300);
    	BoardSpace b38 = new Penalties(38, 100, "Income Tax");
    	//BoardSpace b39 = new Sites("Park Place",350,39,"Purple",new int[] { 2,4,10,30,90,160,250},300);


    	Sites b1 = new Sites("Medeteranian Avenue",60,1,"Brown",new int[] { 2,4,10,30,90,160,250},50);
    	Sites b3 = new Sites("Baltic Avenue",60,3,"Brown",new int[] { 2,4,10,30,90,160,250},50);
    	Sites b6 = new Sites("Oriantal Avenue",100,6,"Blue",new int[] { 2,4,10,30,90,160,250}, 100);
    	Sites b8 = new Sites("Vermont Avenue",100,8,"Blue",new int[] { 2,4,10,30,90,160,250}, 100);
    	Sites b9 = new Sites("Connecticut Avenue",120,9,"Blue",new int[] { 2,4,10,30,90,160,250}, 100) ;
    	Sites b11 = new Sites("St Charles Place",140,11,"Pink",new int[] { 2,4,10,30,90,160,250}, 150);
    	Sites b13 = new Sites("States Avenue",140,13,"Pink",new int[] { 2,4,10,30,90,160,250},150);
    	Sites b14 = new Sites("Virginia Avenue",160,14,"Pink",new int[] { 2,4,10,30,90,160,250}, 150);
    	Sites b16 = new Sites("St Jamses Place",180,16,"Orange",new int[] { 2,4,10,30,90,160,250},200);
    	Sites b18 = new Sites("Tennessee Avenue",180,18,"Orange",new int[] { 2,4,10,30,90,160,250},200);
    	Sites b19 = new Sites("New York Avenue",200,19,"Orange",new int[] { 2,4,10,30,90,160,250},200);
    	Sites b21 = new Sites("Kentucky Avenue",220,21,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	Sites b23 = new Sites("Indiana Avenue",220,23,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	Sites b24 = new Sites("Illinois Avenue",240,24,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	Sites b26 = new Sites("Atlantic Avenue",260,26,"Yellow",new int[] { 2,4,10,30,90,160,250},300);
    	Sites b27 = new Sites("Ventnor Avenue",260,27,"Yellow",new int[] { 2,4,10,30,90,160,250},300);
    	Sites b29 = new Sites("Marvin Gardens",280,29,"Yellow",new int[] { 2,4,10,30,90,160,250},300);
    	Sites b31 = new Sites("Pecific Avenue",300,31,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	Sites b32 = new Sites("North Carolina Avenue",300,32,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	Sites b34 = new Sites("Pennsylvania Avenue",320,34,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	Sites b37 = new Sites("Park Place",350,31,"Purple",new int[] { 2,4,10,30,90,160,250},300);
    	Sites b39 = new Sites("Park Place",350,39,"Purple",new int[] { 2,4,10,30,90,160,250},300);

    	ArrayList<Sites> brown = new ArrayList<Sites>();
    	brown.add(b1);
        brown.add(b3);

        ArrayList<Sites> blue = new ArrayList<Sites>();
    	blue.add(b6);
        blue.add(b8);
        blue.add(b9);

        ArrayList<Sites> pink = new ArrayList<Sites>();
        pink.add(b11);
        pink.add(b13);
        pink.add(b14);

        ArrayList<Sites> orange = new ArrayList<Sites>();
    	orange.add(b16);
        orange.add(b18);
        orange.add(b19);

        ArrayList<Sites> red = new ArrayList<Sites>();
    	red.add(b21);
        red.add(b23);
        red.add(b24);

        ArrayList<Sites> yellow = new ArrayList<Sites>();
    	yellow.add(b26);
        yellow.add(b27);
        yellow.add(b29);

        ArrayList<Sites> green = new ArrayList<Sites>();
    	green.add(b31);
        green.add(b32);
        green.add(b34);

        ArrayList<Sites> purple = new ArrayList<Sites>();
    	purple.add(b37);
        purple.add(b39);

        Board.map.put("Brown", brown);
        Board.map.put("Blue", blue);
        Board.map.put("Pink", pink);
        Board.map.put("Orange", orange);
        Board.map.put("Red", red);
        Board.map.put("Yellow", yellow);
        Board.map.put("Green", green);
        Board.map.put("Purple", purple);


    	Board.sitesBoard.add(b1);
    	Board.sitesBoard.add(b3);
    	Board.sitesBoard.add(b6);
    	Board.sitesBoard.add(b8);
    	Board.sitesBoard.add(b9);
    	Board.sitesBoard.add(b11);
    	Board.sitesBoard.add(b13);
    	Board.sitesBoard.add(b14);
    	Board.sitesBoard.add(b16);
    	Board.sitesBoard.add(b18);
    	Board.sitesBoard.add(b19);
    	Board.sitesBoard.add(b21);
    	Board.sitesBoard.add(b23);
    	Board.sitesBoard.add(b24);
    	Board.sitesBoard.add(b26);
    	Board.sitesBoard.add(b27);
    	Board.sitesBoard.add(b29);
    	Board.sitesBoard.add(b31);
    	Board.sitesBoard.add(b32);
    	Board.sitesBoard.add(b34);
    	Board.sitesBoard.add(b37);
    	Board.sitesBoard.add(b39);

    	this.testBoard.add(b0);
    	this.testBoard.add(b1);
    	this.testBoard.add(b2);
    	this.testBoard.add(b3);
    	this.testBoard.add(b4);
    	this.testBoard.add(b5);
    	this.testBoard.add(b6);
    	this.testBoard.add(b7);
    	this.testBoard.add(b8);
    	this.testBoard.add(b9);
    	this.testBoard.add(b10);
    	this.testBoard.add(b11);
    	this.testBoard.add(b12);
    	this.testBoard.add(b13);
    	this.testBoard.add(b14);
    	this.testBoard.add(b15);
    	this.testBoard.add(b16);
    	this.testBoard.add(b17);
    	this.testBoard.add(b18);
    	this.testBoard.add(b19);
    	this.testBoard.add(b20);
    	this.testBoard.add(b21);
    	this.testBoard.add(b22);
    	this.testBoard.add(b23);
    	this.testBoard.add(b24);
    	this.testBoard.add(b25);
    	this.testBoard.add(b26);
    	this.testBoard.add(b27);
    	this.testBoard.add(b28);
    	this.testBoard.add(b29);
    	this.testBoard.add(b30);
    	this.testBoard.add(b31);
    	this.testBoard.add(b32);
    	this.testBoard.add(b33);
    	this.testBoard.add(b34);
    	this.testBoard.add(b35);
    	this.testBoard.add(b36);
    	this.testBoard.add(b37);
    	this.testBoard.add(b38);
    	this.testBoard.add(b39);
    }


   /* public void setSitesBoard() {

    	Sites b0 = new Sites("Medeteranian Avenue",60,1,"Brown",new int[] { 2,4,10,30,90,160,250},50);
    	Sites b1 = new Sites("Baltic Avenue",60,3,"Brown",new int[] { 2,4,10,30,90,160,250},50);
    	Sites bl0 = new Sites("Oriantal Avenue",100,6,"Blue",new int[] { 2,4,10,30,90,160,250}, 100);
    	Sites bl1 = new Sites("Vermont Avenue",100,8,"Blue",new int[] { 2,4,10,30,90,160,250}, 100);
    	Sites bl2 = new Sites("Connecticut Avenue",120,9,"Blue",new int[] { 2,4,10,30,90,160,250}, 100) ;
    	Sites p0 = new Sites("St Charles Place",140,11,"Pink",new int[] { 2,4,10,30,90,160,250}, 150);
    	Sites p1 = new Sites("States Avenue",140,13,"Pink",new int[] { 2,4,10,30,90,160,250},150);
    	Sites p2 = new Sites("Virginia Avenue",160,14,"Pink",new int[] { 2,4,10,30,90,160,250}, 150);
    	Sites o0 = new Sites("St Jamses Place",180,16,"Orange",new int[] { 2,4,10,30,90,160,250},200);
    	Sites o1 = new Sites("Tennessee Avenue",180,18,"Orange",new int[] { 2,4,10,30,90,160,250},200);
    	Sites o2 = new Sites("New York Avenue",200,19,"Orange",new int[] { 2,4,10,30,90,160,250},200);
    	Sites r0 = new Sites("Kentucky Avenue",220,21,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	Sites r1 = new Sites("Indiana Avenue",220,23,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	Sites r2 = new Sites("Illinois Avenue",240,24,"Red",new int[] { 2,4,10,30,90,160,250},250);
    	Sites y0 = new Sites("Atlantic Avenue",260,26,"Yellow",new int[] { 2,4,10,30,90,160,250},300);
    	Sites y1 = new Sites("Ventnor Avenue",260,27,"Yellow",new int[] { 2,4,10,30,90,160,250},300);
    	Sites y2 = new Sites("Marvin Gardens",280,29,"Yellow",new int[] { 2,4,10,30,90,160,250},300);
    	Sites g0 = new Sites("Pecific Avenue",300,31,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	Sites g1 = new Sites("North Carolina Avenue",300,32,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	Sites g2 = new Sites("Pennsylvania Avenue",320,34,"Green",new int[] { 2,4,10,30,90,160,250},300);
    	Sites pu0 = new Sites("Park Place",350,31,"Purple",new int[] { 2,4,10,30,90,160,250},300);
    	Sites pu1 = new Sites("Park Place",350,39,"Purple",new int[] { 2,4,10,30,90,160,250},300);


    	ArrayList<Sites> brown = new ArrayList<Sites>();
    	brown.add(b0);
        brown.add(b1);

        ArrayList<Sites> blue = new ArrayList<Sites>();
    	blue.add(bl0);
        blue.add(bl1);
        blue.add(bl2);

        ArrayList<Sites> pink = new ArrayList<Sites>();
        pink.add(p0);
        pink.add(p1);
        pink.add(p2);

        ArrayList<Sites> orange = new ArrayList<Sites>();
    	orange.add(o0);
        orange.add(o1);
        orange.add(o2);

        ArrayList<Sites> red = new ArrayList<Sites>();
    	red.add(r0);
        red.add(r1);
        red.add(r2);

        ArrayList<Sites> yellow = new ArrayList<Sites>();
    	yellow.add(y0);
        yellow.add(y1);
        yellow.add(y2);

        ArrayList<Sites> green = new ArrayList<Sites>();
    	green.add(g0);
        green.add(g1);
        green.add(g2);

        ArrayList<Sites> purple = new ArrayList<Sites>();
    	purple.add(bl0);
        purple.add(bl1);

        Board.map.put("Brown", brown);
        Board.map.put("Blue", blue);
        Board.map.put("Pink", pink);
        Board.map.put("Orange", orange);
        Board.map.put("Red", red);
        Board.map.put("Yellow", yellow);
        Board.map.put("Green", green);
        Board.map.put("Purple", purple);

    	Board.sitesBoard.add(b0);
    	Board.sitesBoard.add(b1);
    	Board.sitesBoard.add(bl0);
    	Board.sitesBoard.add(bl1);
    	Board.sitesBoard.add(bl2);
    	Board.sitesBoard.add(p0);
    	Board.sitesBoard.add(p1);
    	Board.sitesBoard.add(p2);
    	Board.sitesBoard.add(o0);
    	Board.sitesBoard.add(o1);
    	Board.sitesBoard.add(o2);
    	Board.sitesBoard.add(r0);
    	Board.sitesBoard.add(r1);
    	Board.sitesBoard.add(r2);
    	Board.sitesBoard.add(y0);
    	Board.sitesBoard.add(y1);
    	Board.sitesBoard.add(y2);
    	Board.sitesBoard.add(g0);
    	Board.sitesBoard.add(g1);
    	Board.sitesBoard.add(g2);
    	Board.sitesBoard.add(pu0);
    	Board.sitesBoard.add(pu1);

    	Board.map.put("Brown", brown);
        Board.map.put("Blue", blue);
        Board.map.put("Pink", pink);
        Board.map.put("Orange", orange);
        Board.map.put("Red", red);
        Board.map.put("Yellow", yellow);
        Board.map.put("Green", green);
        Board.map.put("Purple", purple);
    }*/


}