package jwrc.menus;

import java.util.InputMismatchException;

import jwrc.game.PropertyOverlord;
import jwrc.game.Game;
import jwrc.player.Player;


/**
 * Handles user input and interactions relating to the Bank functionality
 * e.g. buying house/hotel, mortgage
 */
public class BankMenu implements Menuable {


	/**
	 * constructor for the menu
	 */
	public BankMenu() {

	}

	/**
	 * Allow user navigate through their options to perform actions.
	 * Override the interface method in Menuable
	 * @param player the current active player relating to the user
	 *                that is navigating the menu
	 */
	public static void options(Player player) {
		
		while(true) {
			System.out.println("----<"+ player.getName() + "-BANK MENU>");
			System.out.println("----<0 to exit \t1 to buy a house\t2 to sell a house\t3 to buy a hotel\t4 to sell a hotel\t5 to mortgage a property\t6 to lift mortgage ");
			int choice;
			Integer propertyIndex;
			
			try {
		        choice = Game.scanner.nextInt();
		        switch(choice) {
				
				case 0 :
					 System.out.println("...exiting bank menu");
                     return;
				case 1 :
					propertyIndex = PropertyOverlord.siteIndexSelector(player);
					if(propertyIndex != null) {
						PropertyOverlord.buildHouse(player, propertyIndex);
					}
					break;
				case 2:
					propertyIndex = PropertyOverlord.siteIndexSelector(player);
					if(propertyIndex != null) {
						PropertyOverlord.sellHouse(player, propertyIndex);
					}
					break;
				case 3:
					propertyIndex = PropertyOverlord.siteIndexSelector(player);
					if(propertyIndex != null) {
						PropertyOverlord.buildHotel(player, propertyIndex);
					}
					break;
				case 4:
					propertyIndex = PropertyOverlord.siteIndexSelector(player);
					if(propertyIndex != null) {
						PropertyOverlord.sellHotel(player, propertyIndex);
					}
					break;
				case 5:
					propertyIndex = PropertyOverlord.printMortgageOptions(player);
					if(propertyIndex != null) {
						PropertyOverlord.mortgageProperty(player, propertyIndex);
					}
					break;
				case 6:
					propertyIndex = PropertyOverlord.printMortgageOptions(player);
					if(propertyIndex != null) {
						PropertyOverlord.liftMortgage(player, propertyIndex);
					}
					break;
				default:
					System.out.println("Invalid");
					break;
				}

				/*
				* catch exception if user enters a non integer input
				* */
		    } catch (InputMismatchException e) {
		        Game.scanner.next();
		        System.out.println("invalid option");
		        return;
		    }
		}	
	}
}
	
