package jwrc.board;

import java.util.ArrayList;

public class Board {

   // ArrayList<BoardSpace> boardSpaces;
	
	//ArrayList<BoardSpace> propertyBoard;  // just for testing 
	BoardSpace[] propertyBoard;
    
    public Board() {
    	//this.propertyBoard = new ArrayList<BoardSpace>();
    	this.propertyBoard = new BoardSpace[5];
    }
    
    
    public void setBoard() { // will think of more efficient way to create board.
    	propertyBoard[0] = new Property("Mediterranean Avenue",60 ,"Brown",1);
    	
    	propertyBoard[1] = new Property("Baltic Avenue",60,"Brown",3);
    
    	propertyBoard[2] = new Property("Oriental Avenue",100,"LightBlue",6);
    
    	propertyBoard[3] = new Property("Vermont Avenue",100,"LightBlue",8);
    
    	propertyBoard[4] = new Property("Connecticut Avenue",120,"LightBlue",9);
    
    	
    	//test
    //	for(int i=0; i<5; i++) {
    //		System.out.println(((Property)propertyBoard.get(i)).getName() +" is at board index "+ ((Property)propertyBoard.get(i)).getBoardIndex());
    //	}
    	
    	System.out.println(((Property)propertyBoard[0]).getName());
    	
    }
    
    
    
    

    // ArrayList<BoardSpace> communityChestCards;
    //<BoardSpace> currentCommunityChest =
    
    
    




}