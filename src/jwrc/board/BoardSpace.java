package jwrc.board;

public abstract class BoardSpace {

    private int index;
    final SpaceType spaceType;

    public BoardSpace(SpaceType spaceType) {
        this.spaceType = spaceType;
    }
}
