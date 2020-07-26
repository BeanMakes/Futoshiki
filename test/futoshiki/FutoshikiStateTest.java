/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bdtw20
 */
public class FutoshikiStateTest {
    
    public FutoshikiStateTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getChildren method, of class FutoshikiState.
     */
    @Test
    public void testGetChildren() {
        System.out.println("testGetChildren");
        Futoshiki f = new Futoshiki(5);
        FutoshikiState test = new FutoshikiState(f);
        assertTrue(test.getChildren().size()==5);
        f.setSquare(1,0,0);
        test = new FutoshikiState(f);
        assertTrue(test.getChildren().size()==4);
        f.setSquare(2,0,1);
        test = new FutoshikiState(f);
        assertTrue(test.getChildren().size() == 3);
        f.setSquare(3,0,2);
        test = new FutoshikiState(f);
        assertTrue(test.getChildren().size() == 2);
        f.setSquare(4,0,3);
        test = new FutoshikiState(f);
        assertTrue(test.getChildren().size() == 1);
        
    }
    
}
