# Data Files - Important Information

## cerealSubset.csv
**This is EXAMPLE DATA for learning purposes.**

- **76 cereal records** with **5 simplified fields**
- Fields: name, calories, fiber, carbohydrates, cups
- Source: Derived from Kaggle's 80 Cereals dataset
- **Use this for:** Activities 2 and 3 (CSV parsing and analysis)

### Known Data Quality Issues:
- Some cereals have **negative net carbs** (fiber > carbohydrates)
- This is chemically impossible and indicates data errors
- **This is intentional** - you'll discover this in Activity 3 Part C
- Teaches important lesson: always validate real-world data!

---

## cereal.csv
**You download this from Kaggle (authentic data).**

- Download from: https://www.kaggle.com/datasets/crawford/80-cereals
- **77 cereal records** with **16 fields**
- Full nutritional information
- **Use this for:** Understanding authentic data sources (Activity 1 Step 2)

### Fields in full dataset:
- name, manufacturer, type, calories, protein, fat
- sodium, fiber, carbs, sugar, potassium, vitamins
- shelf, weight, cups, rating

---

## Why Two Files?

1. **cerealSubset.csv** - Simplified for learning
   - Only 5 fields keeps code simple
   - Focus on core concepts (parsing, ArrayLists, algorithms)
   - Small enough to debug easily

2. **cereal.csv** - Real data for context
   - Shows what actual datasets look like
   - 16 fields would make initial learning harder
   - You can expand your code to use this later!

---

## Data Source Verification

Both datasets come from the same source:
- **Kaggle:** Reputable data science platform
- **Original source:** Published research data
- **Citation:** "80 Cereals" by Chris Crawford

Always verify your data sources:
- ✓ Check the website is reputable
- ✓ Look for original source citations
- ✓ Examine the data for obvious errors
- ✓ Cross-reference with other sources when possible
