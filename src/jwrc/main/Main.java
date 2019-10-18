package jwrc.main;

import jwrc.game.Game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        game.preGame();
        game.start();

    }
}
