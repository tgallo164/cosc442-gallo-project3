import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;

public class VendingMachineTest {
    VendingMachine machine;
    VendingMachineItem item1;
    VendingMachineItem item2;
    VendingMachineItem item3;
    VendingMachineItem item4;

    @BeforeEach 
    public void setUp(){
        machine = new VendingMachine();
        item1 = new VendingMachineItem("Doritos", 3.50);
        item2 = new VendingMachineItem("Cheetos", 2.75);
        item3 = new VendingMachineItem("Funyuns", 3.25);
        item4 = new VendingMachineItem("Fritos", 2.00);
    }

    @AfterEach
    public void tearDown(){
        machine = null;
        item1 = null;
        item2 = null;
        item3 = null;
        item4 = null;
    }

    @Test
    void testAddItem() throws VendingMachineException {
        machine.addItem(item1, "A");
        assertEquals(item1, machine.getItem("A"));
    }

    @Test
    void testGetBalance() throws VendingMachineException {
        machine.balance = 10;
        assertEquals(10, machine.getBalance());
    }

    @Test
    void testAlreadyOccupied() throws VendingMachineException{
        machine.addItem(item1, "A");
        assertThrows(VendingMachineException.class, () -> {
            machine.addItem(item2, "A");
        });
    }

    @Test
    void testAddInvalidSlot() throws VendingMachineException{
        assertThrows(VendingMachineException.class, () -> {
            machine.addItem(item1, "F");
        });
    }

    @Test
    void testGetItem() throws VendingMachineException {
        machine.addItem(item2, "B");
        assertEquals(item2, machine.getItem("B"));
    }

    @Test 
    void testGetInvalidItem() throws VendingMachineException{
        assertThrows(VendingMachineException.class, () -> {
            machine.getItem("F");
        });
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, .50, .75, 1.00, 5.00, 10.00}) 
    void testInsertMoney(double amount) throws VendingMachineException {
        machine.insertMoney(amount);
        assertEquals(amount, machine.getBalance());
    }

    @Test 
    void testInsertInvalidMoney() throws VendingMachineException{
        assertThrows(VendingMachineException.class, () -> {
            machine.insertMoney(-1.0);
        });
    }
    

    @Test
    void testMakePurchase() throws VendingMachineException {
        machine.addItem(item3, "C");
        machine.insertMoney(10);
        assertTrue(machine.makePurchase("C"));
        assertEquals(6.75, machine.getBalance());
    }

    @Test
    void testMakePurchaseInvalidSlot() throws VendingMachineException{
        assertFalse(machine.makePurchase("C"));
    }

    @Test
    void testMakePurchaseInsufficientMoney() throws VendingMachineException{
        machine.addItem(item1, "A");
        machine.insertMoney(.01);
        assertFalse(machine.makePurchase("A"));
        assertEquals(item1, machine.getItem("A"));
    }

    @Test
    void testRemoveItem() throws VendingMachineException {
        machine.addItem(item4, "D");
        assertEquals(item4, machine.removeItem("D"));
    }

    @Test
    void testRemoveItemEmptySlot() throws VendingMachineException{
        assertThrows(VendingMachineException.class, () -> {
            machine.removeItem("A");
        });
    }

    @Test 
    void testRemoveItemInvalidCode() throws VendingMachineException{
        assertThrows(VendingMachineException.class, () -> {
            machine.removeItem("F");
        });
    }

    @Test
    void testReturnChange() throws VendingMachineException {
        machine.insertMoney(3.00);
        assertEquals(3.00, machine.returnChange());
        assertEquals(0, machine.getBalance());
    }
}
