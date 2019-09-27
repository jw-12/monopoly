package jwrc.board;

import java.util.ArrayList;

public class Board {

   // ArrayList<BoardSpace> boardSpaces;

	ArrayList<BoardSpace> propertyBoard;  // just for testing

    public Board() {
    	this.propertyBoard = new ArrayList<BoardSpace>();
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