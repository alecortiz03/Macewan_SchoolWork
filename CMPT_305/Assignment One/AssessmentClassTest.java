import org.junit.*;
import static org.junit.Assert.*;

public class AssessmentClassTest {
    private AssessmentClass assessmentClassTestOne;
    private AssessmentClass assessmentClassTestTwo;

    @Before
    public void setUp() throws Exception {
        assessmentClassTestOne = new AssessmentClass("Residnetial", "90");
        assessmentClassTestTwo = new AssessmentClass("Commercial", "80");
    }
    @Test
    public void testGetClassName() {
        assertEquals("Residnetial", assessmentClassTestOne.getClassName());
        assertEquals("Commercial", assessmentClassTestTwo.getClassName());
    }

    @Test
    public void testGetPercent() {
        assertEquals("90", assessmentClassTestOne.getPercent());
        assertEquals("80", assessmentClassTestTwo.getPercent());
    }

    @Test
    public void testToString() {
        assertEquals("Residnetial 90% ", assessmentClassTestOne.toString());
        assertEquals("Commercial 80% ", assessmentClassTestTwo.toString());
    }
}
