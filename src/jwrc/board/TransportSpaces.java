package jwrc.board;
import java.util.ArrayList;

import jwrc.game.Trade;
import jwrc.game.Game;
import jwrc.player.Player;

public class TransportSpaces extends Property {
	
	public TransportSpaces(String name, int mortgageValue, int index) {
		super(name,mortgageValue,index);
	}
	
	public void takeAction(Player player, ArrayList <Player> players) {
		
		if(this.mortgageActive) {
			System.out.println(this.getName() + " is currently mortgaged so no action is taken");
			return;
		}
		if (player.getName() == this.getOwner()) {
			System.out.println("You own this Transport.");
			return;
		}
		else if(this.getOwner() == "null") {
			boolean exit = false;
			while(!exit) {
				System.out.println("Would you like to buy this Transport? Enter y/n. Cost = $"+ this.getCost());
				String ans = Game.scanner.next();
				switch(ans) {
				case "y":
					this.changeOwner(player.getName());
					player.changeAccountBalance(-this.getCost());
					player.changeTransportsOwned(1);
					player.addProperty(this);
					System.out.println(player.getName() + " your new balance is: $"+ player.getAccountBalance());
					exit = true;
					break;
				case "n":
					System.out.println("Go to auction");
					ArrayList<Player> auctionPlayers = new ArrayList<Player>(players);
					Trade.startAuction(auctionPlayers, this, Game.scanner);
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
				if(p.getName() == this.getOwner()) {
					payPlayer = p;
				}
			}
			System.out.println("Owned by "+ payPlayer.getName());
			switch (payPlayer.getTransportsOwned()) {
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
			player.changeAccountBalance(-payAmount);
			payPlayer.changeAccountBalance(+payAmount);
			System.out.println(player.getName()+" your new balance is $"+player.getAccountBalance());
			System.out.println(payPlayer.getName()+ " your new balance is $"+ payPlayer.getAccountBalance());
		}
	}
	
	public void readDetails() {
		System.out.println("This is a Transport Space called "+ this.getName());
	}
	
}
