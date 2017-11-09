package labs;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class StudentTest {
	
	@Test
	public void testStudent() {	
		int[] grades = {87, 93, 75, 98};
		int numGrades = grades.length;
		
		String[] gradeInputs = new String[numGrades];
		for (int i = 0; i < numGrades; i++) {
			gradeInputs[i] = Integer.toString(grades[i]);
		}
		
		int numStudents = grades.length / 2;
		
		for (int i = 0; i < numStudents; i++) {
			System.setIn(new ByteArrayInputStream((gradeInputs[2 * i] + "\n" + gradeInputs[2 * i + 1] + "\n").getBytes()));
			
			String studentName = "Student" + i;
			Student nextStudent = new Student(studentName);
			
			assertEquals("Expected getName() to return the student's name", studentName, nextStudent.getName());
			
			nextStudent.inputGrades();
			
			int firstGrade = grades[2 * i], secondGrade = grades[2 * i + 1];
			int average = (firstGrade + secondGrade) / 2;
			
			assertEquals("The average grade should be computed correctly", average, nextStudent.getAverage());
			
			String stringRep = "Name: " + studentName + "\tTest1: " + firstGrade + "\tTest2: " + secondGrade;
			assertEquals("toString() should return a properly-formatted string (hint: use tabs)", stringRep, nextStudent.toString());
		}
		
	}
}
