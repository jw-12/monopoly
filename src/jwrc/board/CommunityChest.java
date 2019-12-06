package jwrc.board;

import jwrc.game.Game;
import jwrc.player.PaymentType;
import jwrc.player.Player;
import java.util.ArrayList;
import java.util.Collections;


public class CommunityChest extends BoardSpace implements RandomizedSpace {

    //todo: move deck to a static variable here
    public CommunityChest(int index) {
        super(index);
    }

    public void takeAction(Player player, ArrayList<Player> players, int deckIndex) {

        switch (deckIndex) {
            case 0:
                System.out.println("Advance to \"Go\". (Collect $200)");
                player.setBoardIndex(0);
                player.changeAccountBalance(+200, PaymentType.BANK);
                break;
            case 1:
                System.out.println("Bank error in your favor. Collect $200.");
                player.changeAccountBalance(+200, PaymentType.BANK);
                break;
            case 2:
                System.out.println("Doctor's fees. {fee} Pay $50.");
                player.changeAccountBalance(-50, PaymentType.BANK);
                break;
            case 3:
                System.out.println("From sale of stock you get $50.");
                player.changeAccountBalance(+50, PaymentType.BANK);
                break;
            case 4:
                System.out.println("Get Out of Jail Free. This card may be kept until needed or sold/traded.");
                player.setGetOutOfJailFreeCard(player.getGetOutOfJailFreeCard() + 1);
                break;
            case 5:
                System.out.println("Go to Jail. Go directly to jail. Do not pass Go, Do not collect $200.");
                player.sendToJail();
                break;
            case 6:
                System.out.println("Grand Opera Night. Collect $50 from every player for opening night seats.");
                for (Player otherPlayer : players) {
                    player.payToPlayer(otherPlayer, 50);
                }
                break;
            case 7:
                System.out.println("Holiday Fund matures. Receive $100.");
                player.changeAccountBalance(+100, PaymentType.BANK);
                break;
            case 8:
                System.out.println("Income tax refund. Collect $20.");
                player.changeAccountBalance(+20, PaymentType.BANK);
                break;
            case 9:
                System.out.println("It is your birthday. Collect $10 from every player.");
                for (Player otherPlayer : players) {
                    player.payToPlayer(otherPlayer, 10);
                }
                break;
            case 10:
                System.out.println("Life insurance matures – Collect $100");
                player.changeAccountBalance(+100, PaymentType.BANK);
                break;
            case 11:
                System.out.println("Hospital Fees. Pay $50.");
                player.changeAccountBalance(-50, PaymentType.BANK);
                break;
            case 12:
                System.out.println("School fees. Pay $50.");
                player.changeAccountBalance(-50, PaymentType.BANK);
                break;
            case 13:
                System.out.println("Receive $25 consultancy fee.");
                player.changeAccountBalance(+25, PaymentType.BANK);
                break;
            case 14:
                System.out.println("You are assessed for street repairs: Pay $40 per house and $115 per hotel you own.");
                int cost = 0;
                for(Sites site : player.getSites()) {
                	if(site.hasHotel) {
                		cost += 115;
                	}
                	else {
                		cost += (40*site.getNoOfHouses());
                	}
                }
                player.changeAccountBalance(-cost, PaymentType.BANK);
                System.out.println("Total repair cost: $"+cost+"\nNew account balance: $"+player.getAccountBalance());
                break;
            case 15:
                System.out.println("You have won second prize in a beauty contest. Collect $10.");
                player.changeAccountBalance(+10, PaymentType.BANK);
                break;
        }

        Collections.rotate(Game.commDeckIndices, -1);
    }

    public void readDetails() {
        System.out.println("You landed on Community Chest. Your card reads:");
    }

}
