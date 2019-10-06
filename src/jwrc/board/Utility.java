package jwrc.board;
import java.util.ArrayList;
import java.util.Scanner;

import jwrc.player.Player;

public class Utility extends Property {
	
	private Scanner input = new Scanner(System.in);
	
	public Utility(String name, int cost, int index) {
		super(name,cost,index);
	}
	
	public void takeAction(Player player, ArrayList <Player> players, int whoseturn) {
		
		if (whoseturn == this.getOwnerIndex()) {
				System.out.println("You own this Property.");
		}
		else if(this.getOwnerIndex() == 99) {
			
			int exit =0;
			while(exit == 0) {
			System.out.println("Would you like to buy this Utility? Enter y/n");
			String ans = input.next();
			switch(ans) {
			case "y":
				this.changeOwner(whoseturn);
				exit = 1;
				break;
			case "n":
				System.out.println("Go to auction");
				exit = 1;
				break;
			default:
				System.out.println("not a valid input!");
			}
			}
			
			
		}
		else {
			System.out.println("owned by another player!!");
			Player payPlayer;
			payPlayer = players.get(this.getOwnerIndex());
			// prompt to roll dice!!
			int payAmount = player.rollDice()*4;
			System.out.println("You must pay the owner "+ payAmount);
			player.changeAccountBalance(-payAmount);
			payPlayer.changeAccountBalance(+payAmount);
			System.out.println(player.getAccountBalance());
			System.out.println(payPlayer.getAccountBalance());
		}
	}
			
	public void readDetails() {
		System.out.println("This is a Utility called "+ this.getName() +" at board index " +this.getBoardIndex());
		System.out.println("cost = "+ this.getCost()+ "ownerIndex = " + this.getOwnerIndex());
	}
	
}
