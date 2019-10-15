package jwrc.board;
import java.util.ArrayList;
import java.util.Scanner;

import jwrc.game.Auction;
import jwrc.player.Player;



public class Utility extends Property {
	
	private Scanner input = new Scanner(System.in);
	
	public Utility(String name, int cost, int index) {
		super(name,cost,index);
	}
	
	public void takeAction(Player player, ArrayList <Player> players) {
		
		if (player.getName() == this.getOwner()) {
				System.out.println("You own this Utility.");
		}
		else if(this.getOwner() == "null") {
			int exit =0;
			while(exit == 0) {
			System.out.println("Would you like to buy this Utility? Enter y/n. Cost = "+ this.getCost());
			String ans = input.next();
			switch(ans) {
				case "y":
					this.changeOwner(player.getName());
					player.changeAccountBalance(-this.getCost());
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
			for(Player p : players) {
				if(p.getName() == this.getOwner()) {
					payPlayer = p;
				}
			}
			System.out.println("Owned by "+ payPlayer.getName());
			//int payAmount = (player.rollDice())*4; //just for now. should be previous roll, not a new dice roll.
			int payAmount = 5;
			System.out.println("You must pay "+ payPlayer.getName() + " " +payAmount);
			player.changeAccountBalance(-payAmount);
			payPlayer.changeAccountBalance(+payAmount);
			System.out.println(player.getName()+" your new balance is "+player.getAccountBalance());
			System.out.println(payPlayer.getName()+ " your new balance is "+ payPlayer.getAccountBalance());
		}
	}
			
	public void readDetails() {
		System.out.println("This is a Utility called "+ this.getName() +" at board index " +this.getBoardIndex());
	}
	
}
