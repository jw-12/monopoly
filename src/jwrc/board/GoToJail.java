package jwrc.board;

import jwrc.player.Player;


/**
 * the BoardSpace associated with sending the player who lands on it to jail
 */
public class GoToJail extends BoardSpace {

    public GoToJail(int index, SpaceType spaceType) {
        super(index, spaceType);
    }

    public void takeAction(Player player) {
        System.out.println("You are being sent to jail, ya durty bowsie.");
        player.sendToJail();
    }

    // to be deleted in future versions
    public SpaceType getSpaceType() {
        return null;
    }
}
