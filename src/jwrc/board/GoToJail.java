package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;


/**
 * the BoardSpace associated with sending the player who lands on it to jail
 */
public class GoToJail extends BoardSpace {

    GoToJail(int index) {
        super(index);
    }

    public void takeAction(Player player) {
        player.sendToJail();
    }

    public void readDetails() {
        System.out.println("You are being sent to jail!");
    }
}
