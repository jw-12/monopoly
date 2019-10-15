package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;


/**
 * the BoardSpace associated with sending the player who lands on it to jail
 */
public class GoToJail extends BoardSpace {

    public GoToJail(int index) {
        super(index);
    }

    public void takeAction(Player player, ArrayList<Player> playerList) {
        player.sendToJail();
    }

    public void readDetails() {
        System.out.println("You are being sent to jail, ya durty bowsie.");
    }
}
