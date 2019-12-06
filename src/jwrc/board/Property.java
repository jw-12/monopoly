package jwrc.board;

import jwrc.player.Player;

import java.util.ArrayList;

/**
 * This is a super class for the three types of property, Utilities, Sites and Transports.
 */
public abstract class Property extends BoardSpace implements Sellable {
	
	private String name;
	private int cost;
	private String owner;
	private int mortgageValue;
	public boolean mortgageActive;
	
	/**
	 * 
	 * @param name The name of the Property
	 * @param mortgageValue The mortgage value of the Property
	 * @param index The board index of the property
	 */
	public Property(String name, int mortgageValue, int index) {
		super(index);
		this.name = name;
		this.mortgageValue = mortgageValue;
		this.cost = mortgageValue*2;
		this.owner = null;
	}
	
	/**
	 * @param player The player taking action on a property space.
	 * @param players The list of players in the game.
	 */
	public abstract void takeAction(Player player, ArrayList <Player> players);
	/**
	 * read details of the specific property space.
	 */
	public abstract void readDetails();

	/**
	 * 
	 * @return Returns the name of the property.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 
	 * @return Returns the mortgage value of the property.
	 */
	public int getmortgageValue() {
		return this.mortgageValue;
	}
	/**
	 * @return Returns the cost of buying the property.
	 */
	public int getCost() {
		return this.cost;
	}
	/**
	 * Used to change the owner of a property. Takes the new owner as an input parameter.
	 * @param owner The new owner of the property.
	 */
	public void changeOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * 
	 * @return Returns the owner of the property.
	 */
			
	public String getOwner() {
		return this.owner;
	}
}
