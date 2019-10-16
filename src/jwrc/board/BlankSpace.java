package jwrc.board;

public class BlankSpace extends BoardSpace {

    public BlankSpace(int index) {
        super(index);
    }

    public void readDetails() {
        System.out.println("You've landed on index: " + this.getBoardIndex());
        System.out.println("No action required");
    }
}
