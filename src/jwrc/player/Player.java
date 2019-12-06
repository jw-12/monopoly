package jwrc.player;
import java.util.ArrayList;
import jwrc.board.Property;
import jwrc.board.Sites;
import jwrc.game.Game;
import jwrc.game.Trade;
import jwrc.menus.BrokeMenu;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * An instance of this class is create dfor each player in the game. It contains information such as their 
 * account balance, what properties the own and their jail status.
 *
 */
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
    public boolean hasRolled;

    /**
     * This constructs a player with specified name and sets their default parameters.
     * @param name The name of the player.
     */
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
        this.hasRolled = false;
    }

    /**
     * rolls the dice, generating 2 random number from 1 to 6.
     */
    public void rollDice() {
        this.diceVal = new int[] {ThreadLocalRandom.current().nextInt(1, 7), ThreadLocalRandom.current().nextInt(1, 7)};  //must be max+1
    }

    /**
     * This class sets the new board index of a player given their dice roll. If they pass index 40 their position resets to 0.
     * @param inputDiceVal The dice value the player has rolled
     */
    public void evaluatePosition(int inputDiceVal) {
        this.boardIndex += inputDiceVal;
        
        // passing 'GO'
        if(this.boardIndex >= 40) {
            this.boardIndex = this.boardIndex % 40;
            changeAccountBalance(+200, PaymentType.BANK);  // and add 200$ to account
        }
    }

    /**
     * 
     * @return Returns the name of the player
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return Returns the board index of the player.
     */
    public int getBoardIndex() {
        return boardIndex;
    }

    /**
     * Set  a new board index for a player.
     * @param boardIndex The new board index of the player.
     */
    public void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
    }

    /**
     * 
     * @return Returns the account balance of a player.
     */
    public int getAccountBalance() {
        return this.accountBalance;
    }

    /**
     *  changes a players account balance depending on the value of delta.
     * @param delta The change in account balance.
     * @param type The type of transaction.(player to player or player to bank)
     */
    public void changeAccountBalance(int delta, PaymentType type) {  // delta +ve for gains or -ve for fines etc.

        if (-delta > this.accountBalance && type == PaymentType.BANK) {
            BrokeMenu.options(this, Game.playerList, -delta);
        }

        this.accountBalance += delta;
    }

    /**
     * pays a specified amount to this player from another player given as an input argument.
     * @param payer The player paying the sum.
     * @param amount The amount to pay.
     */
    public void payToPlayer(Player payer, int amount) {

        if (amount > payer.getAccountBalance()) {
            //sell all houses, hotels etc. Then transfer ownership to 'this' player
            // Then kick payer from game


            int houseVal = Sites.liquidateBuildings(payer);

            while (payer.getPropertiesOwned().size() > 0) {
                Trade.safeTrade(payer, this, payer.getPropertiesOwned().get(0));
            }

            this.changeAccountBalance(houseVal, PaymentType.BANK);

            Game.kickPlayerFromGame(payer);

        } else {
            payer.changeAccountBalance(-amount, PaymentType.PLAYER);
            this.changeAccountBalance(amount, PaymentType.PLAYER);
        }
    }

    /**
     * Prints the account balance and board index of a player .
     */
    public void printPlayerDetails() {
        System.out.println(
                "Balance: $" + this.getAccountBalance() +
                "\nBoard Position: " + this.getBoardIndex() + "/40"
        );
    }

    /**
     * @return Returns how many doubles a player has rolled.
     */
    public int getDoubles() {
        return this.doubles;
    }

    /**
     * @param doubles set the number of doubles a player has rolled.
     */
    public void setDoubles(int doubles) {
        this.doubles = doubles;
    }

    /**
     * 
     * @return Returns the number of turns a player has taken in jail
     */
    public int getTurnsInJail() {
        return this.turnsInJail;
    }

    /**
     * 
     * @param turnsInJail sets the number of turns a player has taken in jail
     */
    public void setTurnsInJail(int turnsInJail) {
        this.turnsInJail = turnsInJail;
    }

    /**
     * @return return true if player is in jail
     */
    public boolean getJailStatus() {
        return this.jailStatus;
    }

    /**
     * Inverts current jail status.
     */
    public void changeJailStatus() {
        this.jailStatus = !this.jailStatus;
    }

    /**
     * Sends a player to jail, setting their board index to 10 and their jail status to true.
     */
    public void sendToJail() {
        this.boardIndex = 10;
        this.setDoubles(0);
        this.hasRolled = true;
        this.changeJailStatus();
    }

    /**
     * 
     * @return Returns the number of GOOJF cards a player has.
     */
    public int getGetOutOfJailFreeCard() {
        return this.getOutOfJailFreeCard;
    }

    /**
     * 
     * @param number sets the number of GOOJF cards a player has.
     */
    public void setGetOutOfJailFreeCard(int number) {
        this.getOutOfJailFreeCard = number;
    }
    /**
     * Adds a property to a players arraylist of properties.
     * @param p The property to be added to a players list 
     */
    public void addProperty(Property p){
        this.propertiesOwned.add(p);
    }

    /**
     * removes a property to a players arraylist of properties.
     * @param p Property to be removed from players list.
     */
    public void removeProperty(Property p){
        this.propertiesOwned.remove(p);
    }

    /**
     * Read the sites a player owns.
     */
    public void readSites() {
    	for(Property s : this.propertiesOwned) {
    	    if (s instanceof Sites) {
                System.out.println(s.getName() + ": " + s.getBoardIndex()+ " (Number of houses : "+ ((Sites)s).noOfHouses+") ( hasHotel = "+((Sites)s).hasHotel+")");
            }
    	}
    }
    /**
     * Read the properties a player owns.
     */
    public void readProperties() {
    	for(Property p : this.propertiesOwned) {
    		System.out.println(p.getName() + ": " + p.getBoardIndex()+"(Mortgage value = $"+ p.getmortgageValue()+")");
    	}
    }
    
    /**
     * 
     * @return Returns an  arraylist of the properties owned by a player.
     */
    public ArrayList<Property> getPropertiesOwned() {
        return this.propertiesOwned;
    }

    /**
     * 
     * @return Returns an arraylist of the sites owned by a player.
     */
    public ArrayList<Sites> getSites() {
        ArrayList<Sites> sitesArrayList = new ArrayList<>();

        for(Property p : this.propertiesOwned) {
            if (p instanceof Sites)
                sitesArrayList.add((Sites)p);
        }
        return sitesArrayList;
    }
    
    /**
     * 
     * @return Returns the number of transports owned by a player.
     */
    public int getTransportsOwned() {
    	return this.transportsOwned;
    }
    /**
     * Changes the number of transports a player owns.
     * @param delta The number of transports to increase/decrease by.
     */
    public void changeTransportsOwned(int delta) {
    	this.transportsOwned += delta;
    }
    /**
     * 
     * @return Returns the number utilities a player owns.
     */
    public int getUtilitiesOwned() {
    	return this.utilitiesOwned;
    }
    /**
     * Changes the number of utilities owned by a player.
     * @param delta The number of utilities to increase/decrease by.
     */
    public void changeUtilitiesOwned(int delta) {
    	this.utilitiesOwned += delta;
    }
}
