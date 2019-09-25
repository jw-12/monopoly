package jwrc.game;

import jwrc.player.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main game component. Manages actions and records metrics of the game itself.
 */

public class Game {

    private int numPlayers;
    private static int maxPlayers = 6, minPlayers = 2;
    //private ArrayList <Player> players;
    private ArrayList <Player> players;
    private int whoseTurn;
    private Scanner input;  // to read input stream

    public Game(Scanner input) {
        this.input = input;
        this.players = new ArrayList<Player>();
        this.whoseTurn = 0;
    }

    public void preGame() {
        int numPlayers;

        //  user interaction
        System.out.println("Welkom!");
        System.out.println("How many players? (2-6 players)");
        numPlayers= input.nextInt();

        // ensure valid number before proceeding
        while(numPlayers > maxPlayers || numPlayers < minPlayers) {
            System.out.println("Must be between 2 and 6 players.");
            numPlayers= input.nextInt();
        }
        this.numPlayers = numPlayers;
        System.out.println("Number of players: " + this.numPlayers);

        for (int i=0; i<numPlayers; i++) {
            String str;
            System.out.println("Name for Player " + (i + 1) + ": ");
            str = input.next();
            Player player = new Player(str);
            this.players.add(player);
        }

    }

    public void start() {
        Player currentPlayer;
        int diceVal;

        for(int i=0; i<10; i++) {  // only ten turns taken for now
            currentPlayer = this.players.get(whoseTurn);
            System.out.println( currentPlayer.getName() + ", it's your turn! Currently on position: " + currentPlayer.getBoardIndex());
            diceVal = currentPlayer.rollDice();
            System.out.println("Rolled a " + diceVal);
            currentPlayer.evaluatePosition(diceVal);
            System.out.println("Moved to position: " + currentPlayer.getBoardIndex());
            this.whoseTurn++;
            this.whoseTurn = this.whoseTurn % this.numPlayers;  // whoseTurn always in range [0, numPlayers-1]
        }

        int x=0;
        while(x<12) {
            x = this.players.get(whoseTurn).rollDice();
        }
        System.out.println(x);
    }

}