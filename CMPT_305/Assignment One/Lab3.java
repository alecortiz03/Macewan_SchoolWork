import java.text.NumberFormat;
import java.util.Scanner;

public class Lab3 {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner usrInput = new Scanner(System.in);
        
        // Prompt the user to enter a file name
        System.out.print("Please enter the CSV file name: ");

        // Read the file name from user input
        String fileName = usrInput.nextLine();

        // Create PropertyAssessments object and populate it with data from the file
        PropertyAssessments mainAssessments = new PropertyAssessments();
        mainAssessments = mainAssessments.createAssessment(fileName); // Create assessments from the file

        // Create a NumberFormat object to format numbers as currency
        NumberFormat currency = NumberFormat.getCurrencyInstance(); // Format numbers as currency

        // Check if there are no records in the assessments
        if (mainAssessments.getRecordAmt() == 0) { // Check if there are no records
            System.exit(0); // Exit if no records
        }

        // Create a Scanner object to read neighborhood name
        Scanner neighborhoodSearch = new Scanner(System.in);
        System.out.print("Please enter a neighborhood name: ");
        
        // Read the neighborhood name from user input
        String neighborhood = neighborhoodSearch.nextLine();

        // Search for assessments in the specified neighborhood
        PropertyAssessments neighborhoodAssessments = mainAssessments.searchNeighborhood(neighborhood);

        // Check if the neighborhood was found
        if (neighborhoodAssessments.getRecordAmt() == 0) {
            System.err.println("Error: Neighborhood not found...");
        } else {
            // Print the number of properties and their mean and median values
            System.out.print("There are " + neighborhoodAssessments.getRecordAmt() + " properties in " + neighborhood
            + "\nThe mean value is: " + currency.format(neighborhoodAssessments.calcMean()) + "\n" 
            + "The median value is: " + currency.format(neighborhoodAssessments.calcMedian()) + "\n");
        }

        // Create a Scanner object to read assessment class
        Scanner assessmentSearch = new Scanner(System.in);
        System.out.print("\nPlease enter an assessment class: ");
        
        // Read the assessment class from user input
        String assessmentClass = assessmentSearch.nextLine();

        // Search for assessments in the specified class
        PropertyAssessments classAssessments = mainAssessments.searchAssessmentClass(assessmentClass);

        // Check if the assessment class was found
        if (classAssessments.getRecordAmt() == 0) {
            System.err.println("Error: Assessment class not found...");
            System.exit(0);
        }

        // Print the number of properties and their min and max values
        System.out.print("There are " + classAssessments.getRecordAmt() + " properties in " + assessmentClass
        + "\nThe min value is: " + currency.format(classAssessments.getMin()) + "\n"
        + "The max value is: " + currency.format(classAssessments.getMax()) + "\n");
    }
}
