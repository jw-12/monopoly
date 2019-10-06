package jwrc.main;

import jwrc.board.Board;
import jwrc.game.Auction;
import jwrc.game.Game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Auction.testAuction();

        /*Game game = new Game(input);
        game.preGame();
        game.start();
        
        //Board newBoard = new Board();
        //newBoard.setBoard();

        input.close();*/

    }
}
