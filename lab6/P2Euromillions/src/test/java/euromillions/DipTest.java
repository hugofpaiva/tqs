/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    /**
     * Tests constructor from array giving a bad array (not 5 numbers and/or not 2 starts),
     * since a valid one is used on setUp
     */
    @Test
    public void testConstructorFromBadArrays() {
        assertThrows(IllegalArgumentException.class, () ->new Dip(new int[]{10, 20, 30, 40, 50, 41}, new int[]{1, 2}), "Numbers array must throw IllegalArgumentException if not 5 elements");

        assertThrows(IllegalArgumentException.class, () ->new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2, 3}), "Stars array must throw IllegalArgumentException if not 2 elements");
    }

    @Test
    public void testFormat() {
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

}
