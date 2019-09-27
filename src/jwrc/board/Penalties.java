package jwrc.board;

public class Penalties extends BoardSpace {


    private int penaltyAmount;

    public Penalties(int boardIndex, int penaltyAmount) {
        super(boardIndex, SpaceType.PENALTY);
        this.penaltyAmount = penaltyAmount;
    }

    public int getPenaltyAmount() {
        return this.penaltyAmount;
    }

}
