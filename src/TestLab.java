

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLab {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setupStreams() {
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void testFlips() {
		CoinTest.main(new String[] { });
		Scanner scan = new Scanner(outContent.toString());
		
		int longestRun = 0, currentRun = 0;
		for (int i = 0; i < 100; i++) {
			assertTrue("Does your program print 100 results?", scan.hasNextLine());
			String line = scan.nextLine();
			if (line.equals("Heads")) {
				currentRun++;
				if (currentRun > longestRun) {
					longestRun = currentRun;
				}
			} else if (line.equals("Tails")) {
				currentRun = 0;
			} else {
				fail("The first 100 lines must be either 'Heads' or 'Tails'");
			}
		}
		
		assertTrue("Did you add the summary line?", scan.hasNextLine());
		
		String lastLine = scan.nextLine();
		String[] lineParts = lastLine.split(" ");
		int userLongestRun = Integer.parseInt(lineParts[lineParts.length - 1]);
		assertEquals("The maximum run should be " + longestRun + " not " + userLongestRun, longestRun, userLongestRun);
	}
	
	@Test
	public void testAccount() {
		Account acct1, acct2;
		
		acct1 = new Account(1000, "Sally", 1111);
		acct2 = new Account(500, "Joe", 2222);
		
		acct2.deposit(100);
		
		assertEquals("Joe's balance should be $600 after the first deposit", 600.0, acct2.getBalance(), 0.00001);
		
		acct2.withdraw(1000);
		
		assertEquals("Joe's balance should remain unchanged after withdrawing more than the balance", 600.0, acct2.getBalance(), 0.00001);
		
		acct1.withdraw(50);
		
		assertEquals("Sally's balance should be $950 after the first withdrawal", 950.0, acct1.getBalance(), 0.00001);
		
		acct1.chargeFee();
		acct2.chargeFee();
		
		assertEquals("Charging a fee should change the account balance", 940.0, acct1.getBalance(), 0.00001);
		assertEquals("Charging a fee should change the account balance", 590.0, acct2.getBalance(), 0.00001);
		
		acct2.changeName("Joseph");
		
		assertTrue("The changeName() method should change the account name", acct2.toString().contains("Joseph"));
	}
	
	@Test
	public void testStudent() {	
		int[] grades = {87, 93, 75, 98};
		int numGrades = grades.length;
		
		String[] gradeInputs = new String[numGrades];
		for (int i = 0; i < numGrades; i++) {
			gradeInputs[i] = Integer.toString(grades[i]);
		}
		
		int numStudents = grades.length / 2;
		
		System.setIn(new ByteArrayInputStream(String.join("\n", gradeInputs).getBytes()));
		
		for (int i = 0; i < numStudents; i++) {
			String studentName = "Student" + i;
			Student nextStudent = new Student(studentName);
			
			assertEquals("getName() should return the student's name", studentName, nextStudent.getName());
			
			nextStudent.inputGrades();
			
			int firstGrade = grades[2 * i], secondGrade = grades[2 * i + 1];
			int average = (firstGrade + secondGrade) / 2;
			
			assertEquals("The average grade should be computed correctly", average, nextStudent.getAverage());
			
			String stringRep = "Name: " + studentName + "\tTest1: " + firstGrade + "\tTest2: " + secondGrade;
			assertEquals("toString() should return a properly-formatted string (hint: use tabs)", stringRep, nextStudent.toString());
		}
		
	}
	
	@After
	public void destroyStreams() {
		System.setOut(null);
	}
	
}
