package labs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoinTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setupStreams() {
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void testFlips() {
		CoinDriver.main(new String[] { });
		Scanner scan = new Scanner(outContent.toString());
		
		int longestRun = 0, currentRun = 0;
		for (int i = 0; i < 100; i++) {
			assertTrue(scan.hasNextLine());
			String line = scan.nextLine();
			if (line.equals("Heads")) {
				currentRun++;
				if (currentRun > longestRun) {
					longestRun = currentRun;
				}
			} else if (line.equals("Tails")) {
				currentRun = 0;
			} else {
				fail();
			}
		}
		
		assertTrue(scan.hasNextLine());
		
		String lastLine = scan.nextLine();
		String[] lineParts = lastLine.split(" ");
		int userLongestRun = Integer.parseInt(lineParts[lineParts.length - 1]);
		assertEquals("The maximum run should be " + longestRun + " not " + userLongestRun, longestRun, userLongestRun);
	
		scan.close();
	}

	@After
	public void destroyStreams() {
		System.setOut(null);
	}
}
