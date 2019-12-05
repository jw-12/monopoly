package jwrc.board;
import java.util.ArrayList;

import jwrc.game.PropertyOverlord;
import jwrc.game.Trade;
import jwrc.game.Game;
import jwrc.player.PaymentType;
import jwrc.player.Player;

public class Sites extends Property {

	private String colour;
	public int noOfHouses;
	private int houseCost;
	public boolean hasHotel;
	private int[] rentValues;
	public int rentIndex;
	public boolean fullSet;
	

	public Sites(String name, int mortgageValue, int index, String colour, int[] rentValues, int houseCost) {
		
		super(name, mortgageValue, index);
		this.colour = colour;
		this.noOfHouses = 0;
		this.hasHotel = false;
		this.rentValues = rentValues;
		this.rentIndex = 0;
		this.houseCost = houseCost;
		this.fullSet = false;
	}
	
	/*
	* Define what actions take place when the player lands on any property.
	* e.g. offer them to buy it at this.housePrice, or give option to pass on to an auction
	* */
	public void takeAction(Player player, ArrayList <Player> players) {
		
		if(this.mortgageActive) {
			System.out.println(this.getName() + " is currently mortgaged so no action is taken");
			return;
		}
			
		if (player.getName().equals(this.getOwner())) {
			System.out.println("You own this Site.");
			return;
		}
		else if(this.getOwner() == null) {
			
			boolean exit = false;
			while(!exit) {
			System.out.println("Would you like to buy this Site? Enter y/n. Cost = $"+ this.getCost());
			String ans = Game.scanner.next();
			switch(ans) {
				case "y":
					this.buySite(player,this.getCost());
					exit = true;
					break;
				case "n":
					System.out.println("Go to auction");
					Trade.startAuction(players, this);
					exit = true;
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
				if(p.getName() == (this.getOwner())) {
					payPlayer = p;
				}
			}
			System.out.println("Owned by "+ payPlayer.getName());
			payAmount = this.rentValues[this.rentIndex];
			System.out.println("You must pay "+ payPlayer.getName() + " " +payAmount);
            payPlayer.payToPlayer(player, payAmount);
			System.out.println(player.getName()+" your new balance is "+player.getAccountBalance());
			System.out.println(payPlayer.getName()+ " your new balance is "+ payPlayer.getAccountBalance());
		}
	}
	
	public String getColour() {
		return this.colour;
	}
	public void buySite(Player player, int cost) {
		
		this.changeOwner(player.getName());
		player.changeAccountBalance(-cost, PaymentType.BANK);
		System.out.println(player.getName() + " your new balance is: $"+ player.getAccountBalance());
		player.addProperty(this);
		this.colourGroupCheck();
	}
	
	public void colourGroupCheck() {
		String siteKey = this.getColour();
		ArrayList<Sites> temp = new ArrayList<Sites>();
		temp = Board.map.get(siteKey);
		
		for(int i=0 ; i<temp.size(); i++) {
			if(this.getOwner() != temp.get(i).getOwner()) {
				return;
			}
		}
		for(int i=0 ; i<temp.size(); i++) {
			temp.get(i).rentIndex++;
			temp.get(i).fullSet = true;
		}
		System.out.println("You now own all Sites of colour "+ this.getColour()+". Rent has increased and you can now build Houses");
		
	}
	
	public void readDetails() {
		System.out.println("Site: "+ this.getName() + "  Colour: "+ this.getColour()+ "  Houses: "+this.getNoOfHouses()+"  Owner: "+this.getOwner());
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
	public void removeHouse() {
		this.noOfHouses--;
		this.rentIndex--;
	}
	
	public static int liquidateBuildings(Player player) {
		int value=0;
		ArrayList<Sites> sites = player.getSites();
		for(Sites s : sites) {
			if(s.hasHotel) {
				value+= 5*(s.getHouseCost()/2);
				s.hasHotel=false;
			}
			else {
				value+= s.getNoOfHouses()*(s.getHouseCost()/2);
				PropertyOverlord.numOfHouses+= s.getNoOfHouses();
			}
			s.noOfHouses = 0;
			s.rentIndex = 1;
		}
		return value;
	}
}
