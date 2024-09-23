// CMPT 305 X01L - LAB ONE - ALEC ORTIZ - 3114013

//====== PACKAGES ======================================
import java.util.Scanner;
import java.io.BufferedReader;                       //|
import java.io.IOException;                          //|
import java.nio.file.Files;                          //|
import java.nio.file.Paths;                          //|
import java.util.Arrays;                             //|
//======================================================

//====== CLASS: LAB ONE AO ======================================

/*
LabOneAO
----------------
- Purpose: Parse Data found in csv file
    1. Print the number of records in file
    2. Print the highest and lowest assessed values in file
    3. Print the number of unique wards in file
    4. Print the unique ward classes in file
 */

public class LabOneAO {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Input your file name:  ");
        // String termed csvFileName | Title of file for data usage 
        String csvFileName = userInput.nextLine();
        // Try 
        try {
            // String termed data | Set to the return of readData method 
            String[][] data = readData(csvFileName);
            // Call method printData 
            printData(data);
        } catch (IOException e) {
            // Excpetion if try fails | Print user message 
            System.out.println("Failed to read " + csvFileName);
        }
        userInput.close();
    }
//==================================================================

//====== READ DATA =================================================
    /*
     * Read the contents of a CSV file and return data as a 2D array of String.
     * 
     * @param csvFileName - the CSV file name
     * @return data - the values in the CSV file
     * @throws IOException - IO exception
     */
    public static String[][] readData(String csvFileName) throws IOException {
        // 2D Array of type string | Termed data
        String[][] data;
        // Set index to integer 0 
        int index = 0;
        // Try-with-resources statement to create a stream to read the CSV file. Automatically closes the resource.
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFileName))) {
            
            // Skip the header - this assumes the first line is a header
            reader.readLine();
            // Create 2D array to store all rows of data as String
            int initialSize = 100;
            // Set data to new String array of size 100 
            data = new String[initialSize][];
            // Read the file line by line and store all rows into a 2D array
            String line;
            
            while ((line = reader.readLine()) != null) {
                // Split a line by comma works for simple CSV files
                String[] values = line.split(",");

                // Check if the array is full
                if (index == data.length)
                // Array is full, create and copy all values to a larger array
                {
                    data = Arrays.copyOf(data, data.length * 2);
                }
                data[index++] = values;
            }
        }

        // Remove empty rows in the array and return it
        return Arrays.copyOf(data, index);
    }
//==========================================================================================



//====== PRINT DATA ========================================================================
    /**
     * Prints the final answers to four question in Lab One:
     * 1. How many records are in the csv file?
     * 2. What is the highest and lowest assessed values in the csv file?
     * 3. How many wards are in Edmonoton?
     * 4. What are the different Ward Classes in Edmonton?
     * @param data - 2D array containing data
     */
    public static void printData(String[][] data) {
        //====== LAB TITLE =================================================================
        // Print Title of Lab
        System.out.println("Lab One - Alec Ortiz - 3114013 - CMPT 305 XO1L");
        //===================================================================================

        //====== QUESTION ONE ================================================================
        System.out.println("\nQuestion 1\n----------");
        // Print the length of the String Array data | Print the number of records found in csv file 
        System.out.println("The number of records in this file is: " + data.length);
        //====================================================================================
        
        //====== QUESTION TWO ================================================================
        // Call highVal to get the highest assessed value located in data and set it to highestValue
        int highestValue = highVal(data);
        System.out.println("\nQuestion 2\n----------");
        // Print answer for highest assessed value
        System.out.println("The highest value is: " + highestValue);
        // Call lowVal to get the lowest assessed value located in data and set it to lowestValue
        int lowestValue = lowVal(data);
        // Print answer for lowest assessed value
        System.out.println("The lowest value is: " + lowestValue);
        //====================================================================================

        //====== QUESTION THREE ==============================================================
        // Call countWards to count the number of wards in Edmonton and set it to wardCount
        int wardCount = countWards(data);
        System.out.println("\nQuestion 3\n----------");
        // Print answer for the number of wards found in Edmonton
        System.out.println("The number of Wards in Edmonton is: " + wardCount);
        //====================================================================================

        //====== QUESTION FOUR ================================================================
        System.out.println("\nQuestion 4\n----------");
        // Call wardClass to print the unique ward classes found in the file
        wardClass(data);
        //=====================================================================================
    }
//=============================================================================================


