package jwrc.board;
import java.util.ArrayList;

import jwrc.player.Player;

public class TransportSpaces extends Property {
	
	public TransportSpaces(String name, int cost, int index) {
		super(name,cost,index);
	}
	
	public void takeAction(Player player, ArrayList <Player> players, int whoseturn) {
		
		if (whoseturn == this.getOwnerIndex()) {
			System.out.println("You own this Transport.");
		}
		else if(this.getOwnerIndex() == 99) {
			System.out.println("Would you like to buy this Transport");
		// if yes player.transportsOwned ++; else auction it 
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
