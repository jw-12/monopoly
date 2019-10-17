package jwrc.game;

import jdk.jshell.execution.Util;
import jwrc.board.*;
import jwrc.player.Player;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Turn class handles actions which proceed before and after the BoardSpace actions take place. e.g. dice rolled here
 * and post-turn prompts (e.g. h to build houses/hotels)
 */

public class Turn {

    private ArrayList<BoardSpace> boardSpaces;
    public static ArrayList<Integer> commDeckIndices;
    public static ArrayList<Integer> chanceDeckIndices;

    public Turn(ArrayList<BoardSpace> boardSpaces, ArrayList<Integer> commDeckIndices, ArrayList<Integer> chanceDeckIndices) {
        this.boardSpaces = boardSpaces;
        commDeckIndices = commDeckIndices;
        chanceDeckIndices = chanceDeckIndices;
    }

    public void takeTurn(Player player, ArrayList<Player> playerList, Scanner scnr) {
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
                        tryLeaveJail(player, playerList, diceVal);
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
                        //player.evaluatePosition(diceVal[0] + diceVal[1]);
                        player.evaluatePosition(1);
                        System.out.println("Moved to position: " + player.getBoardIndex());
                        movePlayerForward(player, playerList);
                    }
                    break;
                case 1:
                    //trade
                    System.out.println("<Trade menu here>");
                    break;
                case 2:
                    //bank/property-authority options
                	//testing buying houses for now.
                	String str="";
                    System.out.println("<Property Overlord Menu Here>");
                    System.out.print("what property would you like to build on?");
                    scnr.nextLine();
                    str += scnr.nextLine();
                    System.out.println(str);
                    Sites constructionSite;
                    for (Sites s : Board.sitesBoard) {
                    	if(s.getName().equals(str)) {
                    		constructionSite = s;
                    		System.out.println("the owner of site "+constructionSite.getName()+" is "+  constructionSite.getOwner() );
                    		System.out.println("accepted : calling buildHouse Function");
                    		PropertyOverlord.BuildHouse(player, constructionSite);
                    		this.boardSpaces.get(player.getBoardIndex()).readDetails();
                    		break;
                    	}
                    }                                       
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

    /*
    * Acts as a filter for defining what action is performed when a player lands on a specific
    * BoardSpace. Static context means takeAction should not need to be called for a space anywhere
    * else as this can act as the interface for actions.
    * */

    public static void movePlayerForward(Player player, ArrayList<Player> playerList) {
        BoardSpace bs = Board.chanceBoard.get(player.getBoardIndex());
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
        }
        // otherwise the BoardSpace doesn't require an action so can continue without further action
    }

    public void tryLeaveJail(Player currentPlayer, ArrayList<Player> playerList, int[] diceVal) {
        //need to verify doubles
        if (diceVal[0] == diceVal[1]) {
            System.out.println("You rolled doubles and have escaped from jail.");
            currentPlayer.changeJailStatus();  // freed from jail
            currentPlayer.evaluatePosition(diceVal[0] + diceVal[1]);
            movePlayerForward(currentPlayer, playerList);
        } else if (currentPlayer.getTurnsInJail() >= 2) {  //todo: set as macro
            System.out.println("3rd Turn in Jail. Deducting $50 from your account.");
            //force payment of fine
            currentPlayer.changeAccountBalance(-50);  //todo: set as macro
            currentPlayer.changeJailStatus();
        } else {
            System.out.println("You have failed to escape from jail.");
            currentPlayer.setTurnsInJail(currentPlayer.getTurnsInJail() + 1);
        }
    }
}
