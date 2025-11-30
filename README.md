# AP Computer Science A - Data Lab

## Overview
In this lab, you'll work with real-world data using Java. You'll learn to:
- Verify data sources and download authentic datasets
- Create custom classes to represent data
- Read and parse CSV files manually
- Store data in ArrayLists
- Analyze data using filtering and searching algorithms

---

## Activity 1: Data Sources and Class Design

**Deliverables:**
1. Run warm-up files to understand live data fetching
2. Download authentic cereal data from Kaggle
3. Complete `Cereal.java` class with 5 instance variables

---

### Step 1: Understanding Live Data (5 minutes)

Before working with static CSV files, see how data can be fetched live from the internet.

**A. Run Welcome01.java**
- This program fetches **current weather data** from NOAA weather stations
- Notice it connects to a URL and pulls live information
- The Sinbad library handles the connection and parsing for you

**B. Modify Welcome02_Object.java**
- Currently compares 3 airport weather stations (KATL, KSAV, KSEA)
- Change one of the station IDs to your local airport code
- Find airport codes at: https://www.weather.gov/
- Run and see **your local weather** compared to others

**C. Explore Welcome03_List.java**
- This fetches a list of ALL weather stations in the US
- Enter a state abbreviation when prompted
- Notice how it:
  - Filters stations by state (ArrayList traversal)
  - Finds the southernmost station (max-finding algorithm)
- **Key concept:** You'll use these same patterns with cereal data!

---

### Step 2: Download Authentic Cereal Data

Now you'll get real nutritional data to analyze.

**A. Go to Kaggle's Cereal Dataset:**
https://www.kaggle.com/datasets/crawford/80-cereals

**B. Download the CSV file:**
- You may need to create a free Kaggle account (use your school email)
- Click "Download" to get `cereal.csv`
- This contains nutritional data for 77 breakfast cereals

**C. Understand the data:**
- Open `cereal.csv` in a text editor or spreadsheet software
- Each row is one cereal brand
- Columns include: name, calories, protein, fat, sodium, fiber, carbs, sugar, etc.
- **Note the format:** Values separated by commas (Comma-Separated Values)

**D. About the example data:**
- We've provided `cerealSubset.csv` with 76 cereals and 5 key fields
- This is a **simplified version** for learning purposes
- You'll work with this subset first, then can expand to the full dataset

**Why this matters:**
- You're verifying the data source is legitimate (Kaggle is reputable)
- Understanding CSV format prepares you for manual parsing
- Real data science: always check your sources!

---

### Step 3: Create the Cereal Class

**File:** `Cereal.java`

**Your task:** Design a class to represent one cereal with nutritional data.

**Requirements:**
Write a class with:
- **5 instance variables:**
  - `name` (String) - cereal brand name
  - `calories` (int) - calories per serving
  - `fiber` (int) - grams of fiber per serving
  - `carbohydrates` (int) - grams of carbs per serving
  - `cups` (double) - serving size in cups

- **Constructor:** Takes all 5 parameters (in that order) and initializes instance variables

- **Accessor methods:** One getter for each instance variable
  - `getName()` returns String
  - `getCalories()` returns int
  - `getFiber()` returns int
  - `getCarbohydrates()` returns int
  - `getCups()` returns double

- **toString() method:** Returns a readable string with all cereal data
  - Example: `"100% Bran - Calories: 70, Fiber: 10g, Carbs: 5g, Serving: 0.33 cups"`

**Data format from cerealSubset.csv:**
```
100% Bran,70,10,5,0.33
All-Bran,70,9,5,0.33
Apple Jacks,110,1,11,1.0
```
Format: name,calories,fiber,carbohydrates,cups

**Stuck? Search for:** "How to write a Java class with constructor and getters"

---

## Activity 2: Manual CSV Parsing

**Deliverables:**
- Complete `CerealRunner2.java` that reads the CSV file
- Output should show: "76 records created."

---

### Understanding the Task

In Activity 1, the Sinbad library automatically:
- Connected to data sources
- Parsed the format
- Created objects for you

Now you'll do this **manually** to understand what's happening behind the scenes.

**File:** `CerealRunner2.java`

**Your task:** Read `cerealSubset.csv` and create Cereal objects.

**Requirements:**

1. **Instance variable:** `private ArrayList<Cereal> cereals;`

2. **Constructor logic:**
   - Initialize the cereals ArrayList
   - Use `try-catch` to handle `FileNotFoundException`
   - Create a `File` object pointing to `"cerealSubset.csv"`
   - Create a `Scanner` to read from the file
   - Loop while the scanner `hasNextLine()`:
     - Read the line with `nextLine()`
     - Split by comma: `String[] parts = line.split(",");`
     - Extract each field from the array:
       - `parts[0]` is the name (already a String)
       - `parts[1]` needs `Integer.parseInt()` for calories
       - `parts[2]` needs `Integer.parseInt()` for fiber
       - `parts[3]` needs `Integer.parseInt()` for carbohydrates
       - `parts[4]` needs `Double.parseDouble()` for cups
     - Create a new Cereal object with those 5 values
     - Add it to the cereals ArrayList
   - Close the scanner
   - In catch block: print error message

3. **Getter method:** `getCereals()` returns the ArrayList

4. **Main method:**
   - Create a CerealRunner2 object
   - Print: `"76 records created."`
   - Optional: Print first 3 cereals to verify

**Expected Output:**
```
76 records created.
```

**Stuck? Search for:** "How to read CSV file in Java using Scanner and split"

