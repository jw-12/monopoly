package jwrc.game;

import jwrc.board.BoardSpace;
import jwrc.player.Player;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Turn class handles actions which proceed before and after the BoardSpace actions take place. e.g. dice rolled here
 * and post-turn prompts (e.g. h to build houses/hotels)
 */

public class Turn {


    public Turn() {

    }

    public static void takeTurn(Player player, Scanner scnr, ArrayList<BoardSpace> boardArray) {
        int userInput;
        int[] diceVal;
        String promptString = "";
        boolean hasRolled = false;
        boolean inJail;
        boolean endTurn = false;

        System.out.println(player.getName() + " it's your turn.");
        player.printPlayerDetails();

        while (!endTurn) {

            inJail = player.getJailStatus();

            if (inJail) {
                promptString = "0 to roll the dice\t1 to trade\t2 to open bank options\t3 to end turn\t4 to use \"Get Out of Jail Free\" card\t5 to pay bail";
            } else {
                promptString = "0 to roll the dice\t1 to trade\t2 to open bank options\t3 to end turn";
            }

            System.out.println(promptString);

            try {
                userInput = scnr.nextInt();
            } catch (InputMismatchException e) {
                scnr.next();
                System.out.println("Must enter an integer");
                continue;
            }

            switch (userInput) {
                case 0:
                    //roll
                    if (hasRolled) {
                        System.out.println("You have already rolled on this turn, can't roll again.");
                        break;
                    }
                    diceVal = player.rollDice();
                    hasRolled = true;
                    System.out.println("Rolled a " + diceVal[0] + " and a " + diceVal[1]);
                    if (inJail) {
                        tryLeaveJail(player, diceVal, boardArray);
                    } else {
                        if (diceVal[0] == diceVal[1]) {
                            System.out.println("You have rolled doubles, and get to go again.");
                            hasRolled = false;  // as if player has not rolled yet
                            player.setDoubles(player.getDoubles() + 1);

                            if (player.getDoubles() >= 3) {
                                //three in succession
                                System.out.println("You have been caught speeding (rolling three doubles in succession). Go straight to jail.");
                                player.sendToJail();
                                player.setDoubles(0);
                                hasRolled = true;
                                break;
                            }
                        }
                        player.evaluatePosition(diceVal[0] + diceVal[1]);
                        System.out.println("Moved to position: " + player.getBoardIndex());
                        boardArray.get(player.getBoardIndex()).takeAction(player);
                    }
                    break;
                case 1:
                    //trade
                    System.out.println("<Trade menu here>");
                    break;
                case 2:
                    //bank/property-authority options
                    System.out.println("<Property Overlord Menu Here>");
                    break;
                case 3:
                    //check if has rolled, then end
                    if (hasRolled) {
                        endTurn = true;
                    } else {
                        System.out.println("You must roll the dice before being able to end your turn");
                    }
                    break;

                    /*
                    * todo: collapse 4 and 5 into JailOptions
                    * */

                case 4:
                    //get out of jail free card
                    if (inJail) {
                        System.out.println("<Use Get out of Jail Free Card> (not implemented)");
                        break;
                    }
                case 5:
                    //pay out of jail
                    if (inJail) {
                        if (player.getAccountBalance() >= 50) {
                            System.out.println("Paying bail of €50. You have now been released from jail.");
                            player.changeAccountBalance(-50);
                            System.out.println("New account balance: " + player.getAccountBalance());
                        } else {
                            System.out.println("Insufficient balance to post bail.");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    public static void tryLeaveJail(Player player, int[] diceVal, ArrayList<BoardSpace> boardArray) {
        //need to verify doubles
        if (diceVal[0] == diceVal[1]) {
            System.out.println("You rolled doubles and have escaped from jail.");
            player.changeJailStatus();  // freed from jail
            player.evaluatePosition(diceVal[0] + diceVal[1]);
            boardArray.get(player.getBoardIndex()).takeAction(player);
        } else if (player.getTurnsInJail() >= 2) {  //todo: set as macro
            System.out.println("3rd Turn in Jail. Deducting $50 from your account.");
            //force payment of fine
            player.changeAccountBalance(-50);  //todo: set as macro
            player.changeJailStatus();
        } else {
            System.out.println("You have failed to escape from jail.");
            player.setTurnsInJail(player.getTurnsInJail() + 1);
        }
    }

}
