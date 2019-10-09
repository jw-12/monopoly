package jwrc.game;

import jwrc.board.BoardSpace;
import jwrc.board.SpaceType;
import jwrc.player.Player;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Turn class handles actions which proceed before and after the BoardSpace actions take place. e.g. dice rolled here
 * and post-turn prompts (e.g. h to build houses/hotels)
 */

public class Turn {

    public Turn() {

    }

    public static void beginTurn(Player player, Scanner input) {
        System.out.println(player.getName() + " it's your turn.");
        player.printPlayerDetails();
        System.out.println("----------------------\nPress 'r' to roll dice:");
        input.next();
        //int diceVal = player.rollDice();
        int diceVal = 2;
        System.out.println("Rolled a " + diceVal);
        player.evaluatePosition(diceVal);
        System.out.println("Moved to position: " + player.getBoardIndex());
    }

    /*
    * Present and prompt the options the user can input at the end of their turn
    * */
    public static void endTurn(Player player, Scanner input) {
        ArrayList<Options> optArray;
        optArray = getEndOptions(player);
        printOptions(optArray);
        String keyIn = input.next();

        // get input keys for allowed options
        ArrayList<String> possibleKeys = new ArrayList<>();
        for (Options opt : optArray) {
            possibleKeys.add(opt.keyIn);
        }

        // keep asking if they press a key which does not relate to an option they currently have
        while (!possibleKeys.contains(keyIn)) {
            System.out.println("Invalid option, try again.");
            keyIn = input.next();
        }

        System.out.println("----------------------");
    }


    /**
     * return the ArrayList of Options that are given to the current player at the end of their turn
     * @param player the person whose turn it currently is
     * @return ArrayList of Options that are given to the current player at the end of this turn
     */
    public static ArrayList<Options> getEndOptions(Player player) {
        ArrayList<Options> optArray = new ArrayList<Options>();

        optArray.add(Options.E_TO_END);  // always added so long as options only at end of turn
        optArray.add(Options.B_TO_BUY);  // buy property off another player
        optArray.add(Options.S_TO_SELL);  // sell property to another player

        //conditional check if player can build etc


        return optArray;
    }

    /**
     * prints the possible options to the user given the ArrayList of options they have
     * @param opt the ArrayList of possible actions the user can take at this time
     */
    public static void printOptions(ArrayList<Options> opt) {
        StringBuilder strOut = new StringBuilder();

        strOut.append("Press:\t");

        for (Options o : opt) {
            if (o == Options.E_TO_END) {
                strOut.append("e to end\t");
            } else if (o == Options.B_TO_BUY) {
                strOut.append("b to buy a property (from another player)\t");
            } else if (o == Options.S_TO_SELL) {
                strOut.append("s to sell a property\t");
            }
        }

        System.out.println(strOut.toString());
    }

}
