package jwrc.game;

import jwrc.board.Sites;
import jwrc.board.Property;
import jwrc.player.Player;
import jwrc.board.Utility;
import jwrc.board.TransportSpaces;

import java.util.*;

/**
 * implements the in-game Trading, Auction etc functionalities
 */
public class Trade {

    public Trade() {

    }

    /**
     * begin an auction to find who will own it
     * @param parts the ArrayList of Player's who will be bidding in the auction (taken to be in correct order)
     * @param property the Property object which is up for bidding
     */
    public static void startAuction(ArrayList<Player> parts, Property property, Scanner input) {
    	
    	ArrayList<Player> participants = new ArrayList<Player>(parts);
    		
        Player currentPlayer;
        int currentBid = 0, previousBid = 0;

        System.out.println("Auction has begun for the property " + property.getName());

        while (participants.size() > 1) {
            currentPlayer = participants.get(0);

            if (previousBid >= currentPlayer.getAccountBalance()) {
                participants.remove(currentPlayer);
                System.out.println(currentPlayer.getName() + " has left the auction due to insufficient balance.");
                continue;
            }

            System.out.println(currentPlayer.getName() + " enter how much are you going to bid:");
            try {
                currentBid = input.nextInt();

                if (currentBid == 0) {
                    currentBid = previousBid;  // needs to be reset to previous non-zero value
                    participants.remove(currentPlayer);
                    System.out.println(currentPlayer.getName() + " has left the auction by choice.");
                    continue;
                }
                else if (previousBid >= currentBid) {
                    System.out.println("Bid must greater than €" + previousBid);
                    continue;
                } else if (currentBid > currentPlayer.getAccountBalance()) {
                    System.out.println("Bid cannot exceed account balance");
                    continue;
                }

                previousBid = currentBid;

                Collections.rotate(participants, -1);  // re-order participants list

            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Must be an integer");
            }
        }

        currentPlayer = participants.get(0);
        
        if (property instanceof Sites) {
        	((Sites) property).buySite(currentPlayer, currentBid);
        	 System.out.println(currentPlayer.getName() + " you bought " + property.getName() + " for €" + currentBid);
        }
        else {
        currentPlayer.changeAccountBalance(-currentBid);
        property.changeOwner(currentPlayer.getName());
        System.out.println(currentPlayer.getName() + " you bought " + property.getName() + " for €" + currentBid);
        }
    }


    /**
     * Represents trade interaction where buyer attempts to buy from seller by amount specified
     * @param seller
     * @param buyer
     * @param amount price offered by buyer to seller. Assumed that buyer has sufficient money
     * @param isBuyer true if the current player is looking to buy the property, false if selling
     */
    public static void tradeGOOJFCard(Player seller, Player buyer, int amount, boolean isBuyer) {

        if (isBuyer) {
            System.out.println(seller.getName() + ", " + buyer.getName() +
                    " is looking to buy your \"Get out of Jail Free\" card for $"
                    + amount + "\nDo you accept the offer? (y/n)");
        } else {
            System.out.println(buyer.getName() + ", " + seller.getName() +
                    " is looking to sell you a \"Get out of Jail Free\" card for $"
                    + amount + "\nDo you accept the offer? (y/n)");
        }

        String input;

        while (true) {
            input = Game.scanner.next();

            switch (input) {
                case "y":  // proceed with the trade
                    seller.setGetOutOfJailFreeCard(seller.getGetOutOfJailFreeCard() - 1);
                    buyer.setGetOutOfJailFreeCard(buyer.getGetOutOfJailFreeCard() + 1);
                    seller.changeAccountBalance(+amount);
                    buyer.changeAccountBalance(-amount);
                    System.out.println("Trade accepted");
                    return;
                case "n":
                    System.out.println("Trade declined");
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }

    /*
    * returns true if user wishes to exit
    * returns false if user entered an invalid character
    * */
    
    public static void sellProperty(Property p,Player seller, Player buyer, int price) {
    	
    	p.changeOwner(buyer.getName());
    	seller.changeAccountBalance(price);
    	buyer.changeAccountBalance(-price);
    	
    	if(p instanceof Sites) {
    		seller.removeSite((Sites)p);
    		buyer.addSite((Sites)p);
    	}
    	else if(p instanceof Utility) {
    		seller.changeUtilitiesOwned(-1);;
    		buyer.changeUtilitiesOwned(1);;
    	}
    	else {
    		seller.changeTransportsOwned(-1);
    		buyer.changeTransportsOwned(1);
    	}
    }
    
    
    private static boolean exitChecker() {
        String inputStr = Game.scanner.next();
        if (inputStr.equals("e")) {
            System.out.println("----Exiting trade menu...");
            return true; //player wishes to exit the trade menu
        } else {
            System.out.println("----Must enter valid integer or e to exit");
            return false;
        }
    }

    public static void testAuction() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("james"));
        players.add(new Player("ronan"));
        players.add(new Player("cathy"));
        players.add(new Player("gav"));
       // Scanner scanner = new Scanner(System.in);

       // Auction.startAuction(players, new Property("Seamount", 200, 54), scanner);

    }
}
