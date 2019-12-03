package jwrc.main;

import jwrc.game.Game;

public class Main {

    public static void main(String[] args) {

        Game game = Game.getInstance(); //singleton design - prevent duplicate Game instances
        game.preGame();
        game.start();

    }
}
