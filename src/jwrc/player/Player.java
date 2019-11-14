package jwrc.player;
import java.util.ArrayList;
import jwrc.board.Property;
import jwrc.board.Sites;
import jwrc.game.Game;
import jwrc.menus.BrokeMenu;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private String name;
    private int boardIndex;
    private int accountBalance;
    private boolean jailStatus;  //true if in jail
    private int doubles;  //how many doubles a player has rolled in successive turns
    private int turnsInJail;
    private int transportsOwned;//will need to decrement this value when a transport is sold/mortgaged
    private int utilitiesOwned;
    private int getOutOfJailFreeCard;  // number of GOOJF cards owned
    private ArrayList<Property> propertiesOwned;
    public boolean isKicked;
    public int[] diceVal; //array of two dice values

    public Player(String name) {
        this.name = name;
        this.accountBalance = 1500;
        this.jailStatus = false;
        this.doubles = 0;
        this.turnsInJail = 0;
        this.transportsOwned = 0;
        this.utilitiesOwned = 0;
        this.getOutOfJailFreeCard = 0;
        this.propertiesOwned = new ArrayList<>();
        this.isKicked = false;
        this.diceVal = null;
    }

    public void rollDice() {
        this.diceVal = new int[] {ThreadLocalRandom.current().nextInt(1, 7), ThreadLocalRandom.current().nextInt(1, 7)};  //must be max+1
    }

    public void evaluatePosition(int diceVal) {
        this.boardIndex += diceVal;

        // passing 'GO'
        if(this.boardIndex >= 40) {
            this.boardIndex = this.boardIndex % 40;
            changeAccountBalance(+200);  // and add 200$ to account
        }
    }

    public String getName() {
        return name;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
    }

    public int getAccountBalance() {
        return this.accountBalance;
    }

    public void changeAccountBalance(int delta) {  // delta +ve for gains or -ve for fines etc.

        if (-delta > this.accountBalance) {
            BrokeMenu.options(this, Game.playerList, -delta);
        }

        this.accountBalance += delta;
    }

    public void printPlayerDetails() {
        System.out.println(
                "Balance: $" + this.getAccountBalance() +
                "\nBoard Position: " + this.getBoardIndex() + "/40"
        );
    }

    public int getDoubles() {
        return this.doubles;
    }

    public void setDoubles(int doubles) {
        this.doubles = doubles;
    }

    public int getTurnsInJail() {
        return this.turnsInJail;
    }

    public void setTurnsInJail(int turnsInJail) {
        this.turnsInJail = turnsInJail;
    }

    //return true if player is in jail
    public boolean getJailStatus() {
        return this.jailStatus;
    }

    //inverts current jail status
    public void changeJailStatus() {
        this.jailStatus = !this.jailStatus;
    }

    public void sendToJail() {
        this.boardIndex = 10;  // TODO: change this to some sort of macro
        this.changeJailStatus();
    }

    public int getGetOutOfJailFreeCard() {
        return this.getOutOfJailFreeCard;
    }

    public void setGetOutOfJailFreeCard(int number) {
        this.getOutOfJailFreeCard = number;
    }
    
    public void addProperty(Property p){
        this.propertiesOwned.add(p);
    }

    public void removeProperty(Property p){
        this.propertiesOwned.remove(p);
    }

    
    public void readSites() {
    	for(Property s : this.propertiesOwned) {
    	    if (s instanceof Sites) {
                System.out.println(s.getName() + ": " + s.getBoardIndex()+ " (Number of houses : "+ ((Sites)s).noOfHouses+") ( hasHotel = "+((Sites)s).hasHotel+")");
            }
    	}
    }
    public void readProperties() {
    	for(Property p : this.propertiesOwned) {
    		System.out.println(p.getName() + ": " + p.getBoardIndex()+"(Mortgage value = $"+ p.getmortgageValue()+")");
    	}
    }
    
    public ArrayList<Property> getPropertiesOwned() {
        return this.propertiesOwned;
    }

    public ArrayList<Sites> getSites() {
        ArrayList<Sites> sitesArrayList = new ArrayList<>();

        for(Property p : this.propertiesOwned) {
            if (p instanceof Sites)
                sitesArrayList.add((Sites)p);
        }
        return sitesArrayList;
    }
    
    public int getTransportsOwned() {
    	return this.transportsOwned;
    }
    public void changeTransportsOwned(int delta) {
    	this.transportsOwned += delta;
    }
    
    public int getUtilitiesOwned() {
    	return this.utilitiesOwned;
    }
    public void changeUtilitiesOwned(int delta) {
    	this.utilitiesOwned += delta;
    }
    
}
