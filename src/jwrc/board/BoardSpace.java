package jwrc.board;

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
    
    
}
