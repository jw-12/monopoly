package jwrc.board;
import java.util.ArrayList;

import jwrc.player.Player;

public class Sites extends Property {
	
	private String colour;
	private int noOfHouses;
	public boolean hotelRights;
	

	public Sites(String name, int cost, int index, String colour) {
		
		super(name, cost, index);
		this.colour = colour;
		this.noOfHouses = 0;
		this.hotelRights = false;
	}
	
	
	/*
	* Define what actions take place when the player lands on any property.
	* e.g. offer them to buy it at this.housePrice, or give option to pass on to an auction
	* */
	public void takeAction(Player player, ArrayList <Player> players, int whoseturn) {
			System.out.println("nothing for now");
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public void readDetails() {
		System.out.println("This is a Site called "+ this.getName() + "of colour "+ this.getColour());
	}
	
	
}
