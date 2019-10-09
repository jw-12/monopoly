package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;

import jwrc.game.*;

public abstract class Property extends BoardSpace {
	
	private String name;
	private int cost;
	private int ownerIndex;
	private String owner;
	
	public Property(String name, int cost, int index) {
		super(index);
		this.name = name;
		this.cost = cost;
		this.owner = "null";
	}

	public abstract void takeAction(Player player, ArrayList <Player> players);
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
	
	public void changeOwner(String owner) {
		this.owner = owner;
	}
	
	
	public String getOwner() {
		return this.owner;
	}
	
	

	//public SpaceType getSpaceType() {
	//	return this.spaceType;
	//}

}
