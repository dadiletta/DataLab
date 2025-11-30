import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * CerealRunner2 - CSV File Parser
 *
 * Activity 2: Complete this class to read cerealSubset.csv
 *
 * Goal: Read the CSV file and create Cereal objects for each row
 * Expected output: "76 records created."
 */
public class CerealRunner2 {
    // Declare your instance variable here
    // You need an ArrayList to store Cereal objects



    /**
     * Constructor - reads the CSV file and populates the cereals ArrayList
     *
     * Steps:
     * 1. Initialize your ArrayList
     * 2. Create a File object for "cerealSubset.csv"
     * 3. Create a Scanner to read from that file
     * 4. Loop while the scanner has more lines
     * 5. For each line:
     *    - Split by comma
     *    - Parse the values (use Integer.parseInt and Double.parseDouble)
     *    - Create a Cereal object
     *    - Add to ArrayList
     * 6. Close the scanner
     * 7. Handle FileNotFoundException with try-catch
     */
    public CerealRunner2() {




    }

    /**
     * Getter method for the cereals ArrayList
     */
    public ArrayList<Cereal> getCereals() {
        return null;  // Fix this
    }

    /**
     * Main method - creates runner and prints number of records
     */
    public static void main(String[] args) {
        // Create a CerealRunner2 object


        // Print the number of records created
        // Should output: "76 records created."


    }
}
