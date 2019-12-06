package jwrc.game;

import jwrc.board.*;
import jwrc.menus.BankMenu;
import jwrc.menus.TradeMenu;
import jwrc.player.PaymentType;
import jwrc.menus.DetailsMenu;
import jwrc.player.Player;
import java.util.ArrayList;
import java.util.InputMismatchException;


/**
 * Turn class handles actions which proceed before and after the BoardSpace actions take place. e.g. dice rolled here
 * and post-turn prompts. Player taking the turn can build, sell etc whenever they want during the turn due to
 * the looping menu structure
 */

public class Turn {

    private static ArrayList<Integer> commDeckIndices; // the deck of indexes to be used in Chance/CommChest
    private static ArrayList<Integer> chanceDeckIndices;

    /**
     * Constructor for the turn object
     * @param inputCommDeckIndices shuffled indexes to be used for community chest
     * @param inputChanceDeckIndices shuffled indexes to be used for chance
     */
    public Turn(ArrayList<Integer> inputCommDeckIndices, ArrayList<Integer> inputChanceDeckIndices) {
        commDeckIndices = inputCommDeckIndices;
        chanceDeckIndices = inputChanceDeckIndices;
    }

    /**
     * The main looping turn method. In here, the player has freedom to perform trades, view other
     * player details and all other actions before, and after they roll the dice.
     * Access is restricted to the package
     * @param player the player whose turn it is
     * @param playerList the entire valid list of players for this turn
     */

    void takeTurn(Player player, ArrayList<Player> playerList) {
        int userInput;
        String promptString = "";
        player.hasRolled = false;
        boolean inJail;
        boolean endTurn = false;

        System.out.println(player.getName() + " it's your turn.");
        player.printPlayerDetails();

        /*
            keep looping - allow player to perform actions until they decide
            to end turn or are kicked from the game
        */
        while (!endTurn && !player.isKicked) {
            inJail = player.getJailStatus();
            if (inJail) {
                promptString = player.getName() + ": 0 to roll the dice\t1 to trade\t2 to open bank options\t3 to end turn\t4 to use \"Get Out of Jail Free\" card\t5 to pay bail";
            } else {
                promptString = player.getName() + ": 0 to roll the dice\t1 to trade\t2 to open bank options\t3 to end turn\t9 to print details";
            }
            System.out.println(promptString);
            try {
                userInput = Game.scanner.nextInt();
            } catch (InputMismatchException e) {
                Game.scanner.next();
                System.out.println("Must enter an integer");
                continue;
            }

            /*
            * Switch on user input to perform actions as they specified
            * e.g. 0 to roll the dice
            * */
            switch (userInput) {
                case 0:
                    endTurn = this.rolledDice(player);
                    break;
                case 1:
                    TradeMenu.options(player, playerList);
                    break;
                case 2:
                	BankMenu.options(player);
                    break;
                case 3:
                    //check if has rolled, then end
                    if (player.hasRolled) {
                        endTurn = true;
                    } else {
                        System.out.println("You must roll the dice before being able to end your turn");
                    }
                    break;
                case 4:
                    //get out of jail free card
                    if (inJail) {
                        this.tryUseGOOJFCard(player);
                    } else {
                        System.out.println("You're not in jail");
                    }
                    break;
                case 5:
                    //pay out of jail
                    if (inJail) {
                        this.tryPostBail(player);
                    }
                    break;
                case 9:
                	DetailsMenu.playerDetails(player, playerList);
                	break;
                default:
                    System.out.println("Invalid entry");
            }
        }
        player.setDoubles(0); //reset doubles at end of the turn
    }

    /**
     * Safely move the player forward and take the specific action as specified
     * by the space they land on
     * @param player current active player whose turn it is
     * @param playerList list of players remaining in the game
     */
    public static void movePlayerForward(Player player, ArrayList<Player> playerList) {

        /*
         * Acts as a filter for defining what action is performed when a player lands on a specific
         * BoardSpace. Static context means takeAction should not need to be called for a space anywhere
         * else as this can act as the interface for actions.
         * */

        BoardSpace bs = Board.spaces.get(player.getBoardIndex());
        bs.readDetails();
        if (bs instanceof Sites) {
            ((Sites) bs).takeAction(player, playerList);
        } else if (bs instanceof TransportSpaces) {
            ((TransportSpaces) bs).takeAction(player, playerList);
        } else if (bs instanceof Utility) {
            ((Utility) bs).takeAction(player, playerList);
        } else if (bs instanceof CommunityChest) {
            ((CommunityChest) bs).takeAction(player, playerList, commDeckIndices.get(0));
        } else if (bs instanceof Chance) {
            ((Chance) bs).takeAction(player, playerList, chanceDeckIndices.get(0));
        } else if (bs instanceof Penalties) {
            ((Penalties) bs).takeAction(player);
        } else if (bs instanceof GoToJail) {
            ((GoToJail) bs).takeAction(player);
        }
        // otherwise the BoardSpace doesn't require an action so can continue without further action
    }

