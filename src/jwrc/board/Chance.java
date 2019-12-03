package jwrc.board;

import jwrc.game.Game;
import jwrc.game.Turn;
import jwrc.player.PaymentType;
import jwrc.player.Player;
import java.util.ArrayList;
import java.util.Collections;

public class Chance extends BoardSpace implements RandomizedSpace {

    public Chance(int index) {
        super(index);
    }

    public void takeAction(Player player, ArrayList<Player> players, int deckIndex) {

        int i;
        BoardSpace bs;

        switch (deckIndex) {
            case 0:
                System.out.println("Advance to \"Go\". (Collect $200)");
                player.setBoardIndex(0);
                break;
            case 1:
                System.out.println("Advance to Illinois Avenue. If you pass Go collect $200.");
                if (player.getBoardIndex() > 24) {
                    player.changeAccountBalance(+200, PaymentType.BANK);
                }
                player.setBoardIndex(24);
                Turn.movePlayerForward(player, players);
                break;
            case 2:
                System.out.println("Advance to St. Charles Place. If you pass Go collect $200.");
                if (player.getBoardIndex() > 11) {
                    player.changeAccountBalance(+200, PaymentType.BANK);
                }
                player.setBoardIndex(11);
                break;
            case 3:
                System.out.println("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total 10 times the amount thrown.");
                i = player.getBoardIndex();
                bs = Board.spaces.get(player.getBoardIndex());

                while (!(bs instanceof Utility)) {
                    bs = Board.spaces.get((player.getBoardIndex() + (++i)) % 40); // overflows
                }

                if (i >= 40) {
                    player.changeAccountBalance(+200, PaymentType.BANK);
                }

                player.setBoardIndex(i % 40);
                Turn.movePlayerForward(player, players);

                /*
                * todo: ensure Utility.takeAction requires you to roll dice in this case
                *  Could ensure this by requiring a diceVal as a prop in the function for Utility.takeAction
                * This would allow us to specify externally what the dice val would be
                * */

                break;
            case 4:
                System.out.println("Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.");
                i = player.getBoardIndex();
                bs = Board.spaces.get(player.getBoardIndex());

                while (!(bs instanceof TransportSpaces)) {
                    bs = Board.spaces.get((player.getBoardIndex() + (++i)) % 40); // overflows
                }

                if (i >= 40) {
                    player.changeAccountBalance(+200, PaymentType.BANK);
                }

                player.setBoardIndex(i % 40);
                Turn.movePlayerForward(player, players);
                break;
            case 5:
                System.out.println("Bank pays you dividend of $50.");
                player.changeAccountBalance(+50, PaymentType.BANK);
                break;
            case 6:
                System.out.println("Get out of Jail Free. This card may be kept until needed, or traded/sold.");
                player.setGetOutOfJailFreeCard(player.getGetOutOfJailFreeCard() + 1);
                break;
            case 7:
                System.out.println("Go Back Three Spaces");
                player.setBoardIndex(player.getBoardIndex() - 3);
                Turn.movePlayerForward(player, players);
                break;
            case 8:
                System.out.println("Go directly to Jail. Do not pass GO, do not collect $200.");
                player.sendToJail();
                break;
            case 9:
                System.out.println("Make general repairs on all your property: For each house pay $25, For each hotel $100.");
                int cost = 0;
                for(Sites site : player.getSites()) {
                	if(site.hasHotel) {
                		cost += 100;
                	}
                	else {
                		cost += 25*site.getNoOfHouses();
                	}
                }
                player.changeAccountBalance(-cost, PaymentType.BANK);
                System.out.println("Total repair cost: $"+cost+"\nNew account balance: $"+player.getAccountBalance());
                break;
            case 10:
                System.out.println("Pay poor tax of $15");
                player.changeAccountBalance(-15, PaymentType.BANK);
                break;
            case 11:
                System.out.println("Take a trip to Reading Railroad. If you pass Go, collect $200.");
                i = player.getBoardIndex();
                if (i > 5) {
                    player.changeAccountBalance(+200, PaymentType.BANK);
                }
                player.setBoardIndex(5);
                Turn.movePlayerForward(player, players);
                break;
            case 12:
                System.out.println("Take a walk on the Boardwalk. Advance token to Boardwalk.");
                player.setBoardIndex(39);
                Turn.movePlayerForward(player, players);
                break;
            case 13:
                System.out.println("You have been elected Chairman of the Board. Pay each player $50.");

                for (Player otherPlayer : players) {
                    otherPlayer.payToPlayer(player, 50);
                }

                break;
            case 14:
                System.out.println("Your building {and} loan matures. Receive {Collect} $150.");
                System.out.println("TO BE IMPLEMENTED");
                //todo: implement
                break;
            case 15:
                System.out.println("You have won a crossword competition. Collect $100.");
                player.changeAccountBalance(+100, PaymentType.BANK);
                break;
        }

        Collections.rotate(Game.chanceDeckIndices, -1);

    }

    public void readDetails() {
        System.out.println("You landed on Chance. Your card reads:");
    }
}
