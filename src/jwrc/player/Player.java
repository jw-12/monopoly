package jwrc.player;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private String name;
    private int boardIndex;

    public Player(String name) {
        this.name = name;
    }

    public int rollDice() {
        return ThreadLocalRandom.current().nextInt(2, 13);  // must be max+1
    }

    public void evaluatePosition(int diceVal) {
        this.boardIndex += diceVal;

        if(this.boardIndex >= 40) {
            this.boardIndex = this.boardIndex % 40;
            // and add 200$ to account
        }
    }

    public String getName() {
        return name;
    }

    public int getBoardIndex() {
        return boardIndex;
    }
}
