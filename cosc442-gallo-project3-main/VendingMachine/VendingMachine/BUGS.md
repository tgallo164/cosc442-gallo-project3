## Fault #1: Array Index Out of Bounds in Vending Machine Constructor
* **Observed Failure:** Observed at every test in VendingMachineTest so it had to be a constructor issue in @BeforeEach.
* **Test that exposed the fault:** Every test.
* **Source-code fault that caused it:** For loop in the VendingMachine() constructor.
* **Fault Diagnosis:** The array that holds items has length 4 but because its an array it is indexes 0-3. The loop in the constructor attempts to access index 4 which does not exist.
* **Correction:** Fixed for loop to: for (int i = 0; i < NUM_SLOTS; i++).

## Fault #2: Invalid amount. Amount must be ]= 0
* **Observed Failure:** Observed when money amount inserted is less than $1
* **Test that exposed the fault:** testInsertMoney
* **Source-code fault that caused it:** the insertMoney method's check for a valid amount was if(amount < 1)
* **Fault Diagnosis:** The if statement checked if it was less than $1 instead of less than 0
* **Correction:** changed the if statement to: if(amount < 0)