---

## Activity 3: ArrayList Analysis

**Deliverables:**
- `filterCarbsPerCup(int min, int max)` method
- `highestPercentFiber()` method
- `findNetCarbs(Cereal c)` method

---

### Part A: Filter by Carbs Per Cup

**File:** `CerealRunner3.java`

**Research Question:** Which cereals have between 15 and 20 carbohydrates per cup?

**Why carbs PER CUP?**
Serving sizes vary (0.25 to 1.5 cups). We need to standardize for fair comparison.

**Method signature:**
```java
public static ArrayList<Cereal> filterCarbsPerCup(int min, int max)
```

**Algorithm:**
1. Create empty result ArrayList
2. Loop through all cereals (use for-each)
3. For each cereal:
   - Calculate: `carbsPerCup = carbohydrates / cups`
   - If carbsPerCup is between min and max (inclusive), add to result
4. Return the filtered ArrayList

**Pattern to use:** Similar to filtering WeatherStations by state in Welcome03_List

**Stuck? Search for:** "How to filter ArrayList based on calculated condition in Java"

---

### Part B: Find Highest Fiber Percentage

**Research Question:** Which cereal has the most fiber relative to its calories?

**Calculation:** `fiberPercent = fiber / calories`

**Method signature:**
```java
public static Cereal highestPercentFiber()
```

**Algorithm (max-finding pattern):**
1. If cereals list is empty, return null
2. Start with first cereal as "best so far"
3. Calculate its fiber percentage
4. Loop through remaining cereals (start at index 1):
   - Calculate current cereal's fiber percentage
   - If current > best, update best
5. Return the best cereal found

**Important:** Cast to `(double)` to avoid integer division!

**Pattern to use:** Similar to finding southernmost WeatherStation in Welcome03_List

**Stuck? Search for:** "Java find maximum value in ArrayList using traversal"

---

### Part C: Calculate Net Carbs

**Research Question:** What are the net carbs for specific cereals?

**Calculation:** `net carbs = carbohydrates - fiber`

**Why?** Fiber doesn't impact blood sugar, so low-carb diets subtract it.

**Method signature:**
```java
public static double findNetCarbs(Cereal c)
```

**Algorithm:**
Simply return the result of subtracting fiber from carbohydrates.

**Data Validation:**
When you run CerealRunner3, it will test three cereals:
- All-Bran with Extra Fiber
- Apple Jacks
- Cocoa Puffs

**One of these will have NEGATIVE net carbs. Why is this impossible?**

Think about what it means if fiber (14g) exceeds total carbohydrates (8g). What does this tell you about data quality?

**Stuck? Search for:** "How to write a calculation method in Java with object parameter"

---

## Testing Your Work

When you complete all three methods in `CerealRunner3.java`, run the main method. It will:
- Load all 76 cereals from the CSV
- Test your filterCarbsPerCup method
- Test your highestPercentFiber method
- Test your findNetCarbs method
- Show you the data validation issue

**Expected results:**
- ~35 cereals between 15-20 carbs per cup
- All-Bran with Extra Fiber has highest fiber percentage (~28%)
- All-Bran with Extra Fiber shows -6.0 net carbs (data error!)

---

## Files in This Project

| File | Purpose |
|------|---------|
| `Welcome01.java` | Demo: Live weather data fetching |
| `Welcome02_Object.java` | Demo: Custom objects and comparison |
| `Welcome03_List.java` | Demo: ArrayList filtering and max-finding |
| `Cereal.java` | **You create:** Custom class for cereal data |
| `CerealRunner2.java` | **You complete:** CSV file parser |
| `CerealRunner3.java` | **You complete:** Data analysis methods |
| `cerealSubset.csv` | Example data (76 cereals, 5 fields) |
| `cereal.csv` | Full Kaggle data (you download) |

---

## Getting Help

### If you're stuck:
1. **Re-read the algorithm** in the comments
2. **Look at the Welcome files** for similar patterns
3. **Search Google** using the provided search terms
4. **Ask specific questions** about what's confusing

### What NOT to do:
- Don't ask an AI to write the whole method
- Don't copy code you don't understand

### What TO do:
- Ask: "How do I loop through an ArrayList in Java?"
- Ask: "What's the syntax for splitting a string by commas?"
- Ask: "Why is my division returning 0 instead of a decimal?"

---

## Running Your Code

**Using command line:**
```bash
# Compile
javac -d bin -cp lib/sinbad.jar src/Welcome01.java
javac -d bin src/Cereal.java src/CerealRunner2.java

# Run
java -cp bin:lib/sinbad.jar Welcome01
java -cp bin CerealRunner2
```

**Or just use your IDE's run button!**

---

## Data Source Attribution

- Weather data: NOAA via weather.gov
- Cereal data: [Kaggle - 80 Cereals Dataset](https://www.kaggle.com/datasets/crawford/80-cereals)
- Sinbad library: [Berry College Sinbad Project](https://berry-cs.github.io/sinbad/)

---

## Learning Objectives

By completing this lab, you will:
- ✓ Verify data sources and understand data provenance
- ✓ Read and parse CSV files using Scanner and String methods
- ✓ Create custom classes to model real-world data
- ✓ Store objects in ArrayLists
- ✓ Implement filtering algorithms (ArrayList traversal)
- ✓ Implement max-finding algorithms
- ✓ Recognize data quality issues (negative net carbs!)
- ✓ Apply computational thinking to answer research questions

---

Good luck! 🎓
