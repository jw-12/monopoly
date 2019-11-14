package jwrc.board;

import java.util.InputMismatchException;
import jwrc.game.PropertyOverlord;
import jwrc.game.Game;
import jwrc.player.Player;

public class PropertyOptions { 
	
	
	
	public PropertyOptions() {
		
	}
		
	public static void readOptions() {
		System.out.println("0 to buy a house\t1 to sell a house\t2 to buy a hotel\t3 to sell a hotel\t4 to mortgage a property\t5 to lift mortgage ");
	}
	
	public static void flow(Player player) {
		readOptions();
		int choice;
		int propertyIndex;
		try {
	        choice = Game.scanner.nextInt();
	    } catch (InputMismatchException e) {
	        Game.scanner.next();
	        System.out.println("Must enter an integer");
	        return;
	    }
		if(choice >= 0 && choice <=3) {
			propertyIndex = PropertyOverlord.siteIndexSelector(player);
			if(propertyIndex == 99) {
				return;
			}
		}
		else if(choice == 4 || choice == 5) {
			propertyIndex = PropertyOverlord.printMortgageOptions(player);
			if(propertyIndex == 99) {
				return;
			}
		}
		else {
			System.out.println("Invalid choice");
			return;
		}

		switch(choice) {
			case 0 :
    				PropertyOverlord.buildHouse(player, propertyIndex);
				break;
			case 1:
    				PropertyOverlord.sellHouse(player, propertyIndex);
				break;
			case 2:
    				PropertyOverlord.buildHotel(player, propertyIndex);
    			break;
			case 3:
					PropertyOverlord.sellHotel(player, propertyIndex);
				break;
			case 4:
					PropertyOverlord.mortgageProperty(player, propertyIndex);
				break;
			case 5:
					PropertyOverlord.liftMortgage(player, propertyIndex);
				break;
			default:
				System.out.println("Invalid");
				break;
		}
	}
}
	
