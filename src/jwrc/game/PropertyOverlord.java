package jwrc.game;
import jwrc.board.Board;
import jwrc.board.BoardSpace;
import jwrc.board.Sites;
import jwrc.player.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PropertyOverlord {
	
	private int numOfHouses;
	//public Map<String, ArrayList<Sites>> map;   // static map in Board class instead.
	
	public PropertyOverlord() {
		numOfHouses = 32;
		//this.map = new HashMap<String, ArrayList<Sites>>();
	}
	
	public static void BuildHouse(Player player, Sites site) {
		
		String siteKey = site.getColour();
		ArrayList<Sites> temp = new ArrayList<Sites>();
		temp = Board.map.get(siteKey);
		String owner = temp.get(0).getOwner();
		int minHouses = site.getNoOfHouses();
		//housing checks
		if(site.getOwner().equals("null")) {
			System.out.println("you cannot build on unowned sites");
			return;
		}
		for(int i=1 ; i<temp.size(); i++) {			
			if(owner != temp.get(i).getOwner()) {
				System.out.println("You must own all Sites of colour "+ site.getColour()+ " to build houses!");
				return;
			}
			if(temp.get(i).getNoOfHouses() < minHouses) {
				System.out.println("Your must build a house on "+temp.get(i).getName()+ " first!");
				return;
			}
		}
		System.out.println("House being built on "+ site.getName());
		System.out.println("Previous rent cost was "+ site.getRentCost());
		player.changeAccountBalance(-site.getHouseCost());
		site.addHouse();
		System.out.println("New rent cost is "+ site.getRentCost());
		
	}
	
	/*public void buildHouseMap() {
		
	    Sites b1 = new Sites("Ha'Penny Bridge",60,5,"Brown",new int[] { 2,4,10,30,90,160,250},20) ;
	    Sites b2 = new Sites("MM Statue",60,5,"Brown",new int[] { 2,4,10,30,90,160,250},20) ;
	    
		
		ArrayList<Sites> brown = new ArrayList<Sites>();
        brown.add(b1);
        brown.add(b2);
        
        Sites bl1 = new Sites("Zoo",60,5,"Blue",new int[] { 2,4,10,30,90,160,250},50) ;
	    Sites bl2 = new Sites("Kilmainham",60,5,"Blue",new int[] { 2,4,10,30,90,160,250},50) ;
	    Sites bl3 = new Sites("Museum",60,5,"Blue",new int[] { 2,4,10,30,90,160,250},50) ;
	    
	    ArrayList<Sites> blue = new ArrayList<Sites>();
        blue.add(bl1);
        blue.add(bl2);
        blue.add(bl3);
      
        /*
        ArrayList<Integer> blue = new ArrayList<Integer>();
        blue.add(0);
        blue.add(0);
        blue.add(0);
        
        ArrayList<Integer> pink = new ArrayList<Integer>();
        pink.add(0);
        pink.add(0);
        pink.add(0);
        
        ArrayList<Integer> orange = new ArrayList<Integer>();
        orange.add(0);
        orange.add(0);
        orange.add(0);
        
        ArrayList<Integer> red = new ArrayList<Integer>();
        red.add(0);
        red.add(0);
        red.add(0);
        
        ArrayList<Integer> yellow = new ArrayList<Integer>();
        yellow.add(0);
        yellow.add(0);
        yellow.add(0);
        
        ArrayList<Integer> green = new ArrayList<Integer>();
        green.add(0);
        green.add(0);
        green.add(0);
        
        ArrayList<Integer> purple = new ArrayList<Integer>();
        purple.add(0);
        purple.add(0);
        
        
        this.map.put("Brown", brown);
        this.map.put("Blue", blue);
        this.map.put("Pink", pink);
        this.map.put("Orange", orange);
        this.map.put("Red", red);
        this.map.put("Yellow", yellow);
        this.map.put("Green", green);
        this.map.put("Purple", purple);
        
        
        this.map.put("Brown", brown);
        this.map.put("Blue", blue);
        
	}*/
}
