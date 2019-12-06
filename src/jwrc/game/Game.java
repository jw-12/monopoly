package jwrc.game;

import jwrc.board.Board;
import jwrc.board.Sites;
import jwrc.board.Property;
import jwrc.player.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the main game component. Manages actions and records metrics of the game itself.
 * Singleton design so as to ensure only one instance.
 */

public class Game {

    private static Game uniqueInstance = null; // ensure Singleton design

    public static ArrayList <Player> playerList;
    public static ArrayList<Integer> commDeckIndices;
    public static ArrayList<Integer> chanceDeckIndices;
    public static Scanner scanner;
    public Board board;
    private static int playersKicked;

    /**
     * Game constructor. Generates the community chest and chance decks, generates empty player list
     * and sets up static scanner object to be used across the game
     */
    private Game() {
        playerList = new ArrayList<Player>();
        commDeckIndices = this.generateDeck();
        chanceDeckIndices = this.generateDeck();
        scanner = new Scanner(System.in);
        playersKicked = 0;
    }

    /**
     * Constructs a game instance if one not created yet, otherwise returns the active game instance
     * @return Game instance
     */
    public static Game getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Game();
        return uniqueInstance;
    }

    /**
     * sets up the game by asking for user input for number of players, names, colours etc.
     */
    public void preGame() {
        int numPlayers = 0;
        this.board = new Board(); //initialize board component

        /* user interaction getting names etc*/
        System.out.println("------------------------------------------------\n\t\t\tWelcome to Monopoly!\n------------------------------------------------");
        // ensure valid number before proceeding
        int maxPlayers = 6;
        int minPlayers = 2;
        while(numPlayers > maxPlayers || numPlayers < minPlayers) {
            System.out.println("How many players? (must be 2-6 players)");
            try {
                numPlayers= scanner.nextInt();

            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Must enter integer between 2 and 6");
            }
        }
        for (int i=0; i<numPlayers; i++) {
            String str;
            System.out.println("Name for Player " + (i + 1) + ": ");
            str = scanner.next();
            Player player = new Player(str.toUpperCase()); //force uppercase name
            playerList.add(player);
        }
    }

    /**
     * implements the main game loop - checks when end-game conditions have been met
     */
    public void start() {
        Player currentPlayer;
        Turn turn = new Turn(commDeckIndices, chanceDeckIndices); //instantiate Turn object

        /* Keep taking turns with each player until end conditions are met */
        while(playersKicked < 2 && (playerList.size() > 1)) {
            currentPlayer = playerList.get(0);
            turn.takeTurn(currentPlayer, playerList);
            Collections.rotate(playerList, -1);
        }
        this.endGame();
        scanner.close(); // close the game's input scanner object
    }

    /**
     * Runs at the end of the game to show the remaining players and their respective liquidated values.
     * Player with the highest value after liquidation wins.
     */
    public void endGame() {
        System.out.println("Remaining players:");

        for (Player p : playerList) {
        	p.changeAccountBalance(2*(Sites.liquidateBuildings(p)), PaymentType.BANK);
        	for(Property prop : p.getPropertiesOwned()) {
        		if(prop.mortgageActive) {
        			p.changeAccountBalance(prop.getmortgageValue(), PaymentType.BANK);
        		}
        		else {
        			p.changeAccountBalance(2*(prop.getmortgageValue()), PaymentType.BANK);
        		}
        	}
            System.out.println(p.getName() + " with valuation of: $ " + p.getAccountBalance());
        }
        System.out.println("Highest valued player wins!");
    }

    /**
     * Used to remove a player from the game if they go Bankrupt.
     * @param player the player who is to be kicked from the game
     */
    public static void kickPlayerFromGame(Player player) {
        playerList.remove(player);
        player.isKicked = true;
        playersKicked += 1;
        System.out.println(player.getName() + " has been kicked from the game.");
        Collections.rotate(playerList, 1);
    }

    /**
     * Generic deck generating function.
     * @return shuffled ArrayList of integers corresponding to index of a community chest/chance card
     */
    private ArrayList<Integer> generateDeck() {
        ArrayList<Integer> deckIndices = new ArrayList<>(16);
        for (int i=0; i<16;i++) {
            deckIndices.add(i);
        }
        Collections.shuffle(deckIndices);
        return deckIndices;
    }
}
