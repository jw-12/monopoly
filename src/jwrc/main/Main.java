package jwrc.main;

import jwrc.board.*;
import jwrc.game.Game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Game game = new Game(input);
        game.preGame();
        game.start();
        
        
        
        
        
       // Board newBoard = new Board();
       // newBoard.setBoard();
        System.out.println("whose turn = "+ game.getWhoseTurn());
        
    }
}
