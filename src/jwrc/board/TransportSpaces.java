package jwrc.board;
import java.util.ArrayList;

import jwrc.game.Trade;
import jwrc.game.Game;
import jwrc.player.PaymentType;
import jwrc.player.Player;


/**
 * This class is for Transport board spaces. It extends the Property class.
 */
public class TransportSpaces extends Property {
	
	
	/**
	 * This Constructs a Transport with specified name, mortgage value and board index.
	 * @param name The name of the Transport.
	 * @param mortgageValue The mortgage value of the Transport.
	 * @param index The board index of the Transport.
	 */
	public TransportSpaces(String name, int mortgageValue, int index) {
		super(name,mortgageValue,index);
	}
	
	
	/**
	 * This function is used to take the action of landing on a Transport space. Depending on who lands on
	 * the space and what the status of the space is, a different action is performed. You can choose to buy
	 * of auction an unowned site when you land on it.
	 * @param player The player that lands on the Utility
	 * @param players The list of players in the game
	 */
	public void takeAction(Player player, ArrayList <Player> players) {
		
		if(this.mortgageActive) {
			System.out.println(this.getName() + " is currently mortgaged so no action is taken");
			return;
		}
		if (player.getName().equals(this.getOwner())) {
			System.out.println("You own this Transport.");
			return;
		}
		else if(this.getOwner() == null) {
			boolean exit = false;
			while(!exit) {
				System.out.println("Would you like to buy this Transport? Enter y/n. Cost = $"+ this.getCost());
				String ans = Game.scanner.next();
				switch(ans) {
				case "y":     //Transport is bought
					this.changeOwner(player.getName());
					player.changeAccountBalance(-this.getCost(), PaymentType.BANK);
					player.changeTransportsOwned(1);
					player.addProperty(this);
					System.out.println(player.getName() + " your new balance is: $"+ player.getAccountBalance());
					exit = true;
					break;
				case "n":     //Transport goes to auction
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
		else {
			Player payPlayer = new Player("null");
			int payAmount = 0;
			for(Player p : players) {
				if(p.getName().equals(this.getOwner())) {
					payPlayer = p;
				}
			}
			System.out.println("Owned by "+ payPlayer.getName());
			switch (payPlayer.getTransportsOwned()) { //Transport rent determined by number of transports owned.
			case 1:
				payAmount = 25;
				break;
			case 2: 
				payAmount = 50;
				break;
			case 3:
				payAmount = 100;
				break;
			case 4:
				payAmount = 200;
				break;
			}
			System.out.println("You must pay "+ payPlayer.getName() + " $" +payAmount);
			payPlayer.payToPlayer(player, payAmount);
			System.out.println(player.getName()+" your new balance is $"+player.getAccountBalance());
			System.out.println(payPlayer.getName()+ " your new balance is $"+ payPlayer.getAccountBalance());
		}
	}
	
	/**
	 *  Read the name and index of the Transport when a player lands on it.
	 */
	public void readDetails() {
		System.out.println("This is a Transport Space called "+ this.getName());
	}
	
}
