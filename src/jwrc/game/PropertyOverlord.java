package jwrc.game;
import jwrc.player.*;

import java.util.ArrayList;
import jwrc.board.*;

/**
 * This class takes care of the property options such as building houses/hotels and mortgaging properties.
 */
public class PropertyOverlord {
	
	public static int numOfHouses=32;
	/**
	 * Static methods are called from this class.
	 */
	public PropertyOverlord() {
	}
	
	/**
	 * This class prompts the user to select a site from the list of sites they own. Once selected it will check
	 * the validity of the site and return this value.
	 * @param p The player selecting a site they own 
	 * @return Returns the board index of the site they select.
	 */
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
        for(Sites s : sitesArrayList) {      // check if user input site is valid.
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
	
	/**
	 *  This class prompts the user to select a property from the list of properties they own. 
	 * @param p The player selecting a property they own.
	 * @return Returns the board index of the property the user selects.
	 */
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
	/**
	 * This method sets the mortgage active status of a property to true if mortgage conditions are met.
	 * The player will also receive the mortgage value to their account.
	 * @param player The player wishing to mortgage a property.
	 * @param propertyIndex The board index of the property they'd like to mortgage.
	 */
	public static void mortgageProperty(Player player, int propertyIndex) {
		
		Property prop = (Property)Board.spaces.get(propertyIndex);
		if (prop instanceof Sites) {
			String siteKey = ((Sites) prop).getColour();
			ArrayList<Sites> temp = new ArrayList<Sites>();
			temp = Board.map.get(siteKey);
			for(int i=0 ; i<temp.size(); i++) { // check sites of same colour have no houses.
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
	
	/**
	 * This class lifts a mortgage from a property for the mortgage value plus 10%.
	 * @param player The player lifting the mortgage
	 * @param propertyIndex The board index of the property.
	 */
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
	
	/**
	 * This method checks if an action(build house, sell hotel) is vaild given the state of other sites of its colour group.
	 * @param player the player whoe's sites need to be checked 
	 * @param siteIndex The index of the site
	 * @param option This corresponds to the action we are taking. Depending on this value, the method will perform checks for a specific action(e.g 0 - buildHouse, 1- sellHouse).
	 * @return Returns true if checks are complete and operation can continue.
	 */
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
	
	/**
	 * This method sells a house from  a site if selling conditions are met.You will receive half the house cost to your account
	 * @param player The player wishing to sell a house
	 * @param siteIndex The board index of the site they want to sell.
	 */
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
	
	/**
	 * This method sells a hotel if conditions are met. It will replace the hotel with 4 houses and add half the hotel cost to the players account. 
	 * @param player The player wishing to sell a hotel
	 * @param siteIndex The board index of the hotel
	 */
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
	
	/**
	 * This method builds a hotel if conditions are met. It will remove 4 houses from the site and replace with a hotel with added rent cost.
	 * @param player The player wishing to build a hotel
	 * @param siteIndex The board index of the site to build a hotel on.
	 */
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
		
	/**
	 * This class will build a house on a site if conditions are met. This will increase rent cost and house cost deducted from players account.
	 * @param player The player wishing to build a house.
	 * @param siteIndex The board index of the site they wish to build on.
	 */ 
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
