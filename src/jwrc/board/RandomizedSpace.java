package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;

/**
 * Relates to a space on the board where the action to be performed could take a number of different
 * values. E.g. Community chest and chance
 */
public interface RandomizedSpace {
    void takeAction(Player player, ArrayList<Player> playerList, int deckIndex);
}