//====== HIGH VAL ============================================================================== 
    /**
     * Returns the highest value found in the "Assessed Value" column in the csv file 
     *
     * @param data - 2D array containing data
     * @return highestValue - Int containing highest value in csv file
     */
    public static int highVal(String[][] data){
        // Int termed highestValue set to first "Assessed Value" in csv file 
        int highestValue = Integer.parseInt(data[0][8]);
        // For loop | Iterated through "Assessed Value" cloumn
        for (String[] row:data){
            // Int termed checkValue | Value to be checked over 
            int checkValue = Integer.parseInt(row[8]);
            // If checkValue is higher than the highest value
            if (checkValue > highestValue){
                // Set the highest value to the checked value 
                highestValue = checkValue;
            }
        }
        // Return the highest value 
        return highestValue;
    }
//==========================================================================================


//====== LOW VAL =========================================================================
     /**
     * Returns the lowest value found in the "Assessed Value" column in the csv file 
     *
     * @param data - 2D array containing data
     * @return lowestValue - Int containing lowest value in csv file
     */
    public static int lowVal(String[][] data){
        // Int termed lowestValue set to first "Assessed Value" in csv file
        int lowestValue = Integer.parseInt(data[0][8]);
        // For loop | Iterate through "Assessed Value" column in csv file
        for (String[] row:data){
            // If column being checked is lower than the lowest value
            if (Integer.parseInt(row[8]) < lowestValue){
                // Set the lowest value to the value in the column
                lowestValue = Integer.parseInt(row[8]);
            }
        }
        // Return the lowest value 
        return lowestValue;
    }
//=========================================================================================


//====== COUNT WARDS =====================================================================
    /**
     * Returns the number of unique wards in  
     *
     * @param data - 2D array containing data
     * @return numOfWards - Int that represents that number unique wards 
     */
    public static int countWards(String[][] data){
        // String Array termed wardList
        String wardList[];
        // Set Ward list to new string array of size 1000
        wardList = new String[1];
        // Int termed numOfWards set to 0
        int numOfWards = 0;
        // Int termed count set to 0 | Used as counter for program 
        int count = 0;
        // For loop | Iterates through wards in csv file 
        for (String[] row:data){
            // Boolean termed dup set to false | Set to true if duplicate is found 
            boolean dup = false;
            // For loop | Iterate through wardList | List of wards that program has marked  
            for (String i:wardList){
                // If ward program is checking, already exists once
                if(row[7].equals(i)){
                    // Set dup to true
                    dup = true;
                }
            }
            // If dup is set to false
            if (dup == false){
                // If the entry in the list is not an empty entry
                if (row[7].equals("") == false){
                    if(count == wardList.length){
                        wardList = Arrays.copyOf(wardList, wardList.length * 2);
                    }
                    // Add the entry to the list of wards
                    wardList[count] = row[7];
                    // Increment numOfWards
                    numOfWards += 1;
                }
                // Increment count 
                count += 1;
                
            }
        }
        // Return numOfWards
        return numOfWards;
    }
//========================================================================================


//====== WARD CLASS ======================================================================
/**
     * Print out all the unique wards found in the csv file
     *
     * @param data - 2D array containing data
     */
    public static void wardClass(String[][] data){
        // String Array termed classList
        String classList[];
        // Set classList to a string array with a size of 1000
        classList = new String[1];
        // Int termed count set to 0 | Used as counter for method 
        int count = 0;
        // For loop | Iterate through wards
        for (String[] row:data){
            // Boolean termed dup set to false | Set to true if duplicate 
            boolean dup = false;
            // For loop | Iterate through classList
            for (String i:classList){
                // If ward is already in list
                if(row[15].equals(i)){
                    // Set dup to true
                    dup = true;
                }
            }
            // If dup is set to false 
            if (dup == false){
                // If entry is not an emptry entry 
                if (row[15].equals("") == false){
                    if(count == classList.length){
                        classList = Arrays.copyOf(classList, classList.length * 2);
                    }
                    // Add entry to classList
                    classList[count] = row[15];
                }
                // Increment count by 1                
                count += 1;
            }
        }
        // Print Ward for user
        System.out.println("The different Ward Classes in Edmonton are: \n");
        // For loop | Iterate through classist 
        for (String i:classList){
            // If entry is not null
            if(i != null){
                // Print entry 
                System.out.println(i);
            }
        }
    }
}
//========================================================================================
