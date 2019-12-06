package jwrc.game;

import jwrc.board.Sites;
import jwrc.board.Board;
import jwrc.board.Property;
import jwrc.board.TransportSpaces;
import jwrc.player.PaymentType;
import jwrc.player.Player;
import jwrc.board.Utility;

import java.util.*;

/**
 * implements the in-game Trading, Auction etc functionalities
 */
public class Trade {

    public Trade() {

    }

    /**
     * begin an auction to find who will own a property
     * @param parts the ArrayList of Player's who will be bidding in the auction (taken to be in correct order)
     * @param property the Property object which is up for bidding
     */
    public static void startAuction(ArrayList<Player> parts, Property property) {
    	ArrayList<Player> participants = new ArrayList<Player>(parts);
        Player currentPlayer;
        int currentBid = 0, previousBid = 0;
        System.out.println("Auction has begun for the property " + property.getName());

        /* keeps running until only one bidder remaining */
        while (participants.size() > 1) {
            currentPlayer = participants.get(0);

            /* kick player from auction if don't have enough to make another bid */
            if (previousBid >= currentPlayer.getAccountBalance()) {
                participants.remove(currentPlayer);
                System.out.println(currentPlayer.getName() + " has left the auction due to insufficient balance.");
                continue;
            }
            System.out.println(currentPlayer.getName() + " enter how much are you going to bid (0 to exit):");
            try {
                currentBid = Game.scanner.nextInt();

                if (currentBid == 0) {
                    currentBid = previousBid;  // needs to be reset to previous non-zero value
                    participants.remove(currentPlayer);
                    System.out.println(currentPlayer.getName() + " has left the auction by choice.");
                    continue;
                }
                else if (previousBid >= currentBid) {
                    System.out.println("Bid must greater than $" + previousBid);
                    continue;
                } else if (currentBid > currentPlayer.getAccountBalance()) {
                    System.out.println("Bid cannot exceed account balance");
                    continue;
                }
                previousBid = currentBid;
                Collections.rotate(participants, -1);  // re-order participants list
            } catch (InputMismatchException e) { // catch the exception when user input is not an int. Loop back to top
                Game.scanner.next();
                System.out.println("Must be an integer");
            }
        }
        /*
        when bidding has stopped i.e. we have our winner
        Allow them to safely purchase the property if site or some other type
         */
        currentPlayer = participants.get(0);
        if (property instanceof Sites) {
        	((Sites) property).buySite(currentPlayer, currentBid);
        	 System.out.println(currentPlayer.getName() + " you bought " + property.getName() + " for $" + currentBid);
        } else {
            currentPlayer.changeAccountBalance(-currentBid, PaymentType.BANK);
            property.changeOwner(currentPlayer.getName());
            System.out.println(currentPlayer.getName() + " you bought " + property.getName() + " for $" + currentBid);
        }
    }

    /**
     * Represents trade interaction where buyer attempts to buy from seller by amount specified
     * This boolean allows us to use the same function for multiple cases
     * i.e. when a player is lookign to sell their card or
     * when a player is looking to buy from another player
     * @param seller player object that is selling the card
     * @param buyer player object that is buying the card
     * @param amount price offered by buyer to seller. Assumed that buyer has sufficient money
     * @param isBuyer true if the current player is looking to buy the property, false if selling
     */
    public static void tradeGOOJFCard(Player seller, Player buyer, int amount, boolean isBuyer) {

        /*
        * Print different string depending on the case
        *  */
        if (isBuyer) {
            System.out.println(seller.getName() + ": " + buyer.getName() +
                    " is looking to buy your \"Get out of Jail Free\" card for $"
                    + amount + "\nDo you accept the offer? (y/n)");
        } else {
            System.out.println(buyer.getName() + ": " + seller.getName() +
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
                    seller.payToPlayer(buyer, amount);
                    System.out.println("Trade accepted");
                    return;
                case "n": //cancel the trade
                    System.out.println("Trade declined");
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }

    /**
     * Represents trade offer for a property between two players. Note: can only be called by the seller as per the
     * rules of this game
     * @param p property object to be sold
     * @param seller player object looking to sell the property
     * @param buyer player object potentially buying the property
     * @param price amount of money at which seller has offered to sell at
     */
    public static void sellProperty(Property p,Player seller, Player buyer, int price) {
        String input;
        System.out.println(
                buyer.getName() + ", " + seller.getName() + " has offered to sell you "
                + p.getName() + " for $" + price + ". Do you accept? (y/n)");
        while (true) {
            input = Game.scanner.next();
            switch (input) {
                case "y":
                    System.out.println("Trade offer accepted");
                    seller.payToPlayer(buyer, price);
                    safeTrade(seller, buyer, p);
                    return;
                case "n":
                    System.out.println("Trade offer declined.");
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    /**
     * Called to change the owner of the property and adjust relevant metrics of each property
     * e.g. if buyer's number of transports has increased, then the rent of all transports should be increased
     * @param seller player object selling the property
     * @param buyer player object buying the property
     * @param p the property object to be sold
     */
    public static void safeTrade(Player seller, Player buyer, Property p) {
        p.changeOwner(buyer.getName());
        buyer.addProperty(p);
        seller.removeProperty(p);

        /*
        * checking if any metrics inside the property object need to be adjusted due to this trade e.g. number of
        * a specific colour sites owned
        *  */
        if(p instanceof TransportSpaces) {
            seller.changeTransportsOwned(-1);
            buyer.changeTransportsOwned(1);
        } else if(p instanceof Utility) {
            seller.changeUtilitiesOwned(-1);
            buyer.changeUtilitiesOwned(1);
        } else {
        	if(((Sites) p).fullSet) {
        		String siteKey = ((Sites) p).getColour();
        		ArrayList<Sites> temp = Board.map.get(siteKey);
        		for(int i=0 ; i<temp.size(); i++) {
        			temp.get(i).fullSet = false;
        			temp.get(i).rentIndex--;
        		}
        	}
        	((Sites) p).colourGroupCheck();
        }
    }
}
