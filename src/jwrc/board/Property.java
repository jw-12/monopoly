package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;

import jwrc.game.*;

public abstract class Property extends BoardSpace {
	
	private String name;
	private int cost;
	private int ownerIndex;
	//private String colour;
	//private int noOfHouses;
	//public boolean hotelRights;
	//public int housePrice;
	
	public Property(String name, int cost, int index) {
		super(index);
		this.name = name;
		this.cost = cost;
	//	this.colour = colour;
		this.ownerIndex = 99;
	//	this.noOfHouses =0;
	//	this.hotelRights = false;
	}

	//public void takeAction(Player player) {
		/*
		* Define what actions take place when the player lands on any property.
		* e.g. offer them to buy it at this.housePrice, or give option to pass on to an auction
		* */
	//}
	public abstract void takeAction(Player player, ArrayList <Player> players, int whoseturn);
	public abstract void readDetails();

	public String getName() {
		return this.name;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public int getOwnerIndex() {
		return this.ownerIndex;
	}
	
	
	//public void readDetails() {
	//	System.out.println("You have landed on " + this.name);
	//}

	//public SpaceType getSpaceType() {
	//	return this.spaceType;
	//}

}
