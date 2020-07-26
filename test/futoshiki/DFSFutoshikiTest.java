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
public class DFSFutoshikiTest {
    
    public DFSFutoshikiTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of depthFirstSearch method, of class DFSFutoshiki.
     */
    @Test
    public void testDepthFirstSearch() {
        System.out.println("testDepthFirstSearch");
        Futoshiki game = new Futoshiki(5);
        game.fillPuzzle(3, 2, 2);
        System.out.println(game.displayString());
        DFSFutoshiki test = new DFSFutoshiki(game);
        System.out.println("------------------------------");
        System.out.println(test.depthFirstSearch().displayString());
        assertTrue(test.depthFirstSearch().isPuzzleFillComp());
        
    }
    
}
