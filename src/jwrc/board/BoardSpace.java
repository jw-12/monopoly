package jwrc.board;

import jwrc.player.*;

import java.util.ArrayList;

import jwrc.game.*;

public abstract class BoardSpace {

    private int index;
   // final SpaceType spaceType;

    public BoardSpace(int index) {
        this.index = index;
       // this.spaceType = spaceType;
    }

    public int getBoardIndex() {
        return index;
    }

    public abstract void takeAction(Player player, ArrayList <Player> players, int whoseturn);

   // public abstract SpaceType getSpaceType();
    
}
