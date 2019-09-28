package jwrc.board;

import jwrc.player.*;

public class Penalties extends BoardSpace {


    private int penaltyAmount;  //should be a negative value

    public Penalties(int boardIndex, int penaltyAmount) {
        super(boardIndex, SpaceType.PENALTY);
        this.penaltyAmount = penaltyAmount;
    }

    public int getPenaltyAmount() {
        return this.penaltyAmount;
    }

    public void takeAction(Player player) {
        player.changeAccountBalance(penaltyAmount);
    }
}
