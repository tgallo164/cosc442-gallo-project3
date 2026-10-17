import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test suite for the VendingMachineItem class using JUnit 5.
 */
public class VendingMachineItemTest {

    private VendingMachineItem defaultItem;

    @BeforeEach
    public void setUp() throws VendingMachineException {
        // Set up a default valid item before each test runs
        defaultItem = new VendingMachineItem("Standard Soda", 1.25);
    }

    @Test
    public void testDefaultItemGetters() {
        assertEquals("Standard Soda", defaultItem.getName(), "Item name should match setup");
        assertEquals(1.25, defaultItem.getPrice(), 0.001, "Item price should match setup");
    }

    @Test
    public void testConstructorZeroPrice() throws VendingMachineException {
        // Zero is a valid boundary price (>= 0)
        VendingMachineItem freeItem = new VendingMachineItem("Free Sample", 0.0);
        
        assertEquals("Free Sample", freeItem.getName());
        assertEquals(0.0, freeItem.getPrice(), 0.001);
    }

    @Test
    public void testConstructorNegativePriceThrowsException() {
        // Exception tests instantiate locally because they require invalid inputs
        VendingMachineException exception = assertThrows(VendingMachineException.class, () -> {
            new VendingMachineItem("Invalid Chips", -0.50);
        });
        
        assertEquals("Price cannot be less than zero", exception.getMessage());
    }

    @Test
    public void testConstructorNullName() throws VendingMachineException {
        VendingMachineItem nullNameItem = new VendingMachineItem(null, 2.00);
        
        assertNull(nullNameItem.getName());
        assertEquals(2.00, nullNameItem.getPrice(), 0.001);
    }
}