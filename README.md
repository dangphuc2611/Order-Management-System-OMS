# CREATE ORDER FLOW

1. The client sends a request to create an order, including the customer Id and a list of products

2. The system checks whether the customer exists.

3. An Order is created with status NEW and totalAmount = 0.

4. For each product in the order:

  - Check product stock availability.

  - Create an OrderItem.

  - Calculate the total amount using the formula: totalAmount += (price × quantity).

5. Update the totalAmount of the Order.

6. If any error occurs at any step, the entire transaction is rolled back.

7. Return the created Order information to the client.