package jwrc.board;
import java.util.ArrayList;

import jwrc.game.Trade;
import jwrc.game.Game;
import jwrc.player.PaymentType;
import jwrc.player.Player;

/**
 * This class is for utility board spaces. It extends the Property class.
 */

public class Utility extends Property {
	
	/**
	 * This Constructs a utility with specified name, mortgage value and board index.
	 * @param name The name of the utility.
	 * @param mortageValue The mortgage value of the utility.
	 * @param index The board index of the utility.
	 */
	public Utility(String name, int mortageValue, int index) {
		super(name,mortageValue,index);
	}
	
	/**
	 * This function is used to take the action of landing on a utility space. Depending on who lands on
	 * the space and what the status of the space is, a different action is performed.
	 * @param player The player that lands on the Utility
	 * @param players The list of players in the game
	 */
	public void takeAction(Player player, ArrayList <Player> players) {
		
		if(this.mortgageActive) {
			System.out.println(this.getName() + " is currently mortgaged so no action is taken");
			return;
		}
		if (player.getName().equals(this.getOwner())) {
				System.out.println("You own this Utility.");
				return;
		}
		else if(this.getOwner() == null) {
			boolean exit = false;
			while(!exit) {
				System.out.println("Would you like to buy this Utility? Enter y/n. Cost = $"+ this.getCost());
				String ans = Game.scanner.next();
				switch(ans) {
					case "y": //site is bought
						this.changeOwner(player.getName());
						player.changeAccountBalance(-this.getCost(), PaymentType.BANK);
						System.out.println(player.getName() + " your new balance is: $"+ player.getAccountBalance());
						player.changeUtilitiesOwned(1);
						player.addProperty(this);
						exit = true;
						break;
					case "n": // if not bought, site goes to auction
						System.out.println("Go to auction");
						ArrayList<Player> auctionPlayers = new ArrayList<Player>(players);
						Trade.startAuction(auctionPlayers, this);
						exit = true;
						break;
					default:
						System.out.println("not a valid input!");
					}
				}
		}
		else { // if site is owned by another player
			Player payPlayer = new Player("null");
			int payAmount = 0;
			for(Player p : players) { // find the player to pay rent to
				if(p.getName().equals(this.getOwner())) {
					payPlayer = p;
				}
			}
			System.out.println("Owned by "+ payPlayer.getName());
			if(payPlayer.getUtilitiesOwned() == 1) { // if only 1 utility is owned
				payAmount = 4*(player.diceVal[0]+player.diceVal[1]);
			}
			else {									  // if both utilities are owned
				payAmount = 10*(player.diceVal[0]+player.diceVal[1]);
			}
			System.out.println("You must pay "+ payPlayer.getName() + " $" +payAmount);
			payPlayer.payToPlayer(player, payAmount);
			System.out.println(player.getName()+" your new balance is $"+player.getAccountBalance());
			System.out.println(payPlayer.getName()+ " your new balance is $"+ payPlayer.getAccountBalance());
		}
	}
	
	/**
	 *  Read the name and index of the utility when a player lands on it.
	 */
	public void readDetails() {
		System.out.println("This is a Utility called "+ this.getName() +" at board index " +this.getBoardIndex());
	}
	
}
