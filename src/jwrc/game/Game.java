package jwrc.game;

import jwrc.board.Board;
import jwrc.board.BoardSpace;
import jwrc.player.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the main game component. Manages actions and records metrics of the game itself.
 */

public class Game {

    private int numPlayers;
    private static int maxPlayers = 6, minPlayers = 2;
    private ArrayList <Player> playerList;
    private int whoseTurn;
    private Board board;
    private ArrayList<BoardSpace> boardArray;
    private ArrayList<Integer> commDeckIndices;
    private ArrayList<Integer> chanceDeckIndices;
    public static Scanner scanner;

    public Game() {
        this.playerList = new ArrayList<Player>();
        this.whoseTurn = 0;
        this.board = new Board();
        this.boardArray = board.getTestBoard();
        this.commDeckIndices = this.generateDeck();
        this.chanceDeckIndices = this.generateDeck();
        scanner = new Scanner(System.in);
    }

    public void preGame() {
        int numPlayers = 0;

        //  user interaction
        System.out.println("Welkom!");

        // ensure valid number before proceeding
        while(numPlayers > maxPlayers || numPlayers < minPlayers) {
            System.out.println("How many players? (must be 2-6 players)");
            try {
                numPlayers= scanner.nextInt();

            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Must enter integer between 2 and 6");
            }
        }
        this.numPlayers = numPlayers;
        System.out.println("Number of players: " + this.numPlayers);

        for (int i=0; i<numPlayers; i++) {
            String str;
            System.out.println("Name for Player " + (i + 1) + ": ");
            str = scanner.next();
            Player player = new Player(str);
            this.playerList.add(player);
        }

    }

    public void start() {
        Player currentPlayer;

        Turn turn = new Turn(boardArray, this.commDeckIndices, this.chanceDeckIndices);
        

        while(numPlayers > 1) {  // todo: need to make a check with bank after every turn if number of players bankrupt exceeds 2
            currentPlayer = this.playerList.get(whoseTurn);
            turn.takeTurn(currentPlayer, playerList);
            this.whoseTurn++;
            this.whoseTurn = this.whoseTurn % this.numPlayers;  // whoseTurn always in range [0, numPlayers-1]
        }

        scanner.close();

    }

    public ArrayList<Integer> generateDeck() {
        ArrayList<Integer> deckIndices = new ArrayList<>(16);
        for (int i=0; i<16;i++) {
            deckIndices.add(i);
        }
        Collections.shuffle(deckIndices);
        return deckIndices;
    }

}
