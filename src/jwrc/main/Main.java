package jwrc.main;

import jwrc.game.Game;

/**
 * Main class. main() method called to initiate the game sequences
 */

public class Main {

    /**
     * main game method to be
     * @param args no input arguments required
     */
    public static void main(String[] args) {

        Game game = Game.getInstance(); //singleton design - prevent duplicate Game instances
        game.preGame();
        game.start();

    }
}
