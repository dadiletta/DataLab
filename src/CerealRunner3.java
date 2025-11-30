import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * CerealRunner3 - Data Analysis Methods
 *
 * Activity 3: Complete three analysis methods
 *
 * This file includes a complete main method that will test your work.
 * DO NOT EDIT the main method - only complete the three method stubs above it.
 */
public class CerealRunner3 {
    // Static ArrayList to hold all cereal data
    private static ArrayList<Cereal> cereals;

    /**
     * Part A: Filter cereals by carbs per cup
     *
     * Returns cereals with carbs-per-cup between min and max (inclusive).
     *
     * Algorithm:
     * 1. Create empty result ArrayList
     * 2. Loop through all cereals
     * 3. Calculate carbsPerCup for each
     * 4. If in range, add to result
     * 5. Return result ArrayList
     *
     * @param min minimum carbs per cup (inclusive)
     * @param max maximum carbs per cup (inclusive)
     * @return ArrayList of cereals meeting criteria
     */
    public static ArrayList<Cereal> filterCarbsPerCup(int min, int max) {




        return null;  // Replace with your code
    }

    /**
     * Part B: Find cereal with highest fiber percentage
     *
     * Returns the Cereal with the highest fiber-to-calorie ratio.
     *
     * Algorithm:
     * 1. Handle edge case (empty list)
     * 2. Start with first cereal as best
     * 3. Loop through remaining cereals
     * 4. Compare fiber percentages
     * 5. Track the best
     * 6. Return best cereal
     *
     * Remember: Cast to (double) to avoid integer division!
     *
     * @return Cereal with highest fiber percentage, or null if empty
     */
    public static Cereal highestPercentFiber() {




        return null;  // Replace with your code
    }

    /**
     * Part C: Calculate net carbs
     *
     * Net carbs = carbohydrates - fiber
     *
     * @param c the Cereal to analyze
     * @return net carbs
     */
    public static double findNetCarbs(Cereal c) {



        return 0;  // Replace with your code
    }

    // ========================================================================
    // DO NOT EDIT BELOW THIS LINE
    // The main method will test your three methods above
    // ========================================================================

    /**
     * Main method - tests all three analysis methods
     */
    public static void main(String[] args) {
        // Load cereal data from CSV file
        cereals = new ArrayList<Cereal>();

        try {
            File cerealFile = new File("cerealSubset.csv");
            Scanner fileScanner = new Scanner(cerealFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                String name = parts[0];
                int calories = Integer.parseInt(parts[1]);
                int fiber = Integer.parseInt(parts[2]);
                int carbohydrates = Integer.parseInt(parts[3]);
                double cups = Double.parseDouble(parts[4]);

                Cereal c = new Cereal(name, calories, fiber, carbohydrates, cups);
                cereals.add(c);
            }

            fileScanner.close();
            System.out.println("Loaded " + cereals.size() + " cereals.\n");

        } catch (FileNotFoundException e) {
            System.out.println("Error: cerealSubset.csv not found!");
            return;
        }

        // Test Method 1: filterCarbsPerCup
        System.out.println("=== Cereals with 15-20 carbs per cup ===");
        ArrayList<Cereal> moderateCarbCereals = filterCarbsPerCup(15, 20);
        if (moderateCarbCereals != null) {
            System.out.println("Found " + moderateCarbCereals.size() + " cereals:");
            for (Cereal c : moderateCarbCereals) {
                double carbsPerCup = c.getCarbohydrates() / c.getCups();
                System.out.printf("  %s (%.1f carbs/cup)%n", c.getName(), carbsPerCup);
            }
        }

        // Test Method 2: highestPercentFiber
        System.out.println("\n=== Cereal with Highest Fiber Percentage ===");
        Cereal fiberChamp = highestPercentFiber();
        if (fiberChamp != null) {
            double fiberPercent = ((double) fiberChamp.getFiber() / fiberChamp.getCalories()) * 100;
            System.out.printf("%s has %.1f%% fiber%n", fiberChamp.getName(), fiberPercent);
            System.out.println(fiberChamp);
        }

        // Test Method 3: findNetCarbs (first 5 cereals)
        System.out.println("\n=== Net Carbs Examples ===");
        for (int i = 0; i < 5 && i < cereals.size(); i++) {
            Cereal c = cereals.get(i);
            double netCarbs = findNetCarbs(c);
            System.out.printf("%s: %.0f net carbs (Total: %d, Fiber: %d)%n",
                    c.getName(), netCarbs, c.getCarbohydrates(), c.getFiber());
        }

        // Data Validation Test - Part C
        System.out.println("\n=== Data Validation: Testing Specific Cereals ===");
        for (Cereal c : cereals) {
            if (c.getName().equals("All-Bran with Extra Fiber") ||
                c.getName().equals("Apple Jacks") ||
                c.getName().equals("Cocoa Puffs")) {
                System.out.println("\nCereal: " + c.getName() + ", NetCarbs: " + findNetCarbs(c));
            }
        }

        System.out.println("\n--- Reflection Questions ---");
        System.out.println("1. Which cereal has negative net carbs? Why is this impossible?");
        System.out.println("2. What does this tell you about the quality of this data set?");
    }
}
