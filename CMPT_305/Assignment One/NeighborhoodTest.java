// Purpose: This file is used to test the Neighborhood class.
import org.junit.*;
import static org.junit.Assert.*;
public class NeighborhoodTest {
    private Neighborhood neighborhoodTestOne;
    private Neighborhood neighborhoodTestTwo;

    @Before
    public void setUp() throws Exception {
        neighborhoodTestOne = new Neighborhood("Downtown", "Ward 1");
        neighborhoodTestTwo = new Neighborhood("Uptown", "Ward 2");
    }

    @Test
    public void testGetNeighborhood() {
        assertEquals("Downtown", neighborhoodTestOne.getNeighborhood());
        assertEquals("Uptown", neighborhoodTestTwo.getNeighborhood());

    }

    @Test
    public void testGetWard() {
        assertEquals("Ward 1", neighborhoodTestOne.getWard());
        assertEquals("Ward 2", neighborhoodTestTwo.getWard());

    }

    @Test
    public void testToString() {
        assertEquals("Downtown (Ward 1)", neighborhoodTestOne.toString());
        assertEquals("Uptown (Ward 2)", neighborhoodTestTwo.toString());
    }
}
