package jwrc.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board {

	public static HashMap<String, ArrayList<Sites>> map;
	public static ArrayList<BoardSpace> spaces;

    
    public Board() {
    	spaces = generateSpaces();
    	map = new HashMap<String,ArrayList<Sites>>();
    	setSiteMap();
    }

    private ArrayList<BoardSpace> generateSpaces() {
		return (
				new ArrayList<>(Arrays.asList(
					new BlankSpace(0),
					new Sites("Mediterranean Avenue", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50),
					new CommunityChest(2),
					new Sites("Baltic Avenue", 30, 3, "Brown", new int[]{4, 8, 20, 60, 180, 320, 450}, 50),
					new Penalties(4, 200, "Income Tax"),
					new TransportSpaces("Reading Railroad", 100, 5),
					new Sites("Oriental Avenue", 50, 6, "Blue", new int[]{6, 12, 30, 90, 270, 400, 550}, 50),
					new Chance(7),
					new Sites("Vermont Avenue", 50, 8, "Blue", new int[]{6, 12, 30, 90, 270, 400, 550}, 50),
					new Sites("Connecticut Avenue", 60, 9, "Blue", new int[]{8, 16, 40, 100, 300, 450, 600}, 50),
					new BlankSpace(10),
					new Sites("St. Charles Place", 70, 11, "Pink", new int[]{10, 20, 50, 150, 450, 625, 750}, 100),
					new Utility("Electric Company", 75, 12),
					new Sites("States Avenue", 70, 13, "Pink", new int[]{10, 20, 50, 150, 450, 625, 750}, 100),
					new Sites("Virginia Avenue", 80, 14, "Pink", new int[]{12, 24, 60, 180, 500, 700, 900}, 100),
					new TransportSpaces("Pennsylvania Railroad", 100, 15),
					new Sites("St James Place", 90, 16, "Orange", new int[]{14, 28, 70, 200, 550, 750, 950}, 100),
					new CommunityChest(17),
					new Sites("Tennessee Avenue", 90, 18, "Orange", new int[]{14, 28, 70, 200, 550, 750, 950}, 100),
					new Sites("New York Avenue", 100, 19, "Orange", new int[]{16, 32, 80, 220, 600, 800, 1000}, 100),
					new BlankSpace(20),
					new Sites("Kentucky Avenue", 110, 21, "Red", new int[]{18, 36, 90, 250, 700, 875, 1050}, 150),
					new Chance(22),
					new Sites("Indiana Avenue", 110, 23, "Red", new int[]{18, 36, 90, 250, 700, 875, 1050}, 150),
					new Sites("Illinois Avenue", 120, 24, "Red", new int[]{20, 40, 100, 300, 750, 925, 1100}, 150),
					new TransportSpaces("B. & O. Railroad", 100, 25),
					new Sites("Atlantic Avenue", 130, 26, "Yellow", new int[]{22, 44, 100, 300, 800, 975, 1150}, 150),
					new Sites("Ventnor Avenue", 130, 27, "Yellow", new int[]{22, 44, 100, 300, 800, 975, 1150}, 150),
					new Utility("Water Works", 75, 28),
					new Sites("Marvin Gardens", 140, 29, "Yellow", new int[]{24, 48, 110, 360, 850, 1025, 1200}, 150),
					new GoToJail(30),
					new Sites("Pacific Avenue", 150, 31, "Green", new int[]{26, 52, 130, 390, 900, 1100, 1275}, 200),
					new Sites("North Carolina Avenue", 150, 32, "Green", new int[]{26, 52, 130, 390, 900, 1100, 1275}, 200),
					new CommunityChest(33),
					new Sites("Pennsylvania Avenue", 160, 34, "Green", new int[]{28, 56, 150, 450, 1000, 1200, 1400}, 200),
					new TransportSpaces("Short Line", 100, 35),
					new Chance(36),
					new Sites("Park Place", 175, 37, "Purple", new int[]{35, 70, 175, 500, 1100, 1300, 1500}, 200),
					new Penalties(38, 75, "Luxury Tax"),
					new Sites("BoardWalk", 200, 39, "Purple", new int[]{50, 100, 200, 600, 1400, 1700, 2000}, 200)
				)
			)
		);
	}

    private void setSiteMap() {
    	ArrayList<Sites> sitesBoard = new ArrayList<>(
    			Arrays.asList(
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
		);

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