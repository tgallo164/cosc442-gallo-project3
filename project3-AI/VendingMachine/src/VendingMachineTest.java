import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test suite for the VendingMachine class using JUnit 5.
 */
public class VendingMachineTest {

    private VendingMachine vendingMachine;
    private VendingMachineItem sampleItem1;
    private VendingMachineItem sampleItem2;

    @BeforeEach
    public void setUp() throws VendingMachineException {
        // Initializes a brand new vending machine and common items before each test execution
        vendingMachine = new VendingMachine();
        sampleItem1 = new VendingMachineItem("Soda", 1.50);
        sampleItem2 = new VendingMachineItem("Chips", 1.00);
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0.0, vendingMachine.getBalance(), 0.001, "Initial balance should be 0.0");
        
        Exception exception = assertThrows(VendingMachineException.class, () -> {
            vendingMachine.removeItem("A");
        });
        assertTrue(exception.getMessage().contains("is empty"));
    }

    @Test
    public void testAddAndGetItemValid() throws VendingMachineException {
        vendingMachine.addItem(sampleItem1, "A");
        vendingMachine.addItem(sampleItem2, "B");

        assertEquals(sampleItem1, vendingMachine.removeItem("A"));
        assertEquals(sampleItem2, vendingMachine.removeItem("B"));
    }

    @Test
    public void testAddDuplicateItemThrowsException() throws VendingMachineException {
        vendingMachine.addItem(sampleItem1, "A");

        VendingMachineException exception = assertThrows(VendingMachineException.class, () -> {
            vendingMachine.addItem(sampleItem2, "A");
        });
        assertTrue(exception.getMessage().contains("already occupied"));
    }

    @Test
    public void testAddInvalidCodeThrowsException() {
        VendingMachineException exception = assertThrows(VendingMachineException.class, () -> {
            vendingMachine.addItem(sampleItem1, "Z");
        });
        assertEquals("Invalid code for vending machine item", exception.getMessage());
    }

    @Test
    public void testRemoveItemValid() throws VendingMachineException {
        vendingMachine.addItem(sampleItem1, "C");
        VendingMachineItem removed = vendingMachine.removeItem("C");
        
        assertEquals(sampleItem1, removed);
        
        assertThrows(VendingMachineException.class, () -> {
            vendingMachine.removeItem("C");
        });
    }

    @Test
    public void testInsertMoneyValid() throws VendingMachineException {
        vendingMachine.insertMoney(2.50);
        assertEquals(2.50, vendingMachine.getBalance(), 0.001);

        vendingMachine.insertMoney(1.00);
        assertEquals(3.50, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testInsertMoneyNegativeThrowsException() {
        VendingMachineException exception = assertThrows(VendingMachineException.class, () -> {
            vendingMachine.insertMoney(-0.50);
        });
        assertEquals("Invalid amount.  Amount must be >= 0", exception.getMessage());
        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testMakePurchaseSuccessful() throws VendingMachineException {
        vendingMachine.addItem(sampleItem1, "A"); // Price: 1.50
        vendingMachine.insertMoney(2.00);

        boolean success = vendingMachine.makePurchase("A");

        assertTrue(success, "Purchase should be successful");
        assertEquals(0.50, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testMakePurchaseInsufficientFunds() throws VendingMachineException {
        vendingMachine.addItem(sampleItem1, "A"); // Price: 1.50
        vendingMachine.insertMoney(1.00);

        boolean success = vendingMachine.makePurchase("A");

        assertFalse(success, "Purchase should fail due to insufficient funds");
        assertEquals(1.00, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testReturnChange() throws VendingMachineException {
        vendingMachine.insertMoney(3.25);
        assertEquals(3.25, vendingMachine.getBalance(), 0.001);

        double change = vendingMachine.returnChange();
        assertEquals(3.25, change, 0.001);
        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
    }
}
