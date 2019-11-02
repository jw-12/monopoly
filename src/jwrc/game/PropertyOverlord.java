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
		
		if(p.sitesOwned.isEmpty()) {
			System.out.println("You own no Sites yet!");
			return 99;
		}
		
		System.out.println("These are the Sites you own and their corresponding board position.\nPlease enter the position of the site you would like to take action on:");
		p.readSites();
		int choice = 99;       // set 99 as an error code to stop action.
		boolean check = false;
		
		try {
            choice = Game.scanner.nextInt();
            for(Sites s : p.sitesOwned) {
    			if(s.getBoardIndex() == choice) {
    				check = true;
    			}
    		}
    		if(!check) {
    			System.out.println("Not a valid choice of board index");
    			return 99;
    		}
            
        } catch (InputMismatchException e) {
            Game.scanner.next();
            System.out.println("Must enter an integer");
        }
		return choice;
	}
	
	public static boolean siteCheck(Player player, int siteIndex, int option) {
		
		Sites site = (Sites)Board.spaces.get(siteIndex);
		String siteKey = site.getColour();
		ArrayList<Sites> temp = new ArrayList<Sites>();
		temp = Board.map.get(siteKey);
		boolean result = false;
		
		switch(option) {
			//build house
			case 0:
				int minHouses = site.getNoOfHouses();
				String owner = temp.get(0).getOwner();
				for(int i=0 ; i<temp.size(); i++) {
					if(owner != temp.get(i).getOwner()) {
						System.out.println("You must own all Sites of colour "+ site.getColour()+ " to build houses!");
						return result;
					}
					if(temp.get(i).getNoOfHouses() < minHouses) {
						if(temp.get(i).hasHotel) {
							continue;
						}
						else {
						System.out.println("Your must build a house on "+temp.get(i).getName()+ " first!");
						return result;
						}
					}
				}
				result = true;
				return result;
			//sell house
			case 1:
				int maxHouses = site.getNoOfHouses();
				for(int i=0 ; i<temp.size(); i++) {
					if(temp.get(i).getNoOfHouses() > maxHouses) {
						System.out.println("Your must sell from house on "+temp.get(i).getName()+ " first!");
						return result;
					}
					if(temp.get(i).hasHotel) {
						System.out.println("You must sell the hotel from "+temp.get(i).getName()+" first!");
						return result;
					}
				}
				result = true;
				return result;
			//build hotel
			case 2:
				for(int i=0 ; i<temp.size(); i++) {
					if(temp.get(i).noOfHouses != 4 && !temp.get(i).hasHotel) {
						System.out.println("you must build 4 houses on "+ temp.get(i).getName()+ " first.");
						return result;
					}
				}
				result = true;
				return result;	
			default:
				return result;
		}
	}
	
	
	public static void sellHouse(Player player, int siteIndex) {
		Sites site = (Sites)Board.spaces.get(siteIndex);
		if(site.getNoOfHouses() == 0){
			System.out.println("You have no houses on "+site.getName()+ " to sell");
		}
		if(!PropertyOverlord.siteCheck(player, siteIndex, 1)) {
			return;
		}
		System.out.println("House being sold from "+ site.getName());
		numOfHouses++;
		site.removeHouse();
		int houseVal = site.getHouseCost()/2;
		player.changeAccountBalance(houseVal);
		System.out.println(houseVal + " has been added to your bank account.");
	}
	
	public static void sellHotel(Player player, int siteIndex) {
		Sites site = (Sites)Board.spaces.get(siteIndex);
		if(!site.hasHotel) {
			System.out.println("you do not have a hotel to sell!");
		}
		if(numOfHouses < 4) {
			System.out.println("Not enough houses remain to replace a hotel. Consider selling some houses first");
			return;
		}
		
		site.hasHotel = false;
		site.noOfHouses = 4;
		int hotelVal = (site.getHouseCost())/2; // change when I add hotel cost to the sites.
		player.changeAccountBalance(hotelVal);
		System.out.println(hotelVal + " has been added to your bank account.");
		numOfHouses -=4;
		site.rentIndex--;
		
	}
	
	public static void buildHotel(Player player, int siteIndex) {
		
		Sites site = (Sites)Board.spaces.get(siteIndex);
		if(site.hasHotel) {
			System.out.println(site.getName()+ " already has a hotel!");
			return;
		}
		if(!PropertyOverlord.siteCheck(player, siteIndex, 2)) {
			return;
		}
		if(site.noOfHouses == 4) {
			site.rentIndex++;
			site.noOfHouses = 0;
			numOfHouses+=4;
			site.hasHotel = true;
			player.changeAccountBalance(-site.getHouseCost());
			System.out.println("Hotel build on "+ site.getName());
		}
		else {
			System.out.println("You must build 4 houses on this site before building a hotel");
		}
	}
		
	
	public static void buildHouse(Player player, int siteIndex) {
		
		Sites site = (Sites)Board.spaces.get(siteIndex);
		
		if(site.hasHotel) {
			System.out.println(site.getName() + " has a hotel built so no more houses can be added");
			return;
		}
		
		if(site.noOfHouses == 4) {
			System.out.println("You have reached the maximum number of houses for this Site");
			return;
		}
		if(site.getOwner().equals("null")) {
			System.out.println("you cannot build on unowned sites");
			return;
		}
		if(!PropertyOverlord.siteCheck(player, siteIndex, 0)) {
			return;
		}
		System.out.println("House being built on "+ site.getName());
		System.out.println("Previous rent cost was "+ site.getRentCost());
		numOfHouses--;
		player.changeAccountBalance(-site.getHouseCost());
		site.addHouse();
		System.out.println("New rent cost is "+ site.getRentCost());
	}
}
