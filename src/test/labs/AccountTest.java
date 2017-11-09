package labs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AccountTest {
	@Test
	public void testDeposit() {
		Account account = new Account(250, "John", 10);
		account.deposit(100);
		assertEquals(350, account.getBalance(), 0.00001);
	}
	
	@Test
	public void testWithdraw() {
		Account account = new Account(250, "John", 10);
		account.withdraw(50);
		assertEquals(200, account.getBalance(), 0.00001);
		account.withdraw(1000);
		assertEquals(200, account.getBalance(), 0.00001);
	}
	
	@Test
	public void testChargeFee() {
		Account account = new Account(250, "John", 10);
		account.chargeFee();
		assertEquals(240, account.getBalance(), 0.00001);
	}
	
	@Test
	public void testChangeName() {
		Account account = new Account(250, "John", 10);
		account.changeName("Joseph");
		assertTrue(account.toString().contains("Joseph"));
	}
}
