package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;

/**
 * if a space can be sold from player to player then this should be implemented
 */
public interface Sellable {
    void takeAction(Player player, ArrayList<Player> playerList);
}
