package jwrc.board;
import java.util.ArrayList;

import jwrc.player.Player;

public class Utility extends Property {
	
	public Utility(String name, int cost, int index) {
		super(name,cost,index);
	}
	
	public void takeAction(Player player, ArrayList <Player> players, int whoseturn) {
		
		if (whoseturn == this.getOwnerIndex()) {
				System.out.println("You own this Property.");
		}
		else if(this.getOwnerIndex() == 99) {
			System.out.println("Would you like to buy this Utility");
			// if not go to auction. 
		}
		else {
			Player payPlayer;
			payPlayer = players.get(this.getOwnerIndex());
			// prompt to roll dice!!
			int payAmount = player.rollDice()*4;
			player.changeAccountBalance(-payAmount);
			payPlayer.changeAccountBalance(+payAmount);
		
		}
	}
			
	
	
	public void readDetails() {
		System.out.println("This is a Utility called "+ this.getName());
	}

}
