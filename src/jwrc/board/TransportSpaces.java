package jwrc.board;
import java.util.ArrayList;
import java.util.Scanner;

import jwrc.game.Auction;
import jwrc.player.Player;

public class TransportSpaces extends Property {
	
	private Scanner input = new Scanner(System.in);
	
	public TransportSpaces(String name, int cost, int index) {
		super(name,cost,index);
	}
	
	public void takeAction(Player player, ArrayList <Player> players) {
		
		if (player.getName() == this.getOwner()) {
			System.out.println("You own this Transport.");
		}
		else if(this.getOwner() == "null") {
			int exit =0;
			while(exit == 0) {
			System.out.println("Would you like to buy this Transport? Enter y/n. Cost = "+ this.getCost());
			String ans = input.next();
			switch(ans) {
			case "y":
				this.changeOwner(player.getName());
				player.changeAccountBalance(this.getCost());
				System.out.println(player.getName() + " your new balance is: "+ player.getAccountBalance());
				exit = 1;
				break;
			case "n":
				System.out.println("Go to auction");
				Auction.startAuction(players, this, input);
				exit = 1;
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
			System.out.println("You must pay "+ payPlayer.getName() + " " +payAmount);
			player.changeAccountBalance(-payAmount);
			payPlayer.changeAccountBalance(+payAmount);
			System.out.println(player.getName()+" your new balance is "+player.getAccountBalance());
			System.out.println(payPlayer.getName()+ " your new balance is "+ payPlayer.getAccountBalance());
		}
	}
	
	public void readDetails() {
		System.out.println("This is a Transport Space called "+ this.getName());
	}
	
}
