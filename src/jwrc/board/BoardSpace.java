package jwrc.board;

import jwrc.player.*;

import java.util.ArrayList;

import jwrc.game.*;

public abstract class BoardSpace {

    private int index;
   // final SpaceType spaceType;

    public BoardSpace(int index) {
        this.index = index;
    }

    public int getBoardIndex() {
        return index;
    }

    public abstract void takeAction(Player player, ArrayList <Player> players);
    public abstract void readDetails();
    
}
