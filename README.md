"# shop-component"

The project has implemented the addition of new products to the MySQL database,
buying these products, viewing the current receipt and sales that is saving the receipt to the database.
The project currently has a record in the memory in the file and in the MySQL database.
Spring Boot, Swagger and Hibernate is implement.
I use design patterns strategy and decorator.

product-book-controller: adds another product to the receipt
and can show the value of the receipt.

product-load-controller: adds new product to database.

receipt-book-controller : is saving and get receipts to the database.

You can test the application in the link browser:
http://localhost:8080/swagger-ui.html#/receipt

For correct action of the application, please:
Run MySQL with  database named: market_test
The base must have:
username: root
password: admin