package jwrc.menus;

import java.util.InputMismatchException;

import jwrc.board.PropertyOverlord;
import jwrc.game.Game;
import jwrc.player.Player;

public class BankMenu { 
	
	
	public BankMenu() {
		
	}
	
	public static void options(Player player) {
		
		while(true) {
			System.out.println("----<"+ player.getName() + "-BANK MENU>");
			System.out.println("----<0 to exit \t1 to buy a house\t2 to sell a house\t3 to buy a hotel\t4 to sell a hotel\t5 to mortgage a property\t6 to lift mortgage ");
			int choice;
			int propertyIndex;
			
			try {
		        choice = Game.scanner.nextInt();
		        switch(choice) {
				
				case 0 :
					 System.out.println("...exiting bank menu");
                     return;
				case 1 :
					propertyIndex = PropertyOverlord.siteIndexSelector(player);
					if(propertyIndex != 99) {
						PropertyOverlord.buildHouse(player, propertyIndex);
					}
					break;
				case 2:
					propertyIndex = PropertyOverlord.siteIndexSelector(player);
					if(propertyIndex != 99) {
						PropertyOverlord.sellHouse(player, propertyIndex);
					}
					break;
				case 3:
					propertyIndex = PropertyOverlord.siteIndexSelector(player);
					if(propertyIndex != 99) {
						PropertyOverlord.buildHotel(player, propertyIndex);
					}
					break;
				case 4:
					propertyIndex = PropertyOverlord.siteIndexSelector(player);
					if(propertyIndex != 99) {
						PropertyOverlord.sellHotel(player, propertyIndex);
					}
					break;
				case 5:
					propertyIndex = PropertyOverlord.printMortgageOptions(player);
					if(propertyIndex == 99) {
						return;
					}
					PropertyOverlord.mortgageProperty(player, propertyIndex);
					break;
				case 6:
					propertyIndex = PropertyOverlord.printMortgageOptions(player);
					if(propertyIndex != 99) {
						PropertyOverlord.liftMortgage(player, propertyIndex);
					}
					break;
				default:
					System.out.println("Invalid");
					break;
			}   
		        
		    } catch (InputMismatchException e) {
		        Game.scanner.next();
		        System.out.println("invalid option");
		        return;
		    }
		}	
	}
}
	
