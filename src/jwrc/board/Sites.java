package jwrc.board;
import java.util.ArrayList;
import java.util.Scanner;

import jwrc.game.Auction;
import jwrc.player.Player;

public class Sites extends Property {
	
	private Scanner input = new Scanner(System.in);
	
	private String colour;
	private int noOfHouses;
	private int houseCost;
	public boolean hotelRights;
	private int[] rentValues;
	private int rentIndex;
	

	public Sites(String name, int cost, int index, String colour, int[] rentValues) {
		
		super(name, cost, index);
		this.colour = colour;
		this.noOfHouses = 0;
		this.hotelRights = false;
		this.rentValues = rentValues;
		this.rentIndex = 0;
	}
	
	
	/*
	* Define what actions take place when the player lands on any property.
	* e.g. offer them to buy it at this.housePrice, or give option to pass on to an auction
	* */
	public void takeAction(Player player, ArrayList <Player> players) {
			
		if (player.getName() == this.getOwner()) {
			System.out.println("You own this Site.");
		}
		else if(this.getOwner() == "null") {
			
			int exit =0;
			while(exit == 0) {
			System.out.println("Would you like to buy this Site? Enter y/n. Cost = "+ this.getCost());
			String ans = input.next();
			switch(ans) {
				case "y":
					this.changeOwner(player.getName());
					player.changeAccountBalance(this.getCost());
					System.out.println(player.getName() + " your new balance is: "+ player.getAccountBalance());
					exit = 1;
					break;
				case "n":
					System.out.println("Go to auction");
					Auction.startAuction(players, this, input);
					exit = 1;
					break;
				default:
					System.out.println("not a valid input!");
				}
			}
		}
		
		else {
			Player payPlayer = new Player("null");
			int payAmount = 0;
			for(Player p : players) {
				if(p.getName() == this.getOwner()) {
					payPlayer = p;
				}
			}
			System.out.println("Owned by "+ payPlayer.getName());
			payAmount = this.rentValues[this.rentIndex];
			System.out.println("You must pay "+ payPlayer.getName() + " " +payAmount);
			player.changeAccountBalance(-payAmount);
			payPlayer.changeAccountBalance(+payAmount);
			System.out.println(player.getName()+" your new balance is "+player.getAccountBalance());
			System.out.println(payPlayer.getName()+ " your new balance is "+ payPlayer.getAccountBalance());
		}
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public void readDetails() {
		System.out.println("This is a Site called "+ this.getName() + "of colour "+ this.getColour());
	}
	
	public int getHouseCost() {
		return this.houseCost;
	}
	public int getRentCost() {
		return this.rentValues[this.rentIndex];
	}
	
	public int getNoOfHouses() {
		return this.noOfHouses;
	}
	public void addHouse() {
		this.noOfHouses++;
		this.rentIndex++;
	}
	
}
