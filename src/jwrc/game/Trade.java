package jwrc.game;


import jwrc.board.Property;
import jwrc.player.Player;

import java.util.*;

/**
 * implements the in-game Trading, Auction etc functionalities
 */
public class Trade {

    public Trade() {

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
        currentPlayer.changeAccountBalance(-currentBid);
        property.changeOwner(currentPlayer.getName());
        System.out.println(currentPlayer.getName() + " you bought " + property.getName() + " for €" + currentBid);
    }

    public static void tradeMenu(Player player, ArrayList<Player> players) {

        /*
        * otherPlayers is every player except for the current player
        * */
        ArrayList<Player> otherPlayers = new ArrayList<>(players);
        otherPlayers.remove(player);

        int inputInt;

        while(true) {
            System.out.println("----<TRADE MENU>");
            System.out.println("----0 to sell a property,\t1 to buy a \"Get Out of Jail Free\" card,\t2 to sell a \"Get Out of Jail Free\" card,\te to exit");
            try {
                inputInt = Game.scanner.nextInt();
                switch (inputInt) {
                    case 0:
                        while (true) {
                            System.out.println("--------Who would you like to sell a property to?");
                            System.out.print("--------");
                            for (int i=0; i<otherPlayers.size(); i++) {
                                System.out.printf("%d for %s,\t", i, otherPlayers.get(i).getName());
                            }
                            System.out.println("e to exit");
                            try {
                                inputInt = Game.scanner.nextInt();

                                System.out.println("--------You selected index: " + inputInt);
                                System.out.println("--------This refers to player: " + otherPlayers.get(inputInt).getName());
                                break;  //break from while
                            } catch (InputMismatchException ex) {
                                if(exitChecker()) {
                                    return;
                                }
                            }
                        }
                        break;  //break from case
                    case 1:
                        while (true) {
                            System.out.println("--------Who would you like to buy a \"Get Out of Jail Free\" card from?");
                            System.out.print("--------");
                            for (int i=0; i<otherPlayers.size(); i++) {
                                System.out.printf("%d for %s,\t", i, otherPlayers.get(i).getName());
                            }
                            System.out.println("e to exit");
                            try {
                                inputInt = Game.scanner.nextInt();

                                System.out.println("--------You selected index: " + inputInt);
                                System.out.println("--------This refers to player: " + otherPlayers.get(inputInt).getName());
                                break;  // break from while loop
                            } catch (InputMismatchException ex) {
                                if(exitChecker()) {
                                    return;
                                }
                            }
                        }
                        break;  // break from case
                    case 2:
                        while (true) {
                            System.out.println("--------Who would you like to sell a \"Get Out of Jail Free\" card to?");
                            System.out.print("--------");
                            for (int i=0; i<otherPlayers.size(); i++) {
                                System.out.printf("%d for %s,\t", i, otherPlayers.get(i).getName());
                            }
                            System.out.println("e to exit");
                            try {
                                inputInt = Game.scanner.nextInt();

                                System.out.println("--------You selected index: " + inputInt);
                                System.out.println("--------This refers to player: " + otherPlayers.get(inputInt).getName());
                                break;  // break from while
                            } catch (InputMismatchException ex) {
                                if(exitChecker()) {
                                    return;
                                }
                            }
                        }
                        break;  //break from case
                }
            } catch (InputMismatchException ex) {
                if (exitChecker()) {
                    return;
                }
            }
        }
    }

    /*
    * returns true if user wishes to exit
    * returns false if user entered an invalid character
    * */
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
