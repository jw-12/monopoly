package jwrc.board;

import java.util.ArrayList;

public class Board {

   // ArrayList<BoardSpace> boardSpaces;
	
	//ArrayList<BoardSpace> propertyBoard;  // just for testing 
	BoardSpace[] propertyBoard;
	ArrayList<BoardSpace> penaltyBoard;  // likewise, for testing -james
	ArrayList<BoardSpace> testBoard;
    
    public Board() {
    	//this.propertyBoard = new ArrayList<BoardSpace>();
    	this.propertyBoard = new BoardSpace[5];
    	this.testBoard = new ArrayList<BoardSpace>();
    	setTestBoard();
    }
    
    public void setTestBoard() {
    	
    	BoardSpace b0 = new Utility("waterWorks", 200, 0);
    	BoardSpace b1 = new Utility("Electricity", 400, 1);
    	BoardSpace b2 = new Utility("waterWorks1", 200, 2);
    	BoardSpace b3 = new Utility("waterWorks2", 200, 3);
    	BoardSpace b4 = new Utility("waterWorks3", 200, 4);
    	
    	
    	this.testBoard.add(b0);
    	this.testBoard.add(b1);
    	this.testBoard.add(b2);
    	this.testBoard.add(b3);
    	this.testBoard.add(b4);
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
    
    public ArrayList<BoardSpace> getTestBoard() {
    	return this.testBoard;
	}
    


    public void setBoard() {
    	propertyBoard[0] = new Utility("Water works",60,1);
    	((Utility)propertyBoard[0]).readDetails();
    	
    	propertyBoard[1] = new Utility("Electric Company",60,1);
    	((Utility)propertyBoard[1]).readDetails();
    
    	propertyBoard[2] = new Sites("Oriental Avenue",100,6,"LightBlue");
    	propertyBoard[3] = new Sites("Vermont Avenue",100,8,"LightBlue");
    	propertyBoard[4] = new Sites("Connecticut Avenue",120,9,"LightBlue");
    	
    }

    // ArrayList<BoardSpace> communityChestCards;
    //<BoardSpace> currentCommunityChest =
}