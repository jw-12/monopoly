package jwrc.board;

import java.util.ArrayList;

public class Board {

   // ArrayList<BoardSpace> boardSpaces;

	ArrayList<BoardSpace> propertyBoard;  // just for testing
	ArrayList<BoardSpace> penaltyBoard;  // likewise, for testing -james

    public Board() {
    	this.propertyBoard = new ArrayList<BoardSpace>();
    	this.penaltyBoard = new ArrayList<BoardSpace>();
    	setPenaltyBoard();
    }

    // just a simple board to test functionality of penalty components.
    public void setPenaltyBoard() {
    	BoardSpace b0 = new Penalties(0, 100, "Income Tax");
    	BoardSpace b1 = new Penalties(1, 100, "Income Tax");
    	BoardSpace b2 = new Penalties(2, 100, "Income Tax");
    	BoardSpace b3 = new Penalties(3, 100, "Income Tax");
    	BoardSpace b4 = new Penalties(4, 100, "Income Tax");
    	BoardSpace b5 = new Penalties(5, 100, "Income Tax");
    	BoardSpace b6 = new Penalties(6, 100, "Speeding Fine");
    	BoardSpace b7 = new Penalties(7, 100, "Speeding Fine");
    	BoardSpace b8 = new Penalties(8, 100, "Speeding Fine");
    	BoardSpace b9 = new Penalties(9, 100, "Speeding Fine");
    	BoardSpace b10 = new Penalties(10, 100, "Speeding Fine");

    	this.penaltyBoard.add(b0);
    	this.penaltyBoard.add(b1);
    	this.penaltyBoard.add(b2);
    	this.penaltyBoard.add(b3);
    	this.penaltyBoard.add(b4);
    	this.penaltyBoard.add(b5);
    	this.penaltyBoard.add(b6);
    	this.penaltyBoard.add(b7);
    	this.penaltyBoard.add(b8);
    	this.penaltyBoard.add(b9);
    	this.penaltyBoard.add(b10);
    }

    public ArrayList<BoardSpace> getPenaltyBoard() {
    	return this.penaltyBoard;
	}


    public void setBoard() { // will think of more efficient way to create board.
    	BoardSpace bs1 = new Property("Mediterranean Avenue",60 ,"Brown",1);
    	propertyBoard.add(bs1);
    	BoardSpace bs2 = new Property("Baltic Avenue",60,"Brown",3);
    	propertyBoard.add(bs2);
    	BoardSpace bs3 = new Property("Oriental Avenue",100,"LightBlue",6);
    	propertyBoard.add(bs3);
    	BoardSpace bs4 = new Property("Vermont Avenue",100,"LightBlue",8);
    	propertyBoard.add(bs4);
    	BoardSpace bs5 = new Property("Connecticut Avenue",120,"LightBlue",9);
    	propertyBoard.add(bs5);

    	//test
    	for(int i=0; i<5; i++) {
    		System.out.println(((Property)propertyBoard.get(i)).getName() +" is at board index"+ ((Property)propertyBoard.get(i)).getBoardIndex());
    	}

    }





    // ArrayList<BoardSpace> communityChestCards;
    //<BoardSpace> currentCommunityChest =







}