    /**
     * Function where player tries to roll their way out of jail. If they roll double they are free
     * If they have been in jail too long, they are forced to pay fine
     * @param currentPlayer player whose turn it is (should be in jail)
     * @param playerList players remaining in the game
     * @param diceVal integer array of two dice values that they rolled
     */
    public void tryLeaveJail(Player currentPlayer, ArrayList<Player> playerList, int[] diceVal) {

        //need to verify doubles
        if (diceVal[0] == diceVal[1]) {
            System.out.println("You rolled doubles and have escaped from jail.");
            currentPlayer.changeJailStatus();  // freed from jail
            currentPlayer.evaluatePosition(diceVal[0] + diceVal[1]);
            currentPlayer.setTurnsInJail(0);
            movePlayerForward(currentPlayer, playerList);
        } else if (currentPlayer.getTurnsInJail() >= 2) {
            System.out.println("3rd Turn in Jail. Deducting $50 from your account.");

            //force payment of fine. If insufficient balance, then player forced to liquidate assets first
            currentPlayer.changeAccountBalance(-50, PaymentType.BANK);
            currentPlayer.changeJailStatus();
            currentPlayer.setTurnsInJail(0);
        } else {
            System.out.println("You have failed to escape from jail.");
            currentPlayer.setTurnsInJail(currentPlayer.getTurnsInJail() + 1);
        }
    }

    /**
     * Handles the actions that come about rolling the dice on a turn. Does necessary checks
     * for doubles and if they have already rolled etc
     * @param player player whose turn it currently is
     * @return boolean where true implies a forced end of turn and false allows them to continue
     */
    private boolean rolledDice(Player player) {

        if (player.hasRolled) {
            System.out.println("You have already rolled on this turn, can't roll again.");
            return false;
        }
        player.rollDice();
        player.hasRolled = true;
        System.out.println("Rolled a " + player.diceVal[0] + " and a " + player.diceVal[1]);
        if (player.getJailStatus()) {
            tryLeaveJail(player, Game.playerList, player.diceVal);
            return false;
        } else {
            player.evaluatePosition(player.diceVal[0] + player.diceVal[1]);
            //player.evaluatePosition(30); todo:REMOVE

            if (player.getBoardIndex() == 30) {  //landed on GOTOJAIL
                System.out.println("Moved to position: " + player.getBoardIndex());
                movePlayerForward(player, Game.playerList);
                return true;
            } else if (player.diceVal[0] == player.diceVal[1]) {  // rolled doubles
                System.out.println("You have rolled doubles, and get to go again.");
                player.hasRolled = false;  // as if player has not rolled yet
                player.setDoubles(player.getDoubles() + 1);
                if (player.getDoubles() >= 3) {

                    //three in succession
                    System.out.println("You have been caught speeding (rolling three doubles in succession). Go straight to jail.");
                    player.sendToJail();
                    return true;
                }
            }
            System.out.println("Moved to position: " + player.getBoardIndex());
            movePlayerForward(player, Game.playerList);
            return false;
        }
    }


    /**
     * Attempt to pay way out of jail provided balance is high enough. To be accessed from jail menu
     * @param player player object who is active. Should be in jail.
     */
    public void tryPostBail(Player player) {
        if (player.getAccountBalance() >= 50) {
            System.out.println("Paying bail of $50. You have now been released from jail.");
            player.changeAccountBalance(-50, PaymentType.BANK);
            System.out.println("New account balance: " + player.getAccountBalance());
            player.changeJailStatus();
            player.setTurnsInJail(0);
        } else {
            System.out.println("Insufficient balance to post bail.");
        }
    }

    /**
     * Get out of jail by using the Get out of Jail free card - if the player has one
     * @param player player who is active - should be in jail
     */
    public void tryUseGOOJFCard(Player player) {
        if (player.getGetOutOfJailFreeCard() > 0) {
            player.setGetOutOfJailFreeCard(player.getGetOutOfJailFreeCard() - 1);
            player.changeJailStatus();
            System.out.println("You have used a card and are freed from jail");
            player.setTurnsInJail(0);
        } else {
            System.out.println("You do not have a card to use");
        }
    }
}
