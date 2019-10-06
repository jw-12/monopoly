package jwrc.game;


import jwrc.board.Property;
import jwrc.player.Player;

import java.util.*;

/**
 * implements the in-game Auction functionality
 */
public class Auction {

    public Auction() {

    }

    /**
     * begin an auction to find who will own it
     * @param participants the ArrayList of Player's who will be bidding in the auction (taken to be in correct order)
     * @param property the Property object which is up for bidding
     */
    public static void startAuction(ArrayList<Player> participants, Property property, Scanner input) {
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

                if (previousBid >= currentBid) {
                    System.out.println("Bid must greater than €" + previousBid);
                    continue;
                } else if (currentBid > currentPlayer.getAccountBalance()) {
                    System.out.println("Bid cannot exceed account balance");
                }

                previousBid = currentBid;

                Collections.rotate(participants, -1);  // re-order participants list

            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Must be an integer");
            }
        }

        System.out.println(participants.get(0).getName() + " you bought " + property.getName() + " for €" + currentBid);
    }

    public static void testAuction() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("james"));
        players.add(new Player("ronan"));
        players.add(new Player("cathy"));
        players.add(new Player("gav"));
        Scanner scanner = new Scanner(System.in);

        Auction.startAuction(players, new Property("Seamount", 200, "blue", 54), scanner);

    }
}
