package jwrc.board;

/**
 * Abstract superclass extended by every square you can land on on the board
 */
public abstract class BoardSpace {

    private int index;

    /**
     * Constructor for the class
     * @param index the position on the board that every space needs
     */
    public BoardSpace(int index) {
        this.index = index;
    }

    /**
     * Return the position of the space
     * @return board position of the space
     */
    public int getBoardIndex() {
        return index;
    }

    /**
     * To be implemented by each space independently. Read the type of action that will be performed
     * by landing on this space
     */
    public abstract void readDetails();
    
}
