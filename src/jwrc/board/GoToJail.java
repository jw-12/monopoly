package jwrc.board;

import jwrc.player.Player;

/**
 * the BoardSpace associated with sending the player who lands on it to jail
 */
public class GoToJail extends BoardSpace {

    /**
     * Constructor to the class
     * @param index position it will be at
     */
    GoToJail(int index) {
        super(index);
    }

    /**
     * perform the native action of the space - send to jail
     * @param player player to be sent to jail
     */
    public void takeAction(Player player) {
        player.sendToJail();
    }

    /**
     * see super
     */
    public void readDetails() {
        System.out.println("You are being sent to jail!");
    }
}
