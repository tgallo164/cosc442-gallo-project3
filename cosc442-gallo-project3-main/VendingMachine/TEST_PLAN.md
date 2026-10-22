
| Method | Valid Behavior | Exception | Expected Result |
| --- | --- | --- | --- |
| addItem | Adds an item to a machine's empty slot | Adding an item to a not empty slot | Adds the item to the specified slot |
| getBalance | Returns the balance of the machine | None | Returns the correct balance of the machine |
| getItem | Returns the item that is stored in the specified slot | None | Returns the correct item in the specified slot |
| insertMoney | Adds money to machine's balance, done with parameterized test | Adding negative values or a 0 | Updated the machine's current balance with the money inserted |
| makePurchase | Allows a purchase if balance amount for item is met and if the slot requested has an item | Not enough balance to purchase the item, slot that is trying to be purchased has no item | Removes item from slot and correctly updates the balance |
| removeItem | Deletes item out of the specified slot | Nonexistent slot or already empty slot | Specified slot will return null | 
| returnChange | Gives change back to the user and updates machine balance | None | Sets balance back to 0 |
| getName | Returns the name of the specified item | None | Returns the correct name of the specified item |
| getPrice | Returns the price of the specified item | None | Returns the correct price of the specified item |
