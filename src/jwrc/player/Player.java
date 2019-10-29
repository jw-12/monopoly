package jwrc.player;
import java.util.ArrayList;
import jwrc.board.Property;
import jwrc.board.Sites;

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
    public ArrayList<Sites> sitesOwned;

    public Player(String name) {
        this.name = name;
        this.accountBalance = 1500;
        this.jailStatus = false;
        this.doubles = 0;
        this.turnsInJail = 0;
        this.transportsOwned = 0;
        this.utilitiesOwned = 0;
        this.getOutOfJailFreeCard = 0;
        this.sitesOwned = new ArrayList<Sites>();
    }

    public int [] rollDice() {
        return new int[] {ThreadLocalRandom.current().nextInt(1, 7), ThreadLocalRandom.current().nextInt(1, 7)};  //must be max+1
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
        this.accountBalance += delta;
    }

    public void printPlayerDetails() {
        System.out.println(
                "Balance: €" + this.getAccountBalance() +
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
    
    public void addSite(Sites s){
        this.sitesOwned.add(s);
    }
    
    public void removeSite(Sites s){
        int index = sitesOwned.indexOf(s);
        sitesOwned.remove(index);
    }
    
    public void readSites() {
    	for(Sites s : this.sitesOwned) {
    		System.out.println(s.getName() + ":" + s.getBoardIndex()+ "(Number of houses : "+ s.noOfHouses+") ( hasHotel = "+s.hasHotel+")");
    	}
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
