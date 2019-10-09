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
        boolean inJail = player.getJailStatus();
        boolean endTurn = false;

        System.out.println(player.getName() + " it's your turn.");
        player.printPlayerDetails();

        while (!endTurn) {

            if (inJail) {
                promptString = "0 to roll the dice\t1 to trade\t2 to open bank options\t3 to end turn\t4 to use \"Get Out of Jail Free\" card";
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
                        tryLeaveJail(player, diceVal);
                    } else {
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
                case 4:
                    //get out of jail free card
                    if (inJail) {
                        System.out.println("Use Get out of Jail Free Card");
                        break;
                    }
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    public static void tryLeaveJail(Player player, int[] diceVal) {
        //need to verify doubles
        if (diceVal[0] == diceVal[1]) {
            player.changeJailStatus();  // freed from jail
            // now move on i.e. evaluatePosition()?
        } else if (player.getTurnsInJail() >= 2) {  //todo: set as macro
            System.out.println("3rd Turn in Jail. Deducting $50 from your account.");
            //force payment of fine
            player.changeAccountBalance(-50);  //todo: set as macro
            player.changeJailStatus();
        } else {
            // increment inJail turn counter
            player.setTurnsInJail(player.getTurnsInJail() + 1);
        }
    }

}
