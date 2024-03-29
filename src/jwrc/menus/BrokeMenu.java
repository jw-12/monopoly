package jwrc.menus;

import jwrc.board.Property;
import jwrc.board.Sites;
import jwrc.game.Game;
import jwrc.game.PropertyOverlord;
import jwrc.game.Trade;
import jwrc.player.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;


/**
 * Menu presented to the player when they have gone broke to the bank and must liquidate assets
 */
public class BrokeMenu implements Menuable {

    /**
     * constructor for Broke Menu
     */
    BrokeMenu() {

    }

    /**
     * Menu offering selling/mortgaging options to a player who does not have sufficient money to proceed
     * Should only access this menu through Bank transaction failure as per specification.
     * @param player the player who needs to acquire money
     * @param players the players remaining in the game
     * @param cost the cost of the transaction in question
     */
    public static void options(Player player, ArrayList<Player> players, int cost) {

        Integer selectedSiteIndex;
        int inputInt;
        ArrayList<Player> otherPlayers = new ArrayList<>(players);
        otherPlayers.remove(player);  //otherPlayers is every player except the current player

        /*
        * Can't leave this menu until they have sufficient money to proceed or they declare bankruptcy
        * */
        while(player.getAccountBalance() < cost) {
            System.out.println("You do not currently have a sufficient account balance to proceed. You are required to liquidate some assets before proceeding.");
            System.out.println("Your current balance is: " + player.getAccountBalance());
            System.out.println("The target to meet is: " + cost);
            System.out.println("0 to sell property, 1 to mortgage a property, 2 to sell houses/hotels, 3 to sell a \"Get Out of Jail Free\" card, 4 to declare bankruptcy");

            try {
                inputInt = Game.scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input");
                continue;
            }

            switch (inputInt) {
                case 0:
                    TradeMenu.sellPropertyCase(player, otherPlayers);
                    break;
                case 1:
                    PropertyOverlord.printMortgageOptions(player);
                    break;
                case 2:
                    System.out.println("Sell houses/hotels here");
                    if ((selectedSiteIndex = PropertyOverlord.siteIndexSelector(player)) == null) {
                        break;
                    }
                    PropertyOverlord.sellHouse(player, selectedSiteIndex);
                    break;
                case 3:
                    TradeMenu.sellGOOJFCase(player, otherPlayers);
                    break;
                case 4:
                    System.out.println("Declaring bankruptcy");
                    Sites.liquidateBuildings(player); //remove houses and hotels
                    Game.kickPlayerFromGame(player);

                    for (Property property : player.getPropertiesOwned()) {
                        Trade.startAuction(otherPlayers, property);
                    }
                    return;
            }
        }
    }
}
