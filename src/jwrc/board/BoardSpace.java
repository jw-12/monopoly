package jwrc.board;

public abstract class BoardSpace {

    private int index;

    public BoardSpace(int index) {
        this.index = index;
    }

    public int getBoardIndex() {
        return index;
    }

    public abstract void readDetails();
    
}
