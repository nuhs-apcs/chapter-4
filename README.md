# Chapter 4 Lab Manual

Adapted from the lab assignments found at https://github.com/orhs-apcs/chapter-4 

## Table of Contents

1. Using the Coin Class
2. Creating a Bank Account Class
3. Tracking Grades
4. Creating a Band Booster Class
5. Representing Names

## Using the Coin Class

In the lab project, there is a `Coin` class that implements the basic behavior of a coin. Your task is to write a program in the `CoinTest` class that finds the length of the longest run of consecutive heads in 100 flips of a coin. You will need to use a `Coin` object to emulate the coin flips. A skeleton of the program is provided in the `CoinTest.java` file that explains the details of your program implementation. 

Here is a sample run of the program:

```
Tails
Heads
Heads
Heads
Heads
Heads
Heads
Tails
Tails
Tails
Heads
Heads
Tails
Heads
Heads
--- output truncated to save space --- 
The maximum run of HEADS was 6
```

## Creating a Bank Account Class

The file `Account.java` contains a partial definition for a class representing a bank account. Complete the `Account` class as described below.

1. Complete the `toString()` method body; this should return a string containing the name, account number, and the balance for the account. **Note:** the `toString()	` should not call `System.out.println()` and instead just return the appropriate string representation.
2. Fill in the code for `chargeFee()` so that it deducts a service fee from the account.
3. Fill in the code for `changeName()` so that it takes a string as a parameter and set the account name to the provided string.

## Tracking Grades

A teacher wants a program to keep track of grades for students and decides to create a `Student` class for use in the program:

1. Each student will be described by three pieces of data: his/her name, his/her score on test #1, and his/her score on test #2.

2. There will be a single constructor that takes the name of the student as its only argument.

3. There will be three methods: `getName()`, which will return the student's name; `inputGrades()`, which will prompt for and read in the student's test grades (using a `Scanner`); and `getAverage()`, which will compute and return the student's average.

4. There will be a `toString()` method which prints a summary of the student per the following example (yes, using tabs when necessary):

   ```
   Name: Joe	Test1: 85	Test2: 91
   ```

Complete the class definition in `Student.java` appropriately. 

## Creating a Band Booster Class

In this exercise, you will write a class that models a band booster and their candy sales. The `BandBooster` class should be implemented according to the following instructions:

1.  The `BandBooster` class should contain two pieces of instance data: `name` and `boxesSold`.

2. There should be a constructor that takes the band booster's name as the only argument. The initial count of `boxesSold` should be 0.

3. The method `getName()` should return the name of the band booster.

4. The method `updateSales()` should take a single integer parameter representing the number of additional boxes sold. This should be added to the total number of boxes sold. **Note:** negative inputs to this method should be ignored and not affect the number of boxes sold.

5. The method `toString()` should return a string containing the name of the band booster and the number of boxes he/she has sold per this format:

   ```
   Joe:	16 boxes
   ```

## Representing Names

Implement a `Name` class with the following methods:

- `public Name(String first, String middle, String last)`—constructor
- `public String getFirst()`—returns the first name
- `public String getMiddle()`—returns the middle name
- `public String getLast()`—returns the last name
- `public String firstMiddleLast()`—returns a string containing the person's full name in order, e.g. `"Mary Jane Smith"`
- `public String lastFirstMiddle()`—returns a string containing the person's full name with the last name first followed by a comma, e.g. `"Smith, Mary Jane"`
- `public boolean equals(Name otherName)`—returns `true` if this name is **case-insensitively** equal to `otherName`
- `public String initials()`—returns the person's initials all uppercase, e.g. `"MJS"`
- `public int length()`—returns the total number of characters in the full name, **not** including spaces