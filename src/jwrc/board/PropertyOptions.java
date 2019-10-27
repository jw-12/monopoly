package jwrc.board;

import java.util.InputMismatchException;
import jwrc.game.PropertyOverlord;
import jwrc.game.Game;
import jwrc.player.Player;

public class PropertyOptions { 
	
	
	
	public PropertyOptions() {
		
	}
		
	public static void readOptions() {
		System.out.println("0 to buy a house\t1 to sell a house\t2 to buy a hotel\t3 to sell a hotel");
	}
	
	
	
	public static void flow(Player player) {
		readOptions();
		int choice;
		int siteIndex;
		try {
	        choice = Game.scanner.nextInt();
	    } catch (InputMismatchException e) {
	        Game.scanner.next();
	        System.out.println("Must enter an integer");
	        return;
	    }
		if(choice < 0 || choice >3) { // must d this check before PropertyOverlord.printOptions is called to prevent options printing to screen.
			System.out.println("Invalid input");
			return;
		}
		
		siteIndex = PropertyOverlord.printOptions(player);
		if(siteIndex == 99) {
			return;
		}
		
		
		switch(choice) {
			case 0 :
    				PropertyOverlord.buildHouse(player, siteIndex);
				break;
			case 1:
    				PropertyOverlord.sellHouse(player, siteIndex);
				break;
			case 2:
    				PropertyOverlord.buildHotel(player, siteIndex);
    			break;
			case 3:
					PropertyOverlord.sellHotel(player, siteIndex);
				break;
			default:
				System.out.println("Invalid");
				break;
		}
	}
}
	
