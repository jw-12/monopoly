package jwrc.board;

import jwrc.player.*;

public class Penalties extends BoardSpace {

    private String penaltyName;

    private int penaltyAmount;  //initialised as positive

    public Penalties(int boardIndex, int penaltyAmount, String penaltyName) {
        super(boardIndex, SpaceType.PENALTY);
        this.penaltyAmount = penaltyAmount;
        this.penaltyName = penaltyName;
    }

    public int getPenaltyAmount() {
        return this.penaltyAmount;
    }

    public void takeAction(Player player) {
        System.out.println("You landed on " + this.getPenaltyName() + ".\nDeducting €" + this.getPenaltyAmount() + " from your account.");
        player.changeAccountBalance(-penaltyAmount);
        System.out.println("New account balance: €" + player.getAccountBalance());
    }

    public String getPenaltyName() {
        return penaltyName;
    }

    public SpaceType getSpaceType() {
        return this.spaceType;
    }
}
