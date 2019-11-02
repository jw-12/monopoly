package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;

import jwrc.game.*;

public abstract class Property extends BoardSpace implements Sellable {
	
	private String name;
	private int cost;
	private String owner;
	private int mortgageValue;
	public boolean mortgageActive;
	
	public Property(String name, int mortgageValue, int index) {
		super(index);
		this.name = name;
		this.mortgageValue = mortgageValue;
		this.cost = mortgageValue*2;
		this.owner = "null";
	}

	public abstract void takeAction(Player player, ArrayList <Player> players);
	public abstract void readDetails();

	public String getName() {
		return this.name;
	}
	public int getmortgageValue() {
		return this.mortgageValue;
	}
	
	public void mortgageProperty() {
		
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public void changeOwner(String owner) {
		this.owner = owner;
	}
	
	public String getOwner() {
		return this.owner;
	}

}
