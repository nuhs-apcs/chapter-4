package labs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RectangleTest {
	public static final double TOLERANCE = 0.00001;
	
	double width = -3.0, height = 4.0, diagonal = 5.0;
	double width2 = 32.0, height2 = -32.000004;
	Rectangle rect = new Rectangle(width, height);
	Rectangle rect2 = new Rectangle(width2, height2);
	
	@Test
	public void testGetters() {
		assertEquals(Math.abs(width), rect.getWidth(), TOLERANCE);
		assertEquals(Math.abs(height), rect.getHeight(), TOLERANCE);
	}
	
	@Test
	public void testArea() {
		assertEquals(Math.abs(width * height), rect.area(), TOLERANCE);
	}
	
	@Test
	public void testPerimeter() {
		assertEquals(2.0 * (Math.abs(width) + Math.abs(height)), rect.perimeter(), TOLERANCE);
	}
	
	@Test
	public void testEquals() {
		assertTrue("Equals (same instance)", rect.equals(rect));
		assertTrue("Equals (new instance)", rect.equals(new Rectangle(width, height)));
		assertFalse("Equals (not equal)", rect.equals(rect2));
	}
	
	@Test
	public void testDiagonal() {
		assertEquals(diagonal, rect.diagonal(), TOLERANCE);
	}
	
	@Test
	public void testSquare() {
		assertFalse(rect.isSquare());
		assertTrue(rect2.isSquare());
	}
}
