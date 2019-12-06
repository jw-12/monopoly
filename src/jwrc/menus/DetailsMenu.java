package jwrc.menus;

import java.util.ArrayList;
import jwrc.board.Sites;
import jwrc.board.TransportSpaces;
import jwrc.board.Utility;
import jwrc.game.Game;
import jwrc.player.Player;
import jwrc.board.Property;

/**
 * give players option to see everyone's details i.e. number of properties etc
 */
public class DetailsMenu implements Menuable {

	/**
	 * constructor to the menu
	 */
	DetailsMenu() {

	}

	/**
	 * method that presents the options to the player what they can view and handles interactions until they leave
	 * @param player the current active player
	 * @param players all players remaining in the game
	 */
	public static void options(Player player, ArrayList<Player> players) {
		while(true) {
			String playerName;
			Player chosenPlayer = null;
			boolean check = false;
			System.out.println("\n----<"+ player.getName() + "-Details MENU>");
			System.out.println("----<Who's details would you like to read(enter name)(e to exit details menu): ");
			for(Player p : players) {
				System.out.println("     "+p.getName());
			}
			
            playerName = Game.scanner.next();
            if(playerName.equals("e")) {
            	System.out.println("......exiting details menu");
            	return;
            }
            for(Player p : players) {
    			if(p.getName().equals(playerName)) {
    				check = true;
    				chosenPlayer = p; 
    			}
    		}
    		if(!check) {
    			System.out.println("     Not a valid choice of player");
    			continue;
    		} 
    		System.out.println("\t-----------------");
			System.out.println("\t     "+chosenPlayer.getName());
			System.out.println("\t Balance : $"+chosenPlayer.getAccountBalance());
			System.out.println("\t-----------------");
			
			readProperties(chosenPlayer);
		}
	}


	/**
	 * Method to print the properties in a neat format for a given player
	 * @param player player to print properties for
	 */
	private static void readProperties(Player player) {
		
		ArrayList<Property> props = player.getPropertiesOwned();
		System.out.println("\t|     Sites \t|");
		for(Property p : props) {
			if(p instanceof Sites) {
				System.out.println("Name: "+p.getName()+"     \tIndex: "+((Sites)p).getBoardIndex()+"\tMortgage Value: "+((Sites)p).getmortgageValue()+"\tColour: "+((Sites)p).getColour()+"\t  Houses: "+((Sites)p).getNoOfHouses()+"\tHotel: "+((Sites)p).hasHotel+"\tRent Cost: "+((Sites)p).getRentCost());
			}
		}
		System.out.println("\n\t|  Transports \t|");
		for(Property p : props) {
			if(p instanceof TransportSpaces) {
				System.out.println("Name: "+p.getName()+"        \tIndex: "+((TransportSpaces)p).getBoardIndex()+"\tMortgage Value: "+((TransportSpaces)p).getmortgageValue());
			}
		}
		System.out.println("\n\t|  Utilities \t|");
		for(Property p : props) {
			if(p instanceof Utility) {
				System.out.println("Name: "+p.getName()+"          \tIndex: "+((Utility)p).getBoardIndex()+"\tMortgage Value: "+((Utility)p).getmortgageValue());
			}
		}
	}
}
