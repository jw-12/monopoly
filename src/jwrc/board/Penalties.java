package jwrc.board;

public class Penalties extends BoardSpace {


    private int penaltyAmount;

    public Penalties(int penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public int getPenaltyAmount() {
        return this.penaltyAmount;
    }

}
