package jwrc.game;
import jwrc.player.*;

import java.util.ArrayList;
import jwrc.board.*;

public class PropertyOverlord {
	
	public static int numOfHouses=32;
	public PropertyOverlord() {
		
	}
	
	public static Integer siteIndexSelector(Player p) {
		
		ArrayList<Sites> sitesArrayList = p.getSites();
		
		
		if(sitesArrayList.isEmpty()) {
			System.out.println("You own no Sites yet!");
			return null;
		}
		
		System.out.println("These are the Sites you own and their corresponding board position.\nPlease enter the position of the site you would like to take action on:");
		p.readSites();
		boolean check = false;
		
        Integer choice = Game.scanner.nextInt();
        for(Sites s : sitesArrayList) {
			if(s.getBoardIndex() == choice) {
				check = true;
			}
		}
		if(!check) {
			System.out.println("Not a valid choice of board index");
			return null;
		}
            
		return choice;
	}
	
	public static Integer printMortgageOptions(Player p) {
		ArrayList<Property> propertyArrayList = p.getPropertiesOwned();
		System.out.println("These are the properties you own and their corresponding board position.\nPlease enter the position of the property you would like to take action on:");
		p.readProperties();
		boolean check = false;
        Integer choice = Game.scanner.nextInt();
        for(Property property : propertyArrayList) {
			if(property.getBoardIndex() == choice) {
				if(property instanceof Sites) {
					if(((Sites) property).getNoOfHouses() !=0) {
						System.out.println("You must sell all houses from "+ property.getName()+ " before you can mortgage");
						return null;
					}
				}
				check = true;
			}
		}
		if(!check) {
			System.out.println("Not a valid choice of board index");
			return null;
		}
		return choice;
	}
	
	public static void mortgageProperty(Player player, int propertyIndex) {
		
		Property prop = (Property)Board.spaces.get(propertyIndex);
		if (prop instanceof Sites) {
			String siteKey = ((Sites) prop).getColour();
			ArrayList<Sites> temp = new ArrayList<Sites>();
			temp = Board.map.get(siteKey);
			for(int i=0 ; i<temp.size(); i++) {
				if(temp.get(i).noOfHouses !=0 || temp.get(i).hasHotel) {
					System.out.println("You must sell all buildings off "+siteKey+" sites if you want to mortgage "+prop.getName());
					return;
				}
			}
		}
		prop.mortgageActive = true;
		player.changeAccountBalance(prop.getmortgageValue(), PaymentType.BANK);
		System.out.println("You have received $"+ prop.getmortgageValue()+" for mortgaging "+prop.getName());
	}
	
	public static void liftMortgage(Player player, int propertyIndex) {
		Property prop = (Property)Board.spaces.get(propertyIndex);
		if(!prop.mortgageActive) {
			System.out.println(prop.getName()+" has not been mportgaged");
			return;
		}
		else {
			prop.mortgageActive=false;
			int amount = prop.getmortgageValue()+(int)(prop.getmortgageValue()*0.1);
			player.changeAccountBalance(-amount, PaymentType.BANK);
			System.out.println("Mortgage has been lifted from "+ prop.getName()+" for a price of $"+amount);
		}
		
	}
	
	public static boolean siteColourChecks(Player player, int siteIndex, int option) {
		
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
			return;
		}
		if(!PropertyOverlord.siteColourChecks(player, siteIndex, 1)) {
			return;
		}
		System.out.println("House being sold from "+ site.getName());
		numOfHouses++;
		site.removeHouse();
		int houseVal = site.getHouseCost()/2;
		player.changeAccountBalance(houseVal,PaymentType.BANK);
		System.out.println(houseVal + " has been added to your bank account.");
	}
	
	public static void sellHotel(Player player, int siteIndex) {
		Sites site = (Sites)Board.spaces.get(siteIndex);
		if(!site.hasHotel) {
			System.out.println("you do not have a hotel to sell!");
			return;
		}
		if(numOfHouses < 4) {
			System.out.println("Not enough houses remain to replace a hotel. Consider selling some houses first");
			return;
		}
		
		site.hasHotel = false;
		site.noOfHouses = 4;
		int hotelVal = (site.getHouseCost())/2;
		player.changeAccountBalance(hotelVal,PaymentType.BANK);
		System.out.println("$"+hotelVal + " has been added to your bank account.");
		numOfHouses -=4;
		site.rentIndex--;
		
	}
	
	public static void buildHotel(Player player, int siteIndex) {
		
		Sites site = (Sites)Board.spaces.get(siteIndex);
		if(site.hasHotel) {
			System.out.println(site.getName()+ " already has a hotel!");
			return;
		}
		if(!PropertyOverlord.siteColourChecks(player, siteIndex, 2)) {
			return;
		}
		if(site.noOfHouses == 4) {
			site.rentIndex++;
			site.noOfHouses = 0;
			numOfHouses+=4;
			site.hasHotel = true;
			player.changeAccountBalance(-site.getHouseCost(),PaymentType.BANK);
			System.out.println("Hotel built on "+ site.getName());
		}
		else {
			System.out.println("You must build 4 houses on this site before building a hotel");
		}
	}
		
	
	public static void buildHouse(Player player, int siteIndex) {
		
		if(numOfHouses == 0) {
			System.out.println("This are no houses left to buy");
			return;
		}
		
		Sites site = (Sites)Board.spaces.get(siteIndex);
		
		if(site.hasHotel) {
			System.out.println(site.getName() + " has a hotel built so no more houses can be added");
			return;
		}
		
		if(site.noOfHouses == 4) {
			System.out.println("You have reached the maximum number of houses for this Site");
			return;
		}
		if(site.getOwner() == null) {
			System.out.println("you cannot build on unowned sites");
			return;
		}
		if(!PropertyOverlord.siteColourChecks(player, siteIndex, 0)) {
			return;
		}
		System.out.println("House built on "+ site.getName());
		System.out.println("Previous rent cost was $"+ site.getRentCost());
		numOfHouses--;
		player.changeAccountBalance(-site.getHouseCost(),PaymentType.BANK);
		site.addHouse();
		System.out.println("New rent cost is $"+ site.getRentCost());
	}
}
