package jwrc.board;

import jwrc.player.Player;

public class Property extends BoardSpace {
	
	private String name;
	private int cost;
	private String owner;
	private String colour;
	private int noOfHouses;
	public boolean hotelRights;
	public int housePrice;
	
	public Property(String name, int cost, String colour, int index) {
		super(index, SpaceType.PROPERTY);
		this.name = name;
		this.cost = cost;
		this.colour = colour;
		this.owner = "";
		this.noOfHouses =0;
		this.hotelRights = false;
	}

	public void takeAction(Player player) {
		/*
		* Define what actions take place when the player lands on any property.
		* e.g. offer them to buy it at this.housePrice, or give option to pass on to an auction
		* */
	}

	public String getName() {
		return this.name;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public int getNoOfHouses() {
		return this.noOfHouses;
	}
	
	public boolean getHotelRights() {
		return this.hotelRights;
	}
	
	public void readDetails() {
		System.out.println("You have landed on " + this.name);
	}

}
