package jwrc.board;

import jwrc.player.*;

public class Penalties extends BoardSpace {

    private String penaltyName;

    private int penaltyAmount;  //initialised as positive

    /**
     * Constructor to the class
     * @param boardIndex position on the board
     * @param penaltyAmount amount player will be penalized
     * @param penaltyName The name of the penalty on the board
     */
    public Penalties(int boardIndex, int penaltyAmount, String penaltyName) {
        super(boardIndex);
        this.penaltyAmount = penaltyAmount;
        this.penaltyName = penaltyName;
    }

    /**
     * Action to be performed by landing on the space
     * @param player player object who landed on it
     */
    public void takeAction(Player player) {
        player.changeAccountBalance(-penaltyAmount, PaymentType.BANK);
        System.out.println("New account balance: $" + player.getAccountBalance());
    }

    /**
     * see super
     */
    public void readDetails() {
		System.out.println("You landed on " + penaltyName +"\nDeducting " +penaltyAmount + " from your account");
	}

}
