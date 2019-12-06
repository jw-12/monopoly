package test;

import jwrc.board.Board;
import jwrc.board.BoardSpace;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    static Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @After
    public void tearDown() {
        board = null;
    }

    @Test
    public final void test_Board_checkIndices() {
        BoardSpace b = Board.spaces.get(30);
        assertEquals("Ensure GoToJail in correct index", 30, b.getBoardIndex());
        b = Board.spaces.get(10);
        assertEquals("Ensure Jail in correct index", 10, b.getBoardIndex());
    }
}
