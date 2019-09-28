package jwrc.board;

import jwrc.player.*;

public abstract class BoardSpace {

    private int index;
    final SpaceType spaceType;

    public BoardSpace(int index, SpaceType spaceType) {
        this.index = index;
        this.spaceType = spaceType;
    }

    public int getBoardIndex() {
        return index;
    }

    public abstract void takeAction(Player player);
    
}
