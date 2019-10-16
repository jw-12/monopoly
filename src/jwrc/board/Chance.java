package jwrc.board;

import jwrc.player.Player;
import java.util.ArrayList;

public class Chance extends BoardSpace implements RandomizedSpace {

    public Chance(int index) {
        super(index);
    }

    public void takeAction(Player player, ArrayList<Player> players, int deckIndex) {

        switch (deckIndex) {
            case 0:
                System.out.println(deckIndex);
                break;
            case 1:
                System.out.println(deckIndex);
                break;
            case 2:
                System.out.println(deckIndex);
                break;
            case 3:
                System.out.println(deckIndex);
                break;
            case 4:
                System.out.println(deckIndex);
                break;
            case 5:
                System.out.println(deckIndex);
                break;
            case 6:
                System.out.println(deckIndex);
                break;
            case 7:
                System.out.println(deckIndex);
                break;
            case 8:
                System.out.println(deckIndex);
                break;
            case 9:
                System.out.println(deckIndex);
                break;
            case 10:
                System.out.println(deckIndex);
                break;
            case 11:
                System.out.println(deckIndex);
                break;
            case 12:
                System.out.println(deckIndex);
                break;
            case 13:
                System.out.println(deckIndex);
                break;
            case 14:
                System.out.println(deckIndex);
                break;
            case 15:
                System.out.println(deckIndex);
                break;
        }

    }

    public void readDetails() {
        System.out.println("You landed on Chance");
    }
}
