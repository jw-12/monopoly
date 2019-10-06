package jwrc.board;
import java.util.ArrayList;
import java.util.Scanner;

import jwrc.player.Player;

public class TransportSpaces extends Property {
	
	private Scanner input = new Scanner(System.in);
	
	public TransportSpaces(String name, int cost, int index) {
		super(name,cost,index);
	}
	
	public void takeAction(Player player, ArrayList <Player> players, int whoseturn) {
		
		if (whoseturn == this.getOwnerIndex()) {
			System.out.println("You own this Transport.");
		}
		else if(this.getOwnerIndex() == 99) {
			int exit =0;
			while(exit == 0) {
			System.out.println("Would you like to buy this Transport? Enter y/n");
			String ans = input.next();
			switch(ans) {
			case "y":
				this.changeOwner(whoseturn);
				player.changeAccountBalance(this.getCost());
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
			Player payPlayer;
			int payAmount = 0;
			payPlayer = players.get(this.getOwnerIndex());
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
			player.changeAccountBalance(-payAmount);
			payPlayer.changeAccountBalance(+payAmount);
		}
	}
	
	public void readDetails() {
		System.out.println("This is a Transport Space called "+ this.getName());
	}
	
}
