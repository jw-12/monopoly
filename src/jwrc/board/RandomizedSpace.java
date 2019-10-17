package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;

public interface RandomizedSpace {
    void takeAction(Player player, ArrayList<Player> playerList, int deckIndex);
}
