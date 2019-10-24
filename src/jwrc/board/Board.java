package jwrc.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board {

	public static ArrayList<Sites> sitesBoard;
	public static HashMap<String, ArrayList<Sites>> map;
	public static ArrayList<BoardSpace> chanceBoard;
	public static ArrayList<BoardSpace> spaces;

    
    public Board() {
    	spaces = generateSpaces();
    	sitesBoard = setSitesBoard();
    	map = new HashMap<String,ArrayList<Sites>>();
    	setSiteMap();
    }

    public ArrayList<BoardSpace> generateSpaces() {
		return (
				new ArrayList<>(Arrays.asList(
					new BlankSpace(0),
					new Sites("Mediterranean Avenue", 60, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50),
					new BlankSpace(2),
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
					new Sites("Park Place", 350, 37, "Purple", new int[]{2, 4, 10, 30, 90, 160, 250}, 300),
					new Penalties(38, 75, "Luxury Tax"),
					new Sites("BoardWalk", 350, 39, "Purple", new int[]{2, 4, 10, 30, 90, 160, 250}, 400)
				)
			)
		);
	}

    public ArrayList<Sites> setSitesBoard() {
		return (
				new ArrayList<>(Arrays.asList(
					(Sites)spaces.get(1),
					(Sites)spaces.get(3),
					(Sites)spaces.get(6),
					(Sites)spaces.get(8),
					(Sites)spaces.get(9),
					(Sites)spaces.get(11),
					(Sites)spaces.get(13),
					(Sites)spaces.get(14),
					(Sites)spaces.get(16),
					(Sites)spaces.get(18),
					(Sites)spaces.get(19),
					(Sites)spaces.get(21),
					(Sites)spaces.get(23),
					(Sites)spaces.get(24),
					(Sites)spaces.get(26),
					(Sites)spaces.get(27),
					(Sites)spaces.get(29),
					(Sites)spaces.get(31),
					(Sites)spaces.get(32),
					(Sites)spaces.get(34),
					(Sites)spaces.get(37),
					(Sites)spaces.get(39)	
				)
			)
		);
    }
    
    public void setSiteMap() {
    	ArrayList<Sites> brown = new ArrayList<Sites>();
    	brown.add(sitesBoard.get(0));
    	brown.add(sitesBoard.get(1));
    	
    	ArrayList<Sites> blue = new ArrayList<Sites>();
    	blue.add(sitesBoard.get(2));
    	blue.add(sitesBoard.get(3));
    	blue.add(sitesBoard.get(4));
        

        ArrayList<Sites> pink = new ArrayList<Sites>();
        pink.add(sitesBoard.get(5));
        pink.add(sitesBoard.get(6));
        pink.add(sitesBoard.get(7));

        ArrayList<Sites> orange = new ArrayList<Sites>();
    	orange.add(sitesBoard.get(8));
    	orange.add(sitesBoard.get(9));
    	orange.add(sitesBoard.get(10));

        ArrayList<Sites> red = new ArrayList<Sites>();
    	red.add(sitesBoard.get(11));
    	red.add(sitesBoard.get(12));
    	red.add(sitesBoard.get(13));

        ArrayList<Sites> yellow = new ArrayList<Sites>();
    	yellow.add(sitesBoard.get(14));
    	yellow.add(sitesBoard.get(15));
    	yellow.add(sitesBoard.get(16));

        ArrayList<Sites> green = new ArrayList<Sites>();
    	green.add(sitesBoard.get(17));
    	green.add(sitesBoard.get(18));
    	green.add(sitesBoard.get(19));

        ArrayList<Sites> purple = new ArrayList<Sites>();
    	purple.add(sitesBoard.get(20));
    	purple.add(sitesBoard.get(21));
    	
    	
    	map.put("Brown", brown);
        map.put("Blue", blue);
        map.put("Pink", pink);
        map.put("Orange", orange);
        map.put("Red", red);
        map.put("Yellow", yellow);
        map.put("Green", green);
        map.put("Purple", purple);
    	
    }

}