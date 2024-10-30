import org.junit.*;
import static org.junit.Assert.*;

public class AssessmentClassesTest {
    private AssessmentClasses assessmentClassesTestOne;
    private AssessmentClasses assessmentClassesTestTwo;
    private AssessmentClass testEntryOne;
    private AssessmentClass testEntryTwo;
    private AssessmentClass testEntryThree;
    private AssessmentClass testEntryFour;

    @Before
    public void setUp() throws Exception {
        assessmentClassesTestOne = new AssessmentClasses();
        assessmentClassesTestTwo = new AssessmentClasses();
        testEntryOne = new AssessmentClass("Residential", "90");
        testEntryTwo = new AssessmentClass("Commercial", "80");
        testEntryThree = new AssessmentClass("Test1", "70");
        testEntryFour = new AssessmentClass("Test2", "60");

        assessmentClassesTestOne.addClass(testEntryOne);
        assessmentClassesTestOne.addClass(testEntryTwo);
    }
    @Test
    public void testAddClass() {
        assertEquals(2, assessmentClassesTestOne.getRecordAmt());
        assessmentClassesTestOne.addClass(testEntryThree);
        assertEquals(3, assessmentClassesTestOne.getRecordAmt());
        assessmentClassesTestOne.addClass(testEntryFour);
        assertEquals(4, assessmentClassesTestOne.getRecordAmt());

    }

    @Test
    public void testGetClass() {
        assertEquals("Residential", assessmentClassesTestOne.getClass(0).getClassName());
        assertEquals("Commercial", assessmentClassesTestOne.getClass(1).getClassName());
    }

    @Test
    public void testGetRecordAmt() {
        assertEquals(2, assessmentClassesTestOne.getRecordAmt());
    }

    @Test
    public void testToString() {
        assertEquals("[ Residential 90% Commercial 80% ]", assessmentClassesTestOne.toString());
    }
}
