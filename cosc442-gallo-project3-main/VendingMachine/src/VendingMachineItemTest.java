import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;

public class VendingMachineItemTest {
    public VendingMachineItem item;

    @BeforeEach
    public void setUp(){ 
    item = new VendingMachineItem("Doritos", 2.00);
}

    @Test
    void testGetName() throws VendingMachineException{
        assertEquals("Doritos", item.getName());
    }

    @Test
    void testGetPrice() throws VendingMachineException {
        assertEquals(2.00, item.getPrice(), .001);
    }   

    @Test
    void testInvalidPirce() throws VendingMachineException{
        assertThrows(VendingMachineException.class, () -> {
            new VendingMachineItem("Fritos", -1.0);
        });
    }
}
