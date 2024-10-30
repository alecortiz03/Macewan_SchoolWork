import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class LocationTest {
    private Location LocationTestOne;
    private Location LocationTestTwo;

    @Before
    public void setUp() throws Exception { // Setting up the Location object
        LocationTestOne = new Location(1.0, 2.0); // Creating a new Location object with x = 1.0 and y = 2.0
        LocationTestTwo = new Location(10, 10); // Creating a new Location object with x = 10 and y = 10

    }

    @Test
    public void testCompareTo() {
        assertEquals(-1, LocationTestOne.compareTo(LocationTestTwo)); // Comparing LocationTestOne with LocationTestTwo
        assertEquals(1, LocationTestTwo.compareTo(LocationTestOne)); // Comparing LocationTestTwo with LocationTestOne
        assertEquals(0, LocationTestOne.compareTo(LocationTestOne)); // Comparing LocationTestOne with itself
        assertThrows(NullPointerException.class, () -> LocationTestOne.compareTo(null)); // Checking if NullPointerException is thrown when comparing LocationTestOne with null

    }

    @Test
    public void testEquals() {
        assertTrue(LocationTestOne.equals(LocationTestOne)); // Checking if LocationTestOne is equal to itself
        assertFalse(LocationTestOne.equals(LocationTestTwo)); // Checking if LocationTestOne is equal to LocationTestTwo
        assertFalse(LocationTestOne.equals(null)); // Checking if LocationTestOne is equal to null
        assertFalse(LocationTestOne.equals(new Object())); // Checking if LocationTestOne is equal to an object
       

    }

    @Test
    public void testGetX() {
        assertEquals(1.0, LocationTestOne.getX(), 0.0); // Checking if the x-coordinate of LocationTestOne is 1.0
        assertEquals(10.0, LocationTestTwo.getX(), 0.0); // Checking if the x-coordinate of LocationTestTwo is 10.0

    }

    @Test
    public void testGetY() {
        assertEquals(2.0, LocationTestOne.getY(), 0.0); // Checking if the y-coordinate of LocationTestOne is 2.0
        assertEquals(10.0, LocationTestTwo.getY(), 0.0); // Checking if the y-coordinate of LocationTestTwo is 10.0

    }

    @Test
    public void testHashCode() {
        assertEquals(LocationTestOne.hashCode(), LocationTestOne.hashCode()); // Checking if the hash code of LocationTestOne is equal to itself
        assertNotEquals(LocationTestOne.hashCode(), LocationTestTwo.hashCode()); // Checking if the hash code of LocationTestOne is not equal to LocationTestTwo

    }

    @Test
    public void testToString() {
        assertEquals("( 1.0, 2.0 )", LocationTestOne.toString()); // Checking if the string representation of LocationTestOne is correct
        assertEquals("( 10.0, 10.0 )", LocationTestTwo.toString()); // Checking if the string representation of LocationTestTwo is correct

    }
}
