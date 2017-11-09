package labs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NameTest {
	private Name me = new Name("Ryan", "Campbell", "Brott");
	private Name alterEgo = new Name("RYaN", "CAMpbeLl", "bRoTt");
	
	@Test
	public void testGetters() {
		assertEquals("Ryan", me.getFirst());
		assertEquals("Campbell", me.getMiddle());
		assertEquals("Brott", me.getLast());
	}
	
	@Test
	public void testFirstMiddleLast() {
		assertEquals("Ryan Campbell Brott", me.firstMiddleLast());
	}
	
	@Test
	public void testLastFirstMiddle() {
		assertEquals("Brott, Ryan Campbell", me.lastFirstMiddle());
	}
	
	@Test
	public void testEquals() {
		assertTrue("Names should be considered equivalent if each part matches case insensitively", me.equals(alterEgo));
	}
	
	@Test
	public void testInitials() {
		assertEquals("The proper initials should be computed", "RCB", alterEgo.initials());
	}

	@Test
	public void testLength() {
		assertEquals(17, me.length());
	}
}
