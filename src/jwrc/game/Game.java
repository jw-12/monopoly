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
 */

public class Game {

    private static Game uniqueInstance = null;

    private static int maxPlayers = 6, minPlayers = 2;
    public static ArrayList <Player> playerList;
    public static ArrayList<Integer> commDeckIndices; //todo: maybe private static inside community chest etc
    public static ArrayList<Integer> chanceDeckIndices;
    public static Scanner scanner;
    public Board board;
    private static int playersKicked;

    private Game() {
        playerList = new ArrayList<Player>();
        commDeckIndices = this.generateDeck();
        chanceDeckIndices = this.generateDeck();
        scanner = new Scanner(System.in);
        playersKicked = 0;
    }

    public static Game getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Game();
        return uniqueInstance;
    }

    public void preGame() {
        int numPlayers = 0;

        this.board = new Board();

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

        for (int i=0; i<numPlayers; i++) {
            String str;
            System.out.println("Name for Player " + (i + 1) + ": ");
            str = scanner.next();
            Player player = new Player(str);
            playerList.add(player);
        }

    }

    public void start() {
        Player currentPlayer;

        Turn turn = new Turn(commDeckIndices, chanceDeckIndices);
        

        while(playersKicked < 2 && (playerList.size() > 1)) {
            currentPlayer = playerList.get(0);
            turn.takeTurn(currentPlayer, playerList);
            Collections.rotate(playerList, -1);
        }

        //todo: need to calculate assets here
        this.endGame();

        scanner.close();

    }

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

    public static void kickPlayerFromGame(Player player) {
        playerList.remove(player);
        player.isKicked = true;
        playersKicked += 1;
        System.out.println(player.getName() + " has been kicked from the game.");
        Collections.rotate(playerList, 1);
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
