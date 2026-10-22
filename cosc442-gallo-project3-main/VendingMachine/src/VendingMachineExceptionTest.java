import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineExceptionTest {

    //tests constructor
    @Test 
    public void testConstructor(){
        VendingMachineException exception = new VendingMachineException();
        assertNotNull(exception); 
    }
    
    //tests constructor with a parameter
    @Test 
    public void testParameterConstructor(){
        String errorMessage = "Invalid amount of balance to pay for item";

        VendingMachineException exception = new VendingMachineException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}
