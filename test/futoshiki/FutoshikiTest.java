/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bdtw20
 */
public class FutoshikiTest {

    private Futoshiki game;

    @Before
    public void setUp() {
        game = new Futoshiki(5);
    }

    @Test
    public void testSetSquare() {
        System.out.println("testSetSquare()");
        game.setSquare(4, 0, 0);
        assertEquals(4, game.getGrid()[0][0].getNumber());
    }

    @Test
    public void testSetRowConstraint() {
        System.out.println("testSetRowConstraint()");
        game.setRowConstraints(0, 0);
        try {
            game.setRowConstraints(5, 3);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Invalid number input for rows. Put number between 0 and 3", e.getMessage());
        }
        assertEquals(null, game.getRowConstraints()[0][0]);
    }

    @Test
    public void testSetColConstraint() {
        System.out.println("testSetColConstraint()");
        game.setColConstraints(2, 2);
        try {
            
            game.setRowConstraints(1, 5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            assertEquals("Invalid number input for column. Put number between 0 and 4", e.getMessage());
        }
        assertEquals(null, game.getColConstraints()[2][2]);
    }

    @Test
    public void testFillPuzzle() {
        System.out.println("testFillPuzzle()");
        int rowConFill = 0;
        int boxFill = 0;
        int colConFill = 0;
        game.fillPuzzle(3, 3, 3);
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                if (game.getGrid()[i][j] != null) {
                    boxFill++;
                }
            }
        }
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize() - 1; j++) {
                if (game.getRowConstraints()[j][i] != null) {
                    rowConFill++;
                }
                if (game.getColConstraints()[i][j] != null) {
                    colConFill++;
                }
            }
        }
        System.out.println(game.displayString());
        assertEquals(3, boxFill);
        assertEquals(3, rowConFill);
        assertEquals(3, colConFill);

    }

    @Test
    public void testDisplayString() {
        System.out.println("testDisplayString()");
        Futoshiki newGame = new Futoshiki(10);
        String testArrayForCharactersInDisplayString = newGame.displayString();
        assertFalse(newGame.displayString() == null);
        System.out.println(newGame.displayString());
        assertEquals(1980, testArrayForCharactersInDisplayString.toCharArray().length);
        newGame.setColConstraints(0, 3);
        newGame.setColConstraints(1, 3);
        newGame.setSquare(1, 0, 8);
        assertEquals(newGame.getColConstraints()[0][3], null);
        assertEquals(newGame.getColConstraints()[1][3], null);
        assertEquals(newGame.getGrid()[0][8].getNumber(), 1);

    }

    @Test
    public void testDifferentSizeGame() {
        System.out.println("testDifferentSizeGame()");
        Futoshiki newGame = new Futoshiki(10);
        String testArrayForCharactersInDisplayString = newGame.displayString();
        assertFalse(newGame.displayString() == null);
        System.out.println(newGame.displayString());
        assertEquals(1980, testArrayForCharactersInDisplayString.toCharArray().length);

    }

    @Test
    public void failedBuildOfGame() {
        System.out.println("failedBuildOfGame()");
        String errorMessage = "Invalid build, put in a number which is greater than 4.";
        try {
            Futoshiki game = new Futoshiki(2);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(errorMessage, e.getMessage());
        }

    }
    
    @Test
    public void setRowConstraintWithFilledSqaure(){
        System.out.println("setRowConstraintWithFilledSqaure");
        Futoshiki game = new Futoshiki(3);
        game.setSquare(1, 0, 0);
        game.setSquare(2, 1, 0);
        game.setRowConstraints(0,0);
        assertEquals(game.getRowConstraints()[0][0].setConstraintsForGrid(),"<");
    }
    
    @Test
    public void setColConstraintWithFilledSqaure(){
        System.out.println("setColConstraintWithFilledSqaure");
        Futoshiki game = new Futoshiki(3);
        game.setSquare(1, 0, 0);
        game.setSquare(2, 0, 1);
        game.setColConstraints(0, 0);
        System.out.println(game.displayString());
        assertEquals(game.getColConstraints()[0][0].setConstraintsForGrid(),"^");
    }

    @Test
    public void testFillPuzzleIfThereIsAlreadyValues() {
        System.out.println("testFillPuzzleIfThereIsAlreadyValues()");
        Futoshiki game = new Futoshiki(5);
        game.setSquare(2, 1, 3);
        game.setColConstraints(1, 1);
        while (game.getGrid()[1][3] != null || game.getColConstraints()[1][1] != null) {
            game.fillPuzzle(3, 3, 3);
        }
        assertTrue(game.getGrid()[1][3] == null);
        assertTrue(game.getColConstraints()[1][1] == null);
    }

    @Test
    public void testisLegalForEmpty() {
        System.out.println("testisLegalForEmpty()");
        Futoshiki game = new Futoshiki(5);
        game.fillPuzzle(3, 3, 3);
        assertTrue(game.isLegal());
    }

    @Test
    public void testisLegalForFilled() {
        System.out.println("testisLegalForFilled()");
        Futoshiki game2 = new Futoshiki(3);
        game2.setSquare(1, 0, 0);
        game2.setSquare(2, 1, 0);
        game2.setSquare(3, 2, 0);
        game2.setSquare(1, 2, 1);
        game2.setSquare(2, 0, 1);
        game2.setSquare(3, 1, 1);
        game2.setSquare(1, 1, 2);
        game2.setSquare(2, 2, 2);
        game2.setSquare(3, 0, 2);
        System.out.println(game2.displayString());
        assertTrue(game2.isLegal());
    }

    @Test
    public void testisLegalForFilledWrong() {
        System.out.println("testisLegalForFilledWrong()");
        Futoshiki game2 = new Futoshiki(3);
        game2.setSquare(3, 0, 0);
        game2.setSquare(3, 1, 0);
        game2.setSquare(3, 2, 0);
        game2.setSquare(1, 2, 1);
        game2.setSquare(2, 0, 1);
        game2.setSquare(3, 1, 1);
        game2.setSquare(1, 1, 2);
        game2.setSquare(2, 2, 2);
        game2.setSquare(3, 0, 2);
        game2.getAnswer()[0][0] = new FutoshikiSquare(false, 1);
        game2.getAnswer()[1][0] = new FutoshikiSquare(false, 2);
        game2.getAnswer()[2][0] = new FutoshikiSquare(false, 3);
        game2.getAnswer()[0][1] = new FutoshikiSquare(false, 2);
        game2.getAnswer()[1][1] = new FutoshikiSquare(false, 3);
        game2.getAnswer()[2][1] = new FutoshikiSquare(false, 1);
        game2.getAnswer()[0][2] = new FutoshikiSquare(false, 3);
        game2.getAnswer()[1][2] = new FutoshikiSquare(false, 1);
        game2.getAnswer()[2][2] = new FutoshikiSquare(false, 2);
        System.out.println(game2.displayString());
        assertFalse(game2.isLegal());
    }

    @Test
    public void testisLegalForFilledWithRowCons() {
        System.out.println("testisLegalForFilledWithRowCons()");
        Futoshiki game2 = new Futoshiki(3);
        game2.setSquare(1, 0, 0);
        game2.setSquare(2, 1, 0);
        game2.setSquare(3, 2, 0);
        game2.setSquare(1, 2, 1);
        game2.setSquare(2, 0, 1);
        game2.setSquare(3, 1, 1);
        game2.setSquare(1, 1, 2);
        game2.setSquare(2, 2, 2);
        game2.setSquare(3, 0, 2);
        game2.setRowConstraints(1, 0);
        System.out.println(game2.displayString());
        assertTrue(game2.isLegal());
    }

    @Test
    public void testisLegalForFilledWithColCons() {
        System.out.println("testisLegalForFilledWithColCons()");
        Futoshiki game2 = new Futoshiki(3);
        game2.setSquare(1, 0, 0);
        game2.setSquare(2, 1, 0);
        game2.setSquare(3, 2, 0);
        game2.setSquare(1, 2, 1);
        game2.setSquare(2, 0, 1);
        game2.setSquare(3, 1, 1);
        game2.setSquare(1, 1, 2);
        game2.setSquare(2, 2, 2);
        game2.setSquare(3, 0, 2);
        game2.setColConstraints(1, 0);
        System.out.println(game2.displayString());
        assertTrue(game2.isLegal());
    }

    @Test
    public void testGetProblems() {
        System.out.println("testGetProblems()");
        Futoshiki game = new Futoshiki(3);
        game.getAnswer()[0][0] = new FutoshikiSquare(false, 1);
        game.getAnswer()[1][0] = new FutoshikiSquare(false, 2);
        game.getAnswer()[2][0] = new FutoshikiSquare(false, 3);
        game.getAnswer()[0][1] = new FutoshikiSquare(false, 2);
        game.getAnswer()[1][1] = new FutoshikiSquare(false, 3);
        game.getAnswer()[2][1] = new FutoshikiSquare(false, 1);
        game.getAnswer()[0][2] = new FutoshikiSquare(false, 3);
        game.getAnswer()[1][2] = new FutoshikiSquare(false, 1);
        game.getAnswer()[2][2] = new FutoshikiSquare(false, 2);
        game.setSquare(2, 1, 1);
        System.out.println(game.getProblems());
        String test = "" + "The following numbers have been repeated: 2. in row: 1. in column: 1.\n";
        assertEquals(test, game.getProblems());

    }
    
    @Test
    public void testEmptyGetProblems(){
        System.out.println("testEmptyGetProblems");
        Futoshiki game = new Futoshiki(3);
        game.fullFilledPuzzle();
        assertEquals("", game.getProblems());
    }

    @Test
    public void testNonEditNumbers() {
        System.out.println("testNonEdit()");
        Futoshiki game = new Futoshiki(30);
        game.fullFilledPuzzle();
        System.out.println(game.displayString());
        System.out.println(game.isLegal());
        assertTrue(game.isLegal());
    }

    @Test
    public void reTestOfFillPuzzel() {
        System.out.println("reTestOfFillPuzzel");
        Futoshiki game = new Futoshiki(5);
        game.fillPuzzle(10, 5, 5);
        System.out.println(game.displayString());
        assertTrue(game.isLegal());
    }
    
    @Test
    public void reTestOfFillPuzzleChangeOfSize(){
        System.out.println("reTestOfFillPuzzleChangeOfSize");
        Futoshiki game = new Futoshiki(16);
        game.fillPuzzle(16, 18, 18);
        assertTrue(game.isLegal());
    }

    @Test
    public void testIsPuzzleComplete() {
        System.out.println("testIsPuzzleComplete");
        Futoshiki game = new Futoshiki(5);
        game.fullFilledPuzzle();
        assertTrue(game.isPuzzleComplete());
    }

    @Test
    public void testFailIsPuzzleComplete(){
        System.out.println("testFailIsComplete()");
        Futoshiki game = new Futoshiki(5);
        game.fillPuzzle(5,8,8);
        assertFalse(game.isPuzzleComplete());
    }
    
    @Test
    public void testInputForNEWCommand() {
        System.out.println("testInputForNEWCommand");
        ByteArrayInputStream in = new ByteArrayInputStream("New 5\n".getBytes());
        System.setIn(in);
        Parser p = new Parser();
        Command c = null;
        
        
        c = p.getCommand();
        assertEquals(c.getCommand(), CommandWord.NEW);
        assertEquals(c.getValue(), 5);

    }
    
    @Test
    public void testInputForMARKCommand(){
        System.out.println("testInputForMARKCommand");
        ByteArrayInputStream in = new ByteArrayInputStream("mark 1 2 3\n".getBytes());
        System.setIn(in);
        Parser p = new Parser();
        Command c = p.getCommand();
        
        assertEquals(c.getCommand(), CommandWord.MARK);
        assertEquals(c.getColumn(), 1);
        assertEquals(c.getRow(), 2);
        assertEquals(c.getValue(), 3);
    }
    
    @Test
    public void testInputForCLEARCommand(){
        System.out.println("testInputForCLEARCommand");
        ByteArrayInputStream in = new ByteArrayInputStream("clear 2 1\n".getBytes());
        System.setIn(in);
        Parser p = new Parser();
        Command c = p.getCommand();
        
        assertEquals(c.getCommand(), CommandWord.CLEAR);
        assertEquals(c.getColumn(), 2);
        assertEquals(c.getRow(), 1);
    }
    
    @Test
    public void testInputForQUITCommand(){
        System.out.println("testInputForQUITCommand");
        ByteArrayInputStream in = new ByteArrayInputStream("quit\n".getBytes());
        
        System.setIn(in);
        Parser p = new Parser();
        Command c = p.getCommand();
        
        assertEquals(c.getCommand(), CommandWord.QUIT);
    }
    
    @Test
    public void testInputForUNKNOWNCommand(){
        System.out.println("testInputForUNKNOWNCommand");
        ByteArrayInputStream in = new ByteArrayInputStream("s\n".getBytes());
        
        System.setIn(in);
        Parser p = new Parser();
        Command c = p.getCommand();
        
        assertEquals(c.getCommand(), CommandWord.UNKNOWN);
    }
    
    
    @Test
    public void testClearSqaureError(){
        System.out.println("testErrorInSetSquare");
        Futoshiki game = new Futoshiki(5);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        game.fullFilledPuzzle();
        game.clearSquare(2,2);
        
        
        String s = "You can not edit this square! Please pick another one."+ System.getProperty("line.separator");
        assertEquals(out.toString(),s);
    }

}
