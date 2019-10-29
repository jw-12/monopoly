package jwrc.menus;

import jwrc.game.Game;
import jwrc.game.Trade;
import jwrc.player.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Handles all the human interaction with the trading functionality on each turn and otherwise
 */

public class TradeMenu {

    TradeMenu() {

    }

    public static void options(Player player, ArrayList<Player> players) {
        /*
         * otherPlayers is every player except for the current player
         * */
        ArrayList<Player> otherPlayers = new ArrayList<>(players);
        otherPlayers.remove(player);

        int inputInt;
        Player otherPlayer;

        while(true) {
            System.out.println("----<"+ player.getName() + "-TRADE MENU>");
            System.out.println("----0 to exit, 1 to sell a property, 2 to buy a \"Get Out of Jail Free\" card, 3 to sell a \"Get Out of Jail Free\" card");
            try {
                inputInt = Game.scanner.nextInt();
                switch (inputInt) {
                    case 0:
                        System.out.println("...exiting trade menu");
                        return;
                    case 1:
                        sellPropertyCase(player, otherPlayers);
                        break;
                    case 2:
                        buyGOOJFCase(player, otherPlayers);
                        break;
                    case 3:
                        sellGOOJFCase(player, otherPlayers);
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (InputMismatchException ex) {
                Game.scanner.next();
                System.out.println("Invalid option");
            }
        }
    }

    //todo: perhaps package-private or something
    public static void sellPropertyCase(Player player, ArrayList<Player> otherPlayers) {
        int inputInt;
        Player otherPlayer; //player to sell to

        while (true) {
            System.out.println("--------Who would you like to sell a property to?");
            System.out.print("--------0 to exit, ");
            for (int i=1; i<otherPlayers.size() + 1; i++) {
                System.out.printf("%d for %s,\t", i, otherPlayers.get(i - 1).getName());
            }
            System.out.println();
            try {
                inputInt = Game.scanner.nextInt();

                if (inputInt == 0) {
                    return;
                } else if (inputInt < otherPlayers.size()) {
                    otherPlayer = otherPlayers.get(inputInt - 1);
                    System.out.println("Sell property here!!!!");
                } else {
                    System.out.println("Invalid option");
                }
                return;  //return from function
            } catch (InputMismatchException ex) {
                Game.scanner.next();
                System.out.println("Invalid input");
            }
        }
    }

    public static void buyGOOJFCase(Player player, ArrayList<Player> otherPlayers) {
        int inputInt;
        Player otherPlayer;  //player to sell to

        while (true) {
            System.out.println("--------Who would you like to buy a \"Get Out of Jail Free\" card from?");
            System.out.print("--------0 to exit, ");
            for (int i=1; i<otherPlayers.size() + 1; i++) {
                System.out.printf("%d for %s, ", i, otherPlayers.get(i - 1).getName());
            }
            System.out.println();
            try {
                inputInt = Game.scanner.nextInt();

                if (inputInt == 0) {
                    break;
                }
                else if (inputInt < otherPlayers.size()) {
                    otherPlayer = otherPlayers.get(inputInt - 1);  //NB due to 0 to exit
                    System.out.println(otherPlayer.getName());
                    if (otherPlayer.getGetOutOfJailFreeCard() > 0) {
                        // ask how much offering to pay
                        System.out.println("How much are you offering to pay? (0 to cancel)");
                        try {
                            inputInt = Game.scanner.nextInt();

                            if (inputInt == 0) {
                                return;
                            } else if (inputInt < 0) {
                                System.out.println("Amount must be positive");
                            } else if (player.getAccountBalance() < inputInt){
                                System.out.println("You don't have that much money in your account");
                            } else {
                                //assuming conditions sufficient for trade
                                Trade.tradeGOOJFCard(otherPlayer, player, inputInt, true);
                                return;
                            }
                        } catch (InputMismatchException ex) {
                            Game.scanner.next();
                            System.out.println("Invalid input");
                        }
                    } else {
                        System.out.println("Selected player does not have this card");
                    }
                } else {
                    System.out.println("Invalid option");
                }
            } catch (InputMismatchException ex) {
                Game.scanner.next();
                System.out.println("Invalid input");
            }
        }
    }

    public static void sellGOOJFCase(Player player, ArrayList<Player> otherPlayers) {

        Player otherPlayer;
        int inputInt;

        while (true) {

            if (player.getGetOutOfJailFreeCard() < 1) {
                System.out.println("--------You do not have a GOOJF card to sell");
                break;
            }

            System.out.println("--------Who would you like to sell a \"Get Out of Jail Free\" card to?");
            System.out.print("--------0 to exit, ");
            for (int i=1; i<otherPlayers.size() + 1; i++) {
                System.out.printf("%d for %s, ", i, otherPlayers.get(i - 1).getName());
            }
            try {
                inputInt = Game.scanner.nextInt();

                if (inputInt == 0) {
                    return;
                } else if (inputInt < otherPlayers.size()) {
                    otherPlayer = otherPlayers.get(inputInt - 1);
                    System.out.println("How much are you requesting for this card? (0 to cancel)");
                    try {
                        inputInt = Game.scanner.nextInt();
                        if (inputInt == 0) {
                            return;
                        } else if (inputInt > otherPlayer.getAccountBalance()) {
                            System.out.println(otherPlayer.getName() + " does not have enough money in their account.");
                        } else if (inputInt < 0) {
                            System.out.println("Amount must be positive");
                        } else {
                            // assuming conditions are sufficient for trade
                            Trade.tradeGOOJFCard(player, otherPlayer, inputInt, false);
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid input");
                    }
                } else {
                    System.out.println("Invalid option");
                }
            } catch (InputMismatchException ex) {
                Game.scanner.next();
                System.out.println("Invalid input");
            }
        }
    }
}
