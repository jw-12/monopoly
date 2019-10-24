package jwrc.game;
import jwrc.board.Board;
import jwrc.board.Property;
import jwrc.board.Sites;
import jwrc.player.*;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class PropertyOverlord {
	
	public static int numOfHouses=32;
	
	public PropertyOverlord() {
		
	}
	
	public static int printOptions(Player p) {
		
		if(numOfHouses == 0) {
			System.out.println("This are no houses left to buy");
			return 99;
		}
		
		if(p.propertiesOwned.isEmpty()) {
			System.out.println("You own no Properties yet!");
			return 99;
		}
		
		System.out.println("These are the Properties you own and their corresponding board position.\nPlease enter the position of the site you would like to take action on:");
		p.readProperties();
		int choice = 99;
		boolean check = false;
		
		try {
            choice = Game.scanner.nextInt();
            for(Property prop : p.propertiesOwned) {
    			if(prop.getBoardIndex() == choice) {
    				System.out.println("VALID");
    				check = true;
    			}
    		}
    		if(check == false) {
    			System.out.println("Not a valid choice of board index");
    			return 99;
    		}
            
        } catch (InputMismatchException e) {
            Game.scanner.next();
            System.out.println("Must enter an integer");
        }
		return choice;
	}
	
	public static void sellHouse(Player player, int siteIndex) {
		Sites site = (Sites)Board.spaces.get(siteIndex);
		if(site.getNoOfHouses() == 0){
			System.out.println("You have no houses on "+site.getName()+ " to sell");
		}
		
		String siteKey = site.getColour();
		ArrayList<Sites> temp = new ArrayList<Sites>();
		temp = Board.map.get(siteKey);
		int maxHouses = site.getNoOfHouses();
		
		for(int i=0 ; i<temp.size(); i++) {
			if(temp.get(i).getNoOfHouses() > maxHouses) {
				System.out.println("Your must sell from house on "+temp.get(i).getName()+ " first!");
				return;
			}
		}
		System.out.println("House being sold from "+ site.getName());
		numOfHouses++;
		site.removeHouse();
		int houseVal = site.getHouseCost()/2;
		player.changeAccountBalance(houseVal);
		System.out.println(houseVal + " has been added to your bank account.");
	}
		
	
	public static void buildHouse(Player player, int siteIndex) {
		
		Sites site = (Sites)Board.spaces.get(siteIndex);
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
		for(int i=0 ; i<temp.size(); i++) {
			System.out.println(temp.get(i).getOwner());
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
		numOfHouses--;
		player.changeAccountBalance(-site.getHouseCost());
		site.addHouse();
		System.out.println("New rent cost is "+ site.getRentCost());
	}
}
