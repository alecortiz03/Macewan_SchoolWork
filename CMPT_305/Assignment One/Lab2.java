import java.text.NumberFormat;
import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {

        // Create a Scanner object to read user input
        Scanner usrInput = new Scanner(System.in);
        
        // Prompt the user to enter a file name
        System.out.print("Enter a file name: ");

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

        // Print statistics of the assessments
        System.out.print("N = " + mainAssessments.getRecordAmt() + "\n"
        + "Max = " + currency.format(mainAssessments.getMax()) + "\n"
        + "Min = " + currency.format(mainAssessments.getMin()) + "\n"
        + "Range = " + currency.format(mainAssessments.getMax()) + "\n"
        + "Median = " + currency.format(mainAssessments.calcMedian()) + "\n"
        + "Mean = " + currency.format(mainAssessments.calcMean()) + "\n"); // Print statistics

        // Create another Scanner object to read user input for account search
        Scanner acntSrch = new Scanner(System.in);

        // Prompt the user to enter an account number
        System.out.print("\nFind a property assessment by account number: ");
        String acntNum = acntSrch.nextLine();

        try {
            // Search for the assessment by account number
            PropertyAssessment searchAssessment = mainAssessments.searchAccount(Integer.parseInt(acntNum)); // Search for assessment by account number

            // Print the details of the found assessment
            System.out.println("Account Number: " + searchAssessment.getAccountNum() + "\n"
            + "Address: " + searchAssessment.getAddress() + "\n"
            + "Assessed Value: " + currency.format(searchAssessment.getAssessedValue()) + "\n"
            + "Assessment Class: " + searchAssessment.getWardClass() + "\n"
            + "Neighbourhood: " + searchAssessment.getNeighborhood() + "\n"
            + "Location: " + searchAssessment.getLocation() + "\n"); // Print assessment details
        } catch (Exception e) {
            // Handle the case where the account number is not found
            System.err.println("Error: Account number not found..."); // Handle account not found
        }

        try {
            // Create another Scanner object to read user input for neighborhood search
            Scanner neighborSrch = new Scanner(System.in);

            // Prompt the user to enter a neighborhood
            System.out.print("Neighbourhood: ");
            String neighborhood = neighborSrch.nextLine();

            // Search for assessments by neighborhood
            PropertyAssessments neighborhoodAssessment = mainAssessments.searchNeighborhood(neighborhood); // Search for assessments by neighborhood

            // Print the statistics of the found neighborhood assessments
            System.out.println("Statistics (neighborhood = " + neighborhood + ")\n"
            + "N = " + neighborhoodAssessment.getRecordAmt() + "\n"
            + "Max = " + currency.format(neighborhoodAssessment.getMax()) + "\n"
            + "Min = " + currency.format(neighborhoodAssessment.getMin()) + "\n"
            + "Range = " + currency.format(neighborhoodAssessment.getMax()) + "\n"
            + "Median = " + currency.format(neighborhoodAssessment.calcMedian()) + "\n"
            + "Mean = " + currency.format(neighborhoodAssessment.calcMean()) + "\n"); // Print neighborhood statistics
        } catch (Exception e) {
            // Handle the case where the neighborhood is not found
            System.err.println("Error: Neighborhood not found..."); // Handle neighborhood not found
        }
    }
}
