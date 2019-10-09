package jwrc.player;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private String name;
    private int boardIndex;
    private int accountBalance;
    private int transportsOwned;

    public Player(String name) {
        this.name = name;
        this.accountBalance = 1500;
        this.transportsOwned = 0;
    }

    public int rollDice() {
        return ThreadLocalRandom.current().nextInt(2, 13);  // must be max+1
    }

    public void evaluatePosition(int diceVal) {
        this.boardIndex += diceVal;

        // passing 'GO'
        if(this.boardIndex >= 40) {
            this.boardIndex = this.boardIndex % 40;
            changeAccountBalance(+200);  // and add 200$ to account
        }
    }

    public String getName() {
        return name;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public int getAccountBalance() {
        return this.accountBalance;
    }
    
    public int getTransportsOwned() {
    	return this.transportsOwned;
    }

    public void changeAccountBalance(int delta) {  // delta +ve for gains or -ve for fines etc.
        this.accountBalance += delta;
    }

    public void printPlayerDetails() {
        System.out.println(
                "Balance: €" + this.getAccountBalance() +
                "\nBoard Position: " + this.getBoardIndex() + "/40"
        );
    }
}
