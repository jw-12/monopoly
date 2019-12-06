package jwrc.board;

/**
 * This class is for board spaces that do not require an action such as "visiting jail", "free parking" and "passing GO"
 */
public class BlankSpace extends BoardSpace {

	/**
	 * This constructs a blankspace with specified board index.
	 * @param index the index of the blackspace.
	 */
    public BlankSpace(int index) {
        super(index);
    }

    /**
     * Read the Index of the blackspace.
     */
    public void readDetails() {
        System.out.println("You've landed on index: " + this.getBoardIndex());
        System.out.println("No action required");
    }
}
