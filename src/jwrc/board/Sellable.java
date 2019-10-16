package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;

public interface Sellable {
    void takeAction(Player player, ArrayList<Player> playerList);
}